package com.company.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 机器人会员表[T_USER_LIST]数据持久化对象
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月11日 上午11:04:52
 */
@Entity
@Table(name = "t_user_list")
public class AnMenberDTO
{ 
	// 主键
	@Id
	@Column(name = "F_INDEX")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String index;

	// 登录名
	@Column(name = "F_USERNAME")
	private String userName;

	// 密码
	@Column(name = "F_PASSWORD")
	private String password;

	// 中文名
	@Column(name = "F_OWNER_NAME")
	private String ownerName;

	@Column(name = "F_OWNER_ID")
	private String ownerId;

	// 联系电话
	@Column(name = "F_PHONE_NO")
	private String phoneNo;

	@Column(name = "F_CREATE_TIME")
	private Date createTime;
	
	// 0正常，1已删除
	@Column(name = "F_IS_DELETE")
	private Integer isDelete;
	
	// 邮箱
	@Column(name = "F_EMAIL")
	private String email;
	
	// 百度唯一推送标识
	@Column(name = "MSG_CHANNEL_ID")
	private String channelId;
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public AnMenberDTO()
	{

	}

	public AnMenberDTO(String index)
	{
		this.setIndex(index);
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}