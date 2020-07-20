package com.ml.testsecurity.entity;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    private Long sysid;

    private String username;

    private String password;

    private String identifier;

    private String nickname;

    private String sex;

    private Date createtime;

    private Date lastlogintime;

    private String isActive;

    private Boolean islimit;

    private String ipaddress;

    private Long sShopId;

    private String openid;

    private String fbindmobile;

    private Long vipcode;

    private String cardno;

    private String lcname;

    private String lccode;

    private String birthday;

    private String address;

    private String introducer;

    private String appopenid;

    private String unionid;

    private String miniintroducer;

    private static final long serialVersionUID = 1L;

    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }

    public Boolean getIslimit() {
        return islimit;
    }

    public void setIslimit(Boolean islimit) {
        this.islimit = islimit;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public Long getsShopId() {
        return sShopId;
    }

    public void setsShopId(Long sShopId) {
        this.sShopId = sShopId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getFbindmobile() {
        return fbindmobile;
    }

    public void setFbindmobile(String fbindmobile) {
        this.fbindmobile = fbindmobile == null ? null : fbindmobile.trim();
    }

    public Long getVipcode() {
        return vipcode;
    }

    public void setVipcode(Long vipcode) {
        this.vipcode = vipcode;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getLcname() {
        return lcname;
    }

    public void setLcname(String lcname) {
        this.lcname = lcname == null ? null : lcname.trim();
    }

    public String getLccode() {
        return lccode;
    }

    public void setLccode(String lccode) {
        this.lccode = lccode == null ? null : lccode.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer == null ? null : introducer.trim();
    }

    public String getAppopenid() {
        return appopenid;
    }

    public void setAppopenid(String appopenid) {
        this.appopenid = appopenid == null ? null : appopenid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getMiniintroducer() {
        return miniintroducer;
    }

    public void setMiniintroducer(String miniintroducer) {
        this.miniintroducer = miniintroducer == null ? null : miniintroducer.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sysid=").append(sysid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", identifier=").append(identifier);
        sb.append(", nickname=").append(nickname);
        sb.append(", sex=").append(sex);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastlogintime=").append(lastlogintime);
        sb.append(", isActive=").append(isActive);
        sb.append(", islimit=").append(islimit);
        sb.append(", ipaddress=").append(ipaddress);
        sb.append(", sShopId=").append(sShopId);
        sb.append(", openid=").append(openid);
        sb.append(", fbindmobile=").append(fbindmobile);
        sb.append(", vipcode=").append(vipcode);
        sb.append(", cardno=").append(cardno);
        sb.append(", lcname=").append(lcname);
        sb.append(", lccode=").append(lccode);
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append(", introducer=").append(introducer);
        sb.append(", appopenid=").append(appopenid);
        sb.append(", unionid=").append(unionid);
        sb.append(", miniintroducer=").append(miniintroducer);
        sb.append("]");
        return sb.toString();
    }
}