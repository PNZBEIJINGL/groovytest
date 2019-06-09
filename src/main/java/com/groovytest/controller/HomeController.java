package com.groovytest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groovytest.engine.GroovyScriptEngine;
import com.groovytest.utils.HttpResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 控制器用于测试
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    protected final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        return "home";
    }

    /**
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "getData", method = RequestMethod.GET)
    @ResponseBody
    public void getData(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        String groovyScriptName = "config.groovy";

        //初始化Groovy引擎
        GroovyScriptEngine engine = new GroovyScriptEngine();

        //传递contexMap参数，此参数为map，在groovy脚本中即可使用context.key调用
        Map<String, Object> contextParamMap = new HashMap<String, Object>();
        contextParamMap.put("param", "参数数据");

        //----------------多个map，返回listMap----------------
        List<Map<String, Object>> listMap = engine
                .convertToMapList(groovyScriptName, contextParamMap);
        //----------------多个map，返回listMap----------------

        //----------------多个map，返回Map----------------
//        Map<Integer, Map<String, Object>> map = engine
//            .convertMapToMap(groovyScriptName, contextParamMap);
//        Set<Integer> keySet = map.keySet();
//        for(Integer key : keySet){
//            listMap.add(map.get(key));
//        }
        //----------------多个map，返回Map----------------

        //----------------单个map----------------
//        List<Map<String, Object>> listMap = new ArrayList<>();
//        Map<String, Object> map = engine.convertToMap(groovyScriptName,
//            contextParamMap);
//        listMap.add(map);
        //----------------单个map----------------

        HttpResponseHelper.outUTF8(response, this.gson.toJson(listMap));
    }

}
