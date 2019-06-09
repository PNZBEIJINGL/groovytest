package com.groovytest.engine.builder;

import java.util.HashMap;
import java.util.Map;

import groovy.lang.Closure;

public class MapBuilder {
    /**
     * 进行转换操作的代码块
     */
    private Closure mapClosure;

    /**
     * 进行转换操作的多个代码块
     */
    private Map<Integer, Closure> closureMap = new HashMap<Integer, Closure>();

    private void call(Integer key, Closure closure) {

        this.closureMap.put(key, closure);
    }

    private void call(Closure closure) {

        this.mapClosure = closure;
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
