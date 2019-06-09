package com.groovytest.engine;

/**
 * LIUY
 * GroovyScriptCache
 */
public class GroovyScriptCache {

    private GroovyScript groovyScript;

    private String md5;

    // GETTER AND SETTER
    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public GroovyScript getGroovyScript() {
        return this.groovyScript;
    }

    public void setGroovyScript(GroovyScript groovyScript) {
        this.groovyScript = groovyScript;
    }

}
