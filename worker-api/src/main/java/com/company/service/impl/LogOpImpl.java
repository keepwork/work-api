package com.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.dao.LogOpDAO;
import com.company.entity.LogOpDTO;
import com.company.service.LogOpService;
import com.company.service.base.BaseService;

/**
 * 操作日志-业务逻辑处理
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月10日 下午3:40:45
 */
@Service("LogOpService")
public class LogOpImpl extends BaseService implements LogOpService {

	@Autowired
	private LogOpDAO logOpDAO;
	
	public LogOpDTO add(LogOpDTO dto) {
		return logOpDAO.save(dto);
	}

}
