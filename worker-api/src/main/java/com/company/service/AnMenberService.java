package com.company.service;

import com.company.entity.AnMenberDTO;

/**
 * 会员-业务逻辑处理接口定义
 * 
 * @author chenxin
 * @date 2017年3月27日 上午9:33:27
 */
public interface AnMenberService {


	/**
	 * 根据会员帐号获取用户信息
	 * @param loginName
	 * @return
	 */
	public AnMenberDTO findByUserName(String loginName);
	
	/**
	 * 新增会员
	 * 
	 * @param anMenberDto
	 * @return
	 */
	public AnMenberDTO add(AnMenberDTO anMenberDto);
	
	/**
	 * 根据会员ID查询会员信息
	 * 
	 * @param menberId
	 * @return
	 */
	public AnMenberDTO findById(String menberId);
	
	/**
	 * 生成新密码并下发邮件
	 * 
	 * @param toEmail
	 * @return
	 * @throws Exception
	 */
	public String sendEmail(String toEmail) throws Exception;

}