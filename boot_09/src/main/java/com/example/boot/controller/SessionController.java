package com.example.boot.controller;

import com.example.boot.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/29
 */
@RestController
public class SessionController {

    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String userName, String password) {
        String msg = "login success!";
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        request.getSession().setAttribute("user", user);
        return msg;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String msg = "index content";
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            msg = "please login first";
        }
        return msg;
    }
}
