package com.company.req;

/**
 * 用户登录参数对象
 * 
 * @author chenxin
 * @date 2017年3月27日 上午11:42:48
 */
public class UserLoginReq{
	
	// 登录名
	private String loginName;
	
	// 登录密码
	private String loginPassword;
	
	// 登录类型（1：会员，2：家庭成员）
	private String type;

	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
