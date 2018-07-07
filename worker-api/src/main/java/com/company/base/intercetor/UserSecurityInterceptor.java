package com.company.base.intercetor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.base.Constants;
import com.company.base.SessionUser;
import com.comtom.bc.common.result.JsonResult;
import com.comtom.bc.common.result.ResultCode;
import com.comtom.bc.common.result.ResultMsg;
import com.comtom.bc.common.utils.CommonUtil;
import com.comtom.bc.common.utils.JsonUtil;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 提供用户安全验证拦截处理
 * 
 * @author chenxin
 * @date 2017年3月27日 上午9:25:58
 */
@Component
@Aspect
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	private RedisUtil redisUtil;   springboot框架自带Redis

	private static ICache cache = CacheFactory.newCache();
//	@Autowired
//	private LogUserService logUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		response.setCharacterEncoding(Constants.CHARACTER_ENCODING);
		response.setContentType(Constants.CONTENT_TYPE);
		
		// 响应对象
		JsonResult<Object, Object> result = new JsonResult<Object, Object>();

		String jsessionid = request.getParameter(Constants.JSESSIONID);

		// 如果调用方没有传入鉴权session，提示没有权限
		if (CommonUtil.isEmpty(jsessionid)) {
			PrintWriter out = response.getWriter();
			result.setResultCode(ResultCode.AUTH_FAILURE);
			result.setResultMsg(ResultMsg.AUTH_FAILURE);
			out.append(JsonUtil.toJson(result));
			return false;
		}

		// 从请求参数中获取接口调用的jsessionid
		try {
			//SessionUser user = (SessionUser) redisUtil.get(jsessionid);  springboot框架自带Redis
			
			
			
//			java.lang.NullPointerException: null
//			at java.io.ByteArrayInputStream.<init>(ByteArrayInputStream.java:106)
//			at com.sinovatech.hd.tools.uitl.SerializeUtil.unserialize(SerializeUtil.java:88)
//			at com.sinovatech.hd.tools.cache.redis.JedisClient.get(JedisClient.java:309)
			if(cache.keyExists(jsessionid)){
				SessionUser user = (SessionUser)cache.get(jsessionid);//jsessionid错误会抛NullPointerException，此处不做处理
				if (user == null) {
					PrintWriter out = response.getWriter();
					result.setResultCode(ResultCode.TIMEOUT);
					result.setResultMsg(ResultMsg.SESSION_TIMEOUT);
					out.append(JsonUtil.toJson(result));
					return false;
				} else {
					request.setAttribute(Constants.LOGIN_USER_ID, user.getId());
				}
			}else{
				PrintWriter out = response.getWriter();
				result.setResultCode(ResultCode.TIMEOUT);
				result.setResultMsg(ResultMsg.SESSION_TIMEOUT);
				out.append(JsonUtil.toJson(result));
				return false;
			}
			

		} catch (RuntimeException e) {
			e.printStackTrace();

			PrintWriter out = response.getWriter();
			result.setResultCode(ResultCode.SESSION_FAILURE);
			result.setResultMsg(ResultMsg.SESSION_FAILURE);
			out.append(JsonUtil.toJson(result));
			return false;
		} 

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
	}

}
