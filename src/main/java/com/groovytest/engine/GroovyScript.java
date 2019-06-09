package com.groovytest.engine;

import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * LIUY
 */
public class GroovyScript extends GroovyObjectSupport {

    /**
     * 脚本名称
     */
    private String name;

    /**
     * 进行转换操作的代码块
     */
    private Closure mapClosure;

    /**
     * 进行转换操作的多个代码块
     */
    private Map<Integer, Closure> closureMap = new HashMap<Integer, Closure>();


    public void map(Integer key, Closure mapClosure) {
        this.closureMap.put(key, mapClosure);
    }


    //////getter and setter //////
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Closure getMapClosure() {
        return this.mapClosure;
    }

    public void setMapClosure(Closure mapClosure) {
        this.mapClosure = mapClosure;
    }

    public Map<Integer, Closure> getClosureMap() {
        return this.closureMap;
    }

    public void setClosureMap(Map<Integer, Closure> closureMap) {
        this.closureMap = closureMap;
    }
}
