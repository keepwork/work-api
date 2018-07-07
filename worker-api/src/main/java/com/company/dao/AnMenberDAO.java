package com.company.dao;

import com.company.base.support.ApiRepository;
import com.company.entity.AnMenberDTO;

/**
 * 会员 - 数据访问层接口定义
 * 
 * @author chenxin
 * @date 2017年3月27日 上午11:38:45
 */
public interface AnMenberDAO extends ApiRepository<AnMenberDTO, Long> {
	
	public AnMenberDTO findByUserName(String f_username);
	
	public AnMenberDTO findByIndex(String menberId);
}
