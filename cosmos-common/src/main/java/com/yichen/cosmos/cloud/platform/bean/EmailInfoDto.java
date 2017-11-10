package com.yichen.cosmos.cloud.platform.bean;

import java.io.Serializable;

/***
 * @author hzxab
 * @ClassName EmailInfoDto
 * @Description: 机构方 email邮件实体类
 * @date 2016年7月20日 下午3:05:14
 */
public class EmailInfoDto implements Serializable {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -1767263945763549940L;

    // 收件人邮箱地址
    private String toAddress;
    // 登陆邮件发送服务器的用户名
    private String userName;
    // 登陆邮件发送服务器的密码
    private String password;
    // 是否需要身份验证
    private boolean validate = true;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件附件的文件名 暂时用不到
    private String[] attachFileNames;
    // 多个发送地址
    private String[] multiAddress;

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public String[] getMultiAddress() {
        return multiAddress;
    }

    public void setMultiAddress(String[] multiAddress) {
        this.multiAddress = multiAddress;
    }

}
