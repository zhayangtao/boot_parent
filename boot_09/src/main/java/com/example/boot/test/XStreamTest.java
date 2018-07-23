package com.example.boot.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/11
 */
public class XStreamTest {

    @XStreamAlias("person")
    @Getter
    @Setter
    static class PersonBean {
        @XStreamAlias("firstName")
        private String firstName;

        @XStreamAlias("lastName")
        private String lastName;

        @XStreamAlias("telephone")
        private PhoneNumber tel;

        @XStreamAlias("faxphone")
        private PhoneNumber fax;

        @XStreamAlias("friends")
        private Friends friend;

        @XStreamAlias("pets")
        private Pets pet;
    }


    @Getter
    @Setter
    @XStreamAlias("phoneNumber")
    static class PhoneNumber {
        @XStreamAlias("code")
        private int code;

        @XStreamAlias("number")
        private String number;
    }

    @Getter
    @Setter
    static class Friends {
        @XStreamImplicit(itemFieldName = "name") // 隐式集合
        private List<String> name;

    }

    @Getter
    @Setter
    static class Animal {
        @XStreamAlias("name")
        private String name;

        @XStreamAlias("age")
        private int age;

        Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 测试同一标签下循环某一对象
     */
    @Setter
    @Getter
    static class Pets {
        @XStreamImplicit(itemFieldName = "pet")
        private List<Animal> animalList;
    }

    public static void main(String[] args) {
        PersonBean bean = new PersonBean();
        bean.setFirstName("chen");
        bean.setLastName("long");

        PhoneNumber tel = new PhoneNumber();
        tel.setCode(11111);
        tel.setNumber("11111111");

        PhoneNumber fax = new PhoneNumber();
        fax.setCode(22222);
        fax.setNumber("2222222");

        bean.setTel(tel);
        bean.setFax(fax);

        List<String> friendList = new ArrayList<>();
        friendList.add("A1");
        friendList.add("A2");
        friendList.add("A3");
        Friends friend1 = new Friends();
        friend1.setName(friendList);
        bean.setFriend(friend1);

        Animal dog = new Animal("Dolly", 2);
        Animal cat = new Animal("ketty", 2);
        List<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(cat);

        Pets pets = new Pets();
        pets.setAnimalList(animals);
        bean.setPet(pets);

        String xml = XmlUtil.toXml(bean);
        System.out.println(xml);


    }
}

class XmlUtil {
    static String toXml(Object object) {
        XStream xStream = new XStream();
        xStream.processAnnotations(object.getClass());
        return xStream.toXML(object);
    }

    static <T> T toBean(String xmlStr, Class<T> tClass) {
        XStream xStream = new XStream(new Dom4JDriver());
        xStream.processAnnotations(tClass);
        return (T) xStream.fromXML(xmlStr);
    }

    static boolean toXMLFile(Object o, String absPath, String fileName) {
        String strXml = toXml(o);
        String filePath = absPath + fileName;
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false ;
            }
        }// end if
        OutputStream ous = null ;
        try {
            ous = new FileOutputStream(file);
            ous.write(strXml.getBytes());
            ous.flush();
        } catch (Exception e1) {
            return false;
        }finally{
            if(ous != null )
                try {
                    ous.close();
                } catch (IOException e) {

                }
        }
        return true ;
    }


}