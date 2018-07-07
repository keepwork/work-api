package com.company.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 后台操作日志[SYS_LOG_OP]数据持久化对象
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月10日 下午3:11:52
 */
@Entity
@Table(name = "sys_log_op")
public class LogOpDTO {

	// 主键id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 操作人ID
	@Column(name = "USER_ID")
	private String userId;

	// 操作模块
	@Column(name = "OP_MODE")
	private String opMode;
	
	// 操作类型
	@Column(name = "OP_TYPE")
	private String opType;
	
	// 操作时间
	@Column(name = "OP_TIME")
	private Date opTime;

	// 操作人ip
	@Column(name = "IP")
	private String ip;
	
	// 操作结果：1成功，0失败
	@Column(name = "STATUS")
	private String status;

	// 备注
	@Column(name = "REMARK")
	private String remark;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpMode() {
		return opMode;
	}

	public void setOpMode(String opMode) {
		this.opMode = opMode;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}