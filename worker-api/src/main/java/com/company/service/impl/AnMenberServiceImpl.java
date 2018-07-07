package com.company.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dao.AnMenberDAO;
import com.company.entity.AnMenberDTO;
import com.company.service.AnMenberService;
import com.company.service.base.BaseService;
import com.util.GlobalConfig;
import com.util.MD5;
import com.util.MailUtil;

/**
 * 会员-业务逻辑处理
 * 
 * @author chenxin
 * @date 2017年3月27日 上午11:43:12
 */
@Service("AnMenberService")
public class AnMenberServiceImpl extends BaseService implements AnMenberService {

	@Autowired
	private AnMenberDAO anMenberDAO;


	/**
	 * 根据系统用户帐号获取用户信息
	 * 
	 * @return
	 */
	public AnMenberDTO findByUserName(String loginName) {
		return anMenberDAO.findByUserName(loginName);
	}

	/**
	 * 新增会员
	 * 
	 * @param anMenberDto
	 * @return
	 */
	public AnMenberDTO add(AnMenberDTO anMenberDto) {
		return anMenberDAO.save(anMenberDto);
	}

	/**
	 * 新增会员
	 *
	 * @param menberId
	 * @return
	 */
	public AnMenberDTO findById(String menberId) {
		return anMenberDAO.findByIndex(menberId);
	}

	private void copyProperties(AnMenberDTO target, AnMenberDTO source) {
		target.setEmail(source.getEmail());
		target.setPhoneNo(source.getPhoneNo());
		target.setUserName(source.getUserName());
		target.setOwnerName(source.getOwnerName());
	}
	
	/**
	 * 生成新密码并下发给用户邮箱
	 * 
	 * @param toEmail
	 * @return
	 * @throws Exception
	 */
	public String sendEmail(String toEmail) throws Exception{
		String smtp = GlobalConfig.getProperty("mail.server.ip");//服务器邮箱smtp
		String user = GlobalConfig.getProperty("mail.username");//服务器邮箱账号
		String password = GlobalConfig.getProperty("mail.password");//服务器邮箱密码
		String subject = GlobalConfig.getProperty("mail.subject");//服务器下发邮件主题
		String content = GlobalConfig.getProperty("mail.content");//服务器下发邮件内容
		
		MailUtil mail = new MailUtil();
		mail.setUser(user);
		mail.setPassword(password);
		mail.setSmtp(smtp);
		//生成6位随机数
		int pwd = (int) ((Math.random() * 9 + 1) * 100000);
		content = content.replace("#password#", pwd+"");
		//更新密码
		String newPwd = MD5.GetMD5Code(pwd+"");
		//String newPwd = Des.encrytWithBase64("DESede", password, "123456565643450987657689876543267676787651234567");
		//邮件下发
		mail.connect().sendEmail(user,toEmail, subject, content).disConnect();
		return newPwd;
	}
	
}
