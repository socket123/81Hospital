package com.chusu.apps.model.business;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码MD5 32位小写加密
     */
    private String password;

    /**
     * 第三方登录ID
     */
    @Column(name = "unionID")
    private Integer unionid;

    /**
     * 房卡数量
     */
    @Column(name = "card_num")
    private Integer cardNum;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 常用地址（记录市级，安全验证时使用）
     */
    @Column(name = "commen_address")
    private String commenAddress;

    /**
     * 微信号
     */
    @Column(name = "wx_number")
    private String wxNumber;

    /**
     * QQ号
     */
    @Column(name = "qq_number")
    private String qqNumber;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * IP地址，安全验证时使用
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 提交时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private String salt;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码MD5 32位小写加密
     *
     * @return password - 密码MD5 32位小写加密
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码MD5 32位小写加密
     *
     * @param password 密码MD5 32位小写加密
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取第三方登录ID
     *
     * @return unionID - 第三方登录ID
     */
    public Integer getUnionid() {
        return unionid;
    }

    /**
     * 设置第三方登录ID
     *
     * @param unionid 第三方登录ID
     */
    public void setUnionid(Integer unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取房卡数量
     *
     * @return card_num - 房卡数量
     */
    public Integer getCardNum() {
        return cardNum;
    }

    /**
     * 设置房卡数量
     *
     * @param cardNum 房卡数量
     */
    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取常用地址（记录市级，安全验证时使用）
     *
     * @return commen_address - 常用地址（记录市级，安全验证时使用）
     */
    public String getCommenAddress() {
        return commenAddress;
    }

    /**
     * 设置常用地址（记录市级，安全验证时使用）
     *
     * @param commenAddress 常用地址（记录市级，安全验证时使用）
     */
    public void setCommenAddress(String commenAddress) {
        this.commenAddress = commenAddress;
    }

    /**
     * 获取微信号
     *
     * @return wx_number - 微信号
     */
    public String getWxNumber() {
        return wxNumber;
    }

    /**
     * 设置微信号
     *
     * @param wxNumber 微信号
     */
    public void setWxNumber(String wxNumber) {
        this.wxNumber = wxNumber;
    }

    /**
     * 获取QQ号
     *
     * @return qq_number - QQ号
     */
    public String getQqNumber() {
        return qqNumber;
    }

    /**
     * 设置QQ号
     *
     * @param qqNumber QQ号
     */
    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取IP地址，安全验证时使用
     *
     * @return ip_address - IP地址，安全验证时使用
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置IP地址，安全验证时使用
     *
     * @param ipAddress IP地址，安全验证时使用
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取提交时间
     *
     * @return create_time - 提交时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置提交时间
     *
     * @param createTime 提交时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSalt() {return salt;}

    public void setSalt(String salt) {this.salt = salt;}
}