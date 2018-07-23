package com.example.boot_rabbitmq;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/29
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @Cacheable(value = "helloCache") // Cacheable 表明方法支持缓存
    public String hello(String name) {
        System.out.println("没有走缓存");
        return "hello " + name;
    }

    /**
     * 一个支持缓存的方法在对象内部被调用时不会触发缓存功能
     *
     * @param name
     * @return
     */
    @RequestMapping("/condition")
    @Cacheable(value = "condition", condition = "#name.length() <= 4")
    public String condition(String name) {
        System.out.println("没有走缓存");
        return "hello " + name;
    }

    @RequestMapping("/cachePut")
    @CachePut(value = "cachePut", key = "#name")
    public String cachePut(String name) {
        System.out.println("执行了数据库操作");
        return "hello " + name;
    }

    @RequestMapping("/getUser")
    @Cacheable(value = "userCache", key = "1000")
    public String getUserByName(String userName) {
        System.out.println("两次调用第一次会调用，第二次不会调用");
        return "hello " + userName;
    }

    @RequestMapping("/updateUser")
    @CachePut(value = "userCache", key = "1000")
    public String updateUser(String userName) {
        userName += "hiahiahia";
        System.out.println("调用更新updateUser");
        return "update " + userName;
    }

    /**
     * CacheEvict用于清理缓存
     *
     * @param name
     * @return
     */
    @RequestMapping("/allEntries")
    @CacheEvict(value = "userCache", allEntries = true)
    public String allEntries(String name) {
        System.out.println("执行了数据库操作");
        return "hello " + name;
    }

    /**
     * 清除操作默认是在对应方法成功执行之后触发的，
     * 即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     * 使用 beforeInvocation 可以改变触发清除操作的时间，
     * 当我们指定该属性值为 true 时，Spring 会在调用该方法之前清除缓存中的指定元素。
     */
    @RequestMapping("/beforeInvocation")
    @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }

    @PostMapping(value = "/WebService/OutInterfaceService.asmx", consumes = "application/xml", produces ="application/xml")
    public OutRandCountResponse outRandCountResponse() {

        OutRandCountResponse randCountResponse = new OutRandCountResponse();
        OutRandCountResponse.Parameter parameter = new OutRandCountResponse.Parameter();
        parameter.setHashKey("XXce51cfXXXX472");
        parameter.setOpType("0");
        OutRandCountResponse.Head head = new OutRandCountResponse.Head();
        head.setParameter(parameter);
        OutRandCountResponse.Body body = new OutRandCountResponse.Body();
        body.setResult("OK");
        OutRandCountResponse.Row row = new OutRandCountResponse.Row();
//        row.setRandNum("2502");
        OutRandCountResponse.DataTable dataTable = new OutRandCountResponse.DataTable();
        dataTable.setRow(row);
        body.setDataTable(dataTable);
        randCountResponse.setHead(head);
        randCountResponse.setBody(body);

        return randCountResponse;
    }

    @PostMapping(value = "/WebService/AlipayService.asmx", consumes = "application/xml", produces ="application/xml;charset=utf-8")
    public OutRandCountResponse alipayService() {
        OutRandCountResponse randCountResponse = new OutRandCountResponse();
        OutRandCountResponse.Parameter parameter = new OutRandCountResponse.Parameter();
        parameter.setHashKey("XXce51cfXXXX472");
        parameter.setOpType("0");
        OutRandCountResponse.Head head = new OutRandCountResponse.Head();
        head.setParameter(parameter);
        OutRandCountResponse.Body body = new OutRandCountResponse.Body();
        body.setResult("OK");
        OutRandCountResponse.Row row = new OutRandCountResponse.Row();
//        row.setRandNum("2502");
        row.setItemname("抽血检验B");
        row.setWaitnum("1");
        row.setCurnum("2220");
        row.setMynum("2221");
        row.setIsmiss("否");
        row.setFlag("1");
        OutRandCountResponse.DataTable dataTable = new OutRandCountResponse.DataTable();
        dataTable.setRow(row);
        body.setDataTable(dataTable);
        randCountResponse.setHead(head);
        randCountResponse.setBody(body);
        return randCountResponse;
    }

}

@XmlRootElement(name = "root")
class OutRandCountResponse {

    private Head head;
    private Body body;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Head {
        private Parameter parameter;

        @XmlElement
        public Parameter getParameter() {
            return parameter;
        }

        void setParameter(Parameter parameter) {
            this.parameter = parameter;
        }
    }

    public static class Parameter {
        private String hashKey;

        private String opType;

        public String getHashKey() {
            return hashKey;
        }

        public void setHashKey(String hashKey) {
            this.hashKey = hashKey;
        }

        @XmlElement(name = "Optype")
        public String getOpType() {
            return opType;
        }
        public void setOpType(String opType) {
            this.opType = opType;
        }
    }

    public static class Body {
        private String result;
        private DataTable dataTable;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        @XmlElement(name = "DataTable")
        public DataTable getDataTable() {
            return dataTable;
        }

        public void setDataTable(DataTable dataTable) {
            this.dataTable = dataTable;
        }
    }


    public static class DataTable {

        private Row row;

        public Row getRow() {
            return row;
        }

        public void setRow(Row row) {
            this.row = row;
        }
    }

    public static class Row {
        private String id;
        private String itemname;
        private String waitnum;
        private String curnum;
        private String mynum;
        private String ismiss;
        private String flag;
//        private String randNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getWaitnum() {
            return waitnum;
        }

        public void setWaitnum(String waitnum) {
            this.waitnum = waitnum;
        }

        public String getCurnum() {
            return curnum;
        }

        public void setCurnum(String curnum) {
            this.curnum = curnum;
        }

        public String getMynum() {
            return mynum;
        }

        public void setMynum(String mynum) {
            this.mynum = mynum;
        }

        public String getIsmiss() {
            return ismiss;
        }

        public void setIsmiss(String ismiss) {
            this.ismiss = ismiss;
        }

        @XmlElement(name = "Flag")
        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

       /* @XmlElement(name = "RandNum")
        public String getRandNum() {
            return randNum;
        }

        public void setRandNum(String randNum) {
            this.randNum = randNum;
        }*/
    }
}