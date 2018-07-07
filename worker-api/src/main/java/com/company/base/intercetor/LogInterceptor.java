package com.company.base.intercetor;

import io.swagger.annotations.ApiOperation;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.base.Constants;
import com.company.base.config.WebCxt;
import com.company.entity.LogOpDTO;
import com.company.service.LogOpService;
import com.comtom.bc.common.annotation.NoRecordLog;
import com.comtom.bc.common.utils.CommonUtil;

/**
 * 用户操作日志拦截器<br>
 * 提供访问接口操作日志记录
 * 
 * @author chenxin
 * @date 2017年3月27日 上午9:27:10
 */
@Component
@Aspect
public class LogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private LogOpService logOpService;

	/**
	 * 请求前拦截逻辑处理
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

	/**
	 * 请求完成，记录用户请求操作日志
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		String loginUserId = (String) request.getAttribute(Constants.LOGIN_USER_ID);
		ApiOperation operation = null;
		NoRecordLog noRecordLog = null;

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			operation = method.getAnnotation(ApiOperation.class);
			noRecordLog = method.getAnnotation(NoRecordLog.class);
		}

		// 需要记录日志
		if (noRecordLog == null) {
			if (operation != null) {

				// 获取API注解设置参数
				String value = operation.value();
				String notes = operation.notes();

				// 获取接口设置日志内容和请求参数
				String opMode = (String) request.getAttribute(Constants.LOG_OP_MODE);
				String opType = (String) request.getAttribute(Constants.LOG_OP_TYPE);
				String remark = (String) request.getAttribute(Constants.LOG_REMARK);
				
				LogOpDTO logUser = new LogOpDTO();
				logUser.setUserId(loginUserId);
				logUser.setOpMode(opMode);
				logUser.setOpType(opType);
				logUser.setOpTime(new Date());
				logUser.setIp(WebCxt.getClientIpAddr(request));
				logUser.setStatus("1");
				logUser.setRemark(remark);

				if (CommonUtil.isNotEmpty(loginUserId)) {
					// 记录用户操作日志
					//logOpService.add(logUser);
				}
			}
		}

		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 请求完成，记录用户请求操作日志
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}
}