package com.groovytest.engine.builder;

public class NameBuilder {

    private String name;

    public void call(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
