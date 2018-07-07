package com.company.base;

import java.io.Serializable;


/**
 * 保存登录后的用户信息（会员或家庭成员）
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月11日 下午2:26:39
 */
public class SessionUser implements Serializable
{ 
	private String id;

	private String loginName;

	private String realName;

	private String mobile;

	private String email;
	
	//登录用户类型（1：会员，2家庭成员）
	private String type;
	
	//用户登录后的凭证
	private String jsessionid;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}
	
	
}