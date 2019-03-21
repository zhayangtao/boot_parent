package com.example;

import act.Act;
import act.conf.AppConfig;
import act.controller.annotation.UrlContext;
import act.inject.HeaderVariable;
import act.util.Global;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.inject.annotation.Configuration;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.ResponseContentType;
import org.osgl.util.Str;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static act.controller.Controller.Util.renderText;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/12/26
 */
@UrlContext("/conf")
public class ConfTest {

    @Configuration("myconf.foo.bar")
    private int bar;

    @GetAction("inject")
    public int inject() {
        return this.bar;
    }

    @GetAction("pull")
    public int pull() {
        AppConfig config = Act.appConfig();
        return $.convert(config.get("myconf.foo.bar")).toInt();
    }

    @GetAction("map")
    @ResponseContentType(H.MediaType.JSON)
    public Object barMap(@Configuration("myconf.map.demo") Map<String, Integer> barMap) {
        return barMap;
    }

    @GetAction("list")
    @ResponseContentType(H.MediaType.JSON)
    public int[] listDemo(@Configuration("myconf.list.demo") int[] list) {
        return list;
    }

    @GetAction("list2")
    @ResponseContentType(H.MediaType.JSON)
    public List<Integer> listDemo2(@Configuration("myconf.list.demo") List<Integer> list) {
        return list;
    }

    @GetAction("list3")
    @ResponseContentType(H.MediaType.JSON)
    public List<String> listDemo3(@Configuration("myconf.list.demo") List<String> list) {
        return list;
    }

    // 路径变量
    @GetAction("users/{userId}")
    public int getUserById(int userId) {
        return userId;
    }

    @GetAction("range/latitude={latitude},longitude={longitude}")
    public List<Double> searchRange(double latitude, double longitude) {
        List<Double> range = new ArrayList<>();
        range.add(latitude);
        range.add(longitude);
        return range;
    }

    // 使用请求与响应
    @GetAction("echo/a")
    public void echo_a(H.Request request, H.Response response) {
        String message = request.paramVal("message");
        response.header("Content-Type", "text/plain").output().append(message).close();
    }

    @GetAction("echo/b")
    public void echo_b(H.Request request, H.Response response) {
        String message = request.paramVal("message");
        response.writeText(message);
    }

    @GetAction("echo/c/{message}") // 优先级: URL 路径变量 > Query 参数 > Form 字段
    public void echo_c(String message) {
        renderText(message);
    }

    @Inject
    private TestController testController;

    @GetAction("testcontrol")
    public void testcontrol() {
        testController.pr();
        System.out.println("testcontrol");
    }

    @Global
    @Before
    public void countVisits(H.Cookie count) {
        if (count == null) {
            count = new H.Cookie("Count", "1");
        }
        count.incr();
        count.addToResponse();
    }

    // header 数据绑定
    @GetAction("/header/user-agent")
    public String header(@HeaderVariable("User-Agent") String userAgentString) {
        return userAgentString;
    }
}
