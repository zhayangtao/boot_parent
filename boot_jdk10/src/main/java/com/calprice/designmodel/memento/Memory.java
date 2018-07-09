package com.calprice.designmodel.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/20
 */
public class Memory {
    private List<String> storyList;

    List<String> getStoryList() {
        return storyList;
    }

    void setStoryList(List<String> storyList) {
        this.storyList = storyList;
    }
}

class Person {
    private String name;
    private List<String> storyList;

    Person(String name) {
        this.name = name;
        storyList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStoryList() {
        return storyList;
    }

    void addStory(String history) {
        this.storyList.add(history);
    }

    // 获取记忆
    Memory getMemory() {
        Memory memory = new Memory();
        memory.setStoryList(new ArrayList<>(storyList));
        return memory;
    }

    // 恢复记忆
    void setMemory(Memory memory) {
        storyList = memory.getStoryList();
    }

}

class Soul {
    private Map<Person, Memory> memoryMap = new HashMap<>();

    // 收集记忆
    void pullAwayMemory(Person person) {
        memoryMap.put(person, person.getMemory());
    }

    // 恢复记忆
    void forceFixMemory(Person person) {
        if (memoryMap.containsKey(person)) {
            person.setMemory(memoryMap.get(person));
        }
    }
}

class Client {
    public static void main(String[] args) {
        Soul soulA = new Soul();
        Person personA = new Person("person A");
        Person personB = new Person("person B");
        personA.addStory("lalalal");
        personA.addStory("gagagag");
        personB.addStory("hahahah");
        personB.addStory("xixixix");
        soulA.pullAwayMemory(personA);
        soulA.pullAwayMemory(personB);
        personA.addStory("lalalal");
        personA.addStory("gagagag");

        soulA.forceFixMemory(personA);
        soulA.forceFixMemory(personB);
    }
}