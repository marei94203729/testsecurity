package com.ml.testsecurity.mapper;

import com.ml.testsecurity.entity.Menu;
import org.apache.ibatis.jdbc.SQL;

public class MenuSqlProvider {
    public String insertSelective(Menu record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("menu");
        
        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIconcls() != null) {
            sql.VALUES("iconCls", "#{iconcls,jdbcType=VARCHAR}");
        }
        
        if (record.getParentid() != null) {
            sql.VALUES("parentId", "#{parentid,jdbcType=BIGINT}");
        }
        
        if (record.getHasmenu() != null) {
            sql.VALUES("hasMenu", "#{hasmenu,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Menu record) {
        SQL sql = new SQL();
        sql.UPDATE("menu");
        
        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIconcls() != null) {
            sql.SET("iconCls = #{iconcls,jdbcType=VARCHAR}");
        }
        
        if (record.getParentid() != null) {
            sql.SET("parentId = #{parentid,jdbcType=BIGINT}");
        }
        
        if (record.getHasmenu() != null) {
            sql.SET("hasMenu = #{hasmenu,jdbcType=BIT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}