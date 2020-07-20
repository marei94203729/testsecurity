package com.ml.testsecurity.mapper;

import com.ml.testsecurity.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {


    @Delete({
        "delete from c_customer",
        "where sysId = #{sysid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long sysid);

    @Insert({
        "insert into c_customer (sysId, username, ",
        "password, identifier, ",
        "nickname, sex, createtime, ",
        "lastlogintime, is_active, ",
        "islimit, ipaddress, ",
        "s_shop_id, openid, ",
        "FBindMobile, VIPCode, ",
        "cardno, lcname, ",
        "lccode, birthday, ",
        "address, introducer, ",
        "appopenid, unionid, ",
        "miniintroducer)",
        "values (#{sysid,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{identifier,jdbcType=CHAR}, ",
        "#{nickname,jdbcType=VARCHAR}, #{sex,jdbcType=CHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{lastlogintime,jdbcType=TIMESTAMP}, #{isActive,jdbcType=CHAR}, ",
        "#{islimit,jdbcType=BIT}, #{ipaddress,jdbcType=VARCHAR}, ",
        "#{sShopId,jdbcType=BIGINT}, #{openid,jdbcType=VARCHAR}, ",
        "#{fbindmobile,jdbcType=VARCHAR}, #{vipcode,jdbcType=BIGINT}, ",
        "#{cardno,jdbcType=VARCHAR}, #{lcname,jdbcType=VARCHAR}, ",
        "#{lccode,jdbcType=VARCHAR}, #{birthday,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{introducer,jdbcType=VARCHAR}, ",
        "#{appopenid,jdbcType=VARCHAR}, #{unionid,jdbcType=VARCHAR}, ",
        "#{miniintroducer,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "sysid", keyColumn = "sysId")//设置主键
    int insert(Customer record);



    @Select({
        "select",
        "sysId, username, password, identifier, nickname, sex, createtime, lastlogintime, ",
        "is_active, islimit, ipaddress, s_shop_id, openid, FBindMobile, VIPCode, cardno, ",
        "lcname, lccode, birthday, address, introducer, appopenid, unionid, miniintroducer",
        "from c_customer",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="sysId", property="sysid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="identifier", property="identifier", jdbcType=JdbcType.CHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastlogintime", property="lastlogintime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_active", property="isActive", jdbcType=JdbcType.CHAR),
        @Result(column="islimit", property="islimit", jdbcType=JdbcType.BIT),
        @Result(column="ipaddress", property="ipaddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="s_shop_id", property="sShopId", jdbcType=JdbcType.BIGINT),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="FBindMobile", property="fbindmobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="VIPCode", property="vipcode", jdbcType=JdbcType.BIGINT),
        @Result(column="cardno", property="cardno", jdbcType=JdbcType.VARCHAR),
        @Result(column="lcname", property="lcname", jdbcType=JdbcType.VARCHAR),
        @Result(column="lccode", property="lccode", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="introducer", property="introducer", jdbcType=JdbcType.VARCHAR),
        @Result(column="appopenid", property="appopenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="unionid", property="unionid", jdbcType=JdbcType.VARCHAR),
        @Result(column="miniintroducer", property="miniintroducer", jdbcType=JdbcType.VARCHAR)
    })
    Customer selectByName(String username);
//, @Param("pageNum") int pageNum,@Param("pageSize") int pageSize


    @Update({
        "update c_customer",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "identifier = #{identifier,jdbcType=CHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=CHAR},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "lastlogintime = #{lastlogintime,jdbcType=TIMESTAMP},",
          "is_active = #{isActive,jdbcType=CHAR},",
          "islimit = #{islimit,jdbcType=BIT},",
          "ipaddress = #{ipaddress,jdbcType=VARCHAR},",
          "s_shop_id = #{sShopId,jdbcType=BIGINT},",
          "openid = #{openid,jdbcType=VARCHAR},",
          "FBindMobile = #{fbindmobile,jdbcType=VARCHAR},",
          "VIPCode = #{vipcode,jdbcType=BIGINT},",
          "cardno = #{cardno,jdbcType=VARCHAR},",
          "lcname = #{lcname,jdbcType=VARCHAR},",
          "lccode = #{lccode,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "introducer = #{introducer,jdbcType=VARCHAR},",
          "appopenid = #{appopenid,jdbcType=VARCHAR},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "miniintroducer = #{miniintroducer,jdbcType=VARCHAR}",
        "where sysId = #{sysid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Customer record);
}