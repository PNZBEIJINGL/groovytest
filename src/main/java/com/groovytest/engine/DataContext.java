package com.groovytest.engine;

import java.util.HashMap;
import java.util.Map;


public class DataContext extends HashMap {

    private static final String TEMPLATE_DATA_SOURCE = "template_data_source";

    public Map<String, Object> getDataSource() {
        Map<String, Object> dataSource = (Map<String, Object>) this
                .get(TEMPLATE_DATA_SOURCE);

        if (dataSource == null) {
            dataSource = new HashMap<String, Object>();
            this.put(TEMPLATE_DATA_SOURCE, dataSource);
        }
        return dataSource;
    }
}
