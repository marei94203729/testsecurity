package com.ml.testsecurity.mapper;

import com.ml.testsecurity.entity.Menu;
import com.ml.testsecurity.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuMapper {
    @Delete({
        "delete from menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into menu (url, name, ",
        "iconCls, parentId, ",
        "hasMenu)",
        "values (#{url,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{iconcls,jdbcType=VARCHAR}, #{parentid,jdbcType=BIGINT}, ",
        "#{hasmenu,jdbcType=BIT})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "Id")//设置主键
   // @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Menu record);

    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Menu record);

    @Select({
        "select",
        "id, url, name, iconCls, parentId, hasMenu",
        "from menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="iconCls", property="iconcls", jdbcType=JdbcType.VARCHAR),
        @Result(column="parentId", property="parentid", jdbcType=JdbcType.BIGINT),
        @Result(column="hasMenu", property="hasmenu", jdbcType=JdbcType.BIT)
    })
    Menu selectByPrimaryKey(Long id);
    @Select({
            "select",
            "id, url, name, iconCls, parentId, hasMenu",
            "from menu",
            "where url = #{requestUrl,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="iconCls", property="iconcls", jdbcType=JdbcType.VARCHAR),
            @Result(column="parentId", property="parentid", jdbcType=JdbcType.BIGINT),
            @Result(column="hasMenu", property="hasmenu", jdbcType=JdbcType.BIT),
            @Result(column="id", property="roles", javaType = List.class,many = @Many(select="listById"))
    })
    Menu selectByRequestUrl(String requestUrl);
    @Select({
            "select",
            "id, url, name, iconCls, parentId, hasMenu",
            "from menu"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="iconCls", property="iconcls", jdbcType=JdbcType.VARCHAR),
            @Result(column="parentId", property="parentid", jdbcType=JdbcType.BIGINT),
            @Result(column="hasMenu", property="hasmenu", jdbcType=JdbcType.BIT),
            @Result(column="id", property="roles", javaType = List.class,many = @Many(select="listById"))
    })
    List<Menu> findAllMenu();
    @Select({
            "select",
            "r.id, name, nameZh",
            "from role r left join menu_role ur on r.id=ur.rid",
            "where ur.mid = #{mid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="nameZh", property="namezh", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> listById(Long mid);
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    @Update({
        "update menu",
        "set url = #{url,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "iconCls = #{iconcls,jdbcType=VARCHAR},",
          "parentId = #{parentid,jdbcType=BIGINT},",
          "hasMenu = #{hasmenu,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Menu record);
}