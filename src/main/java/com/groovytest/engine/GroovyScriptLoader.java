package com.groovytest.engine;

import com.google.javascript.jscomp.SourceFile;
import com.groovytest.annotation.DataProvider;
import com.groovytest.engine.builder.GroovyScriptBuilder;
import com.groovytest.test.ApplicationContextHolder;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroovyScriptLoader {

    private static Map<String, GroovyScriptCache> cache = new ConcurrentHashMap<String, GroovyScriptCache>();

    private static final Log LOGGER = LogFactory.getLog(GroovyScriptLoader.class);

    /**
     * load脚本
     *
     * @param fileRelativePath
     * @return
     * @throws Exception
     */
    public GroovyScript load(String fileRelativePath) throws Exception {
        if (!StringUtils.hasText(fileRelativePath)) {
            throw new Exception("print.template.path.is.not.config");
        }
        LOGGER.info("Parse print script start,file path:" + fileRelativePath);
        GroovyScriptCache groovyScriptCache = cache.get(fileRelativePath);
        String script = this.getScriptRawValue(fileRelativePath);
        String md5 = DigestUtils.md5DigestAsHex(script.getBytes());
        if (groovyScriptCache == null || !groovyScriptCache.getMd5().equals(md5)) {
            GroovyScript groovyScript = GroovyScriptLoader.parseScript(script);
            groovyScriptCache = new GroovyScriptCache();
            groovyScriptCache.setMd5(md5);
            groovyScriptCache.setGroovyScript(groovyScript);
            cache.put(fileRelativePath, groovyScriptCache);
        }
        LOGGER.info("Parse print script finish,file path:" + fileRelativePath);
        return cache.get(fileRelativePath).getGroovyScript();
    }

    /**
     * 解析并构建groovy脚本，封装在GScriptBuilder内返回
     *
     * @param script
     * @return
     */
    private static GroovyScript parseScript(String script) {
        Binding binding = new Binding();
        GroovyScriptBuilder groovyScriptBuilder = GroovyScriptLoader
                .createScriptBuilder(binding);
        GroovyScriptLoader.initSupportMethods(binding);
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(script);
        return groovyScriptBuilder.build();
    }

    private static GroovyScriptBuilder createScriptBuilder(Binding binding) {
        GroovyScriptBuilder groovyScriptBuilder = new GroovyScriptBuilder();
        //设置groovy脚本名称，暂时不使用
        binding.setVariable("name", groovyScriptBuilder.getNameBuilder());
        //设置variable，在groovy脚本内可以调用此variable，本例即“map”方法
        binding.setVariable("map", groovyScriptBuilder.getMapBuilder());
        return groovyScriptBuilder;
    }

    /**
     * 遍历查找所有注解带有DataProvider的类，并解析注解方法名称，传递至binding，这样即可在groovy脚本内调用
     *
     * @param binding
     */
    private static void initSupportMethods(Binding binding) {

        Map<String, Object> beansWithDataProvider = ApplicationContextHolder
                .getApplicationContext().getBeansWithAnnotation(DataProvider.class);
        beansWithDataProvider.values().forEach(bean -> {
            DataProvider dataProvider = bean.getClass()
                    .getAnnotation(DataProvider.class);
            try {
                GroovyScriptLoader.checkDuplicate(dataProvider.name(), binding,
                        bean);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            binding.setVariable(dataProvider.name(), bean);
        });

    }

    /**
     * 判断是否有重复方法名
     *
     * @param methodName
     * @param binding
     * @param bean
     * @throws Exception
     */
    private static void checkDuplicate(String methodName, Binding binding,
                                       Object bean) throws Exception {
        if (binding.hasVariable(methodName)) {
            throw new Exception("method.already.exist");
        }
    }

    private String getScriptRawValue(String fileRelativePath) throws Exception {
        String scriptAbsolutePath = this.getClass().getClassLoader()
                .getResource("").getPath() + fileRelativePath;
        LOGGER.info("scriptAbsolutePath:" + scriptAbsolutePath);
        SourceFile sourceFile = SourceFile.fromFile(scriptAbsolutePath,
                Charset.forName("UTF-8"));
        return sourceFile.getCode();
    }

}
