package com.groovytest.annotation.service;

import com.groovytest.annotation.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * groovy脚本注解扫描类，在groovy脚本内即可使用getCount(sql)方法，注意方法名不可和其他注解扫描类冲突
 *
 * @author lion
 */
@DataProvider(name = "getCount")
public class ProviderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object call(String sql) {
        Long count = this.jdbcTemplate.queryForObject(sql, Long.class);
        return count;
    }

}
