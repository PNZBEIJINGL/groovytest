package com.groovytest.engine.builder;

import com.groovytest.engine.GroovyScript;

public class GroovyScriptBuilder {

    private NameBuilder nameBuilder = new NameBuilder();

    private MapBuilder mapBuilder = new MapBuilder();

    public NameBuilder getNameBuilder() {

        return this.nameBuilder;
    }

    public MapBuilder getMapBuilder() {

        return this.mapBuilder;
    }

    public GroovyScript build() {
        GroovyScript groovyScript = new GroovyScript();
        groovyScript.setName(this.nameBuilder.getName());
        groovyScript.setMapClosure(this.mapBuilder.getMapClosure());
        groovyScript.setClosureMap(this.mapBuilder.getClosureMap());
        return groovyScript;
    }

}
