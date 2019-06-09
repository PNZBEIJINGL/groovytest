package com.groovytest.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import groovy.lang.Closure;

/**
 * GroovyScript引擎
 */
public class GroovyScriptEngine {

    private static final Log LOGGER = LogFactory.getLog(GroovyScriptEngine.class);

    /**
     * 当配置文件只有一个map方法的时候适用
     *
     * @param scriptUrl 脚本文件名
     * @param mapResult context参数map
     * @return
     * @throws Exception
     */
    public Map<String, Object> convertToMap(String scriptUrl,
                                            Map<String, Object> mapResult) throws Exception {
        LOGGER.info("Convert print script start");

        GroovyScriptLoader loader = new GroovyScriptLoader();
        GroovyScript groovyScript = loader.load(scriptUrl);
        MapContext mapContext = new MapContext();
        mapContext.initDataContext(mapResult);
        Closure mapClosure = groovyScript.getMapClosure();
        mapClosure.setDelegate(mapContext);
        mapClosure.call();
        Map<String, Object> dataSource = mapContext.getContext()
                .getDataSource();

        LOGGER.info("Convert print script finish");
        return dataSource;
    }

    /**
     * 当配置文件调用多个map方法的时候适用，返回List集合
     *
     * @param scriptUrl 脚本文件名
     * @param mapResult context参数map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> convertToMapList(String scriptUrl,
                                                      Map<String, Object> mapResult) throws Exception {
        LOGGER.info("Convert print script start");
        List<Map<String, Object>> returnMapList = new ArrayList<Map<String, Object>>();
        GroovyScriptLoader loader = new GroovyScriptLoader();
        GroovyScript groovyScript = loader.load(scriptUrl);
        Map<Integer, Closure> closureMap = groovyScript.getClosureMap();
        if (closureMap != null && closureMap.size() > 0) {
            Set<Integer> keySet = closureMap.keySet();
            for (Integer key : keySet) {
                MapContext mapContext = new MapContext();
                mapContext.initDataContext(mapResult);
                Closure mapClosure = closureMap.get(key);
                if (mapClosure != null) {
                    mapClosure.setDelegate(mapContext);
                    mapClosure.call();
                    Map<String, Object> dataSource = mapContext.getContext()
                            .getDataSource();
                    returnMapList.add(dataSource);
                }
            }
        }
        LOGGER.info("Convert print script finish");
        return returnMapList;
    }

    /**
     * 当配置文件有多个map的时候适用，返回Map集合，Map的key即为配置文件中调用map方法的第一个参数
     *
     * @param scriptUrl 脚本文件名
     * @param mapResult context参数map
     * @return
     * @throws Exception
     */
    public Map<Integer, Map<String, Object>> convertMapToMap(String scriptUrl,
                                                             Map<String, Object> mapResult) throws Exception {
        LOGGER.info("Convert print script start");
        Map<Integer, Map<String, Object>> mapToMaps = new HashMap<Integer, Map<String, Object>>();
        GroovyScriptLoader loader = new GroovyScriptLoader();
        GroovyScript groovyScript = loader.load(scriptUrl);
        Map<Integer, Closure> closureMap = groovyScript.getClosureMap();
        if (closureMap != null && closureMap.size() > 0) {
            Set<Integer> keySet = closureMap.keySet();
            for (Integer key : keySet) {
                MapContext mapContext = new MapContext();
                mapContext.initDataContext(mapResult);
                Closure mapClosure = closureMap.get(key);
                if (mapClosure != null) {
                    mapClosure.setDelegate(mapContext);
                    mapClosure.call();
                    Map<String, Object> dataSource = mapContext.getContext()
                            .getDataSource();
                    mapToMaps.put(key, dataSource);
                }
            }
        }
        LOGGER.info("Convert print script finish");
        return mapToMaps;
    }

}
