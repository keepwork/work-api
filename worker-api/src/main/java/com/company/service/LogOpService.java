package com.company.service;

import com.company.entity.LogOpDTO;

/**
 * 操作日志-业务逻辑处理接口定义
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月10日 下午3:40:05
 */
public interface LogOpService {

	public LogOpDTO add(LogOpDTO dto);

}