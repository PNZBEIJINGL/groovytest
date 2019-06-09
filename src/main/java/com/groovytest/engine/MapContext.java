package com.groovytest.engine;

import com.groovytest.test.ApplicationContextHolder;
import com.groovytest.utils.BeanConvertUtils;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.Map;


public class MapContext extends GroovyObjectSupport implements Serializable {

    private DataContext context = new DataContext();

    public MapContext() {
        ApplicationContext applicationContext = ApplicationContextHolder
                .getApplicationContext();
        applicationContext.getAutowireCapableBeanFactory()
                .autowireBeanProperties(this,
                        AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
    }

    public void push(String node, Object value) {

        this.context.getDataSource().put(node, value);
    }

    public void push(Map value) {
        this.context.getDataSource().putAll(value);
    }

    public void push(Object value) {
        this.context.getDataSource().putAll(BeanConvertUtils.toMap(value));

    }

    public void push(String node, Closure closure) {
        this.context.getDataSource().put(node, closure.call());
    }

    public <T> T get(String key) {

        return (T) this.context.getDataSource().get(key);
    }

    public DataContext getContext() {
        return this.context;
    }

    public void initDataContext(Map<String, Object> data) {
        if (data != null) {
            this.context.putAll(data);
        }
    }
}
