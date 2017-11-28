package com.waylau.netty.demo.codec.jackcon;

import java.util.List;
import java.util.Map;

/**
 * Created by thomas.su on 2017/11/21 21:49.
 */
public class JacksonBean {
    private String name;
    private int age;
    private List<String> sons;
    private Map<String, String> addrs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSons() {
        return sons;
    }

    public void setSons(List<String> sons) {
        this.sons = sons;
    }

    public Map<String, String> getAddrs() {
        return addrs;
    }

    public void setAddrs(Map<String, String> addrs) {
        this.addrs = addrs;
    }
}
