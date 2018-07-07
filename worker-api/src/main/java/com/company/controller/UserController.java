package com.company.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.base.ResultUtil;
import com.company.base.SessionUser;
import com.company.base.config.WebCxt;
import com.company.entity.AnMenberDTO;
import com.company.req.UserLoginReq;
import com.company.service.AnMenberService;
import com.comtom.bc.common.result.JsonResult;
import com.comtom.bc.common.utils.CommonUtil;
import com.comtom.bc.common.utils.GenUUId;
import com.sinovatech.common.util.DateUtil;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;
import com.util.StringUtil;

/**
 * 提供前端登陆鉴权，登陆通过后，返回用户权限信息和jsessionid前端
 *
 * @author chenxin
 * @date 2017年3月27日 上午9:29:33
 */
@Api(value = "用户管理")
@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AnMenberService anMenberService;

//	@Autowired
//	RedisUtil redisUtil;

	private static ICache cache = CacheFactory.newCache();


	/**
	 * 提供客户端登录鉴权
	 *
	 * @return ResponseEntity<String>
	 */
	@ApiOperation(value = "登录退出", notes = "用户登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserLoginReq req) {
		log.debug("用户登录 - UserController.login");
		// 初始化返回结果对象JsonResult
		JsonResult<String, Object> resultJson = new JsonResult<String, Object>();
		Map<String, Object> dataMap = WebCxt.newJsonMap(resultJson);

		String type = req.getType();
		String loginName = req.getLoginName();
		String password = req.getLoginPassword();

		if(StringUtil.isEmpty(type)){
			return ResultUtil.GoResponseValidateError(resultJson,"用户类型不能为空");
		}
		if(!type.equals("1") && !type.equals("2")){
			return ResultUtil.GoResponseValidateError(resultJson,"用户类型只能为1或2");
		}
		if(StringUtil.isEmpty(loginName)){
			return ResultUtil.GoResponseValidateError(resultJson,"登录名不能为空");
		}
		if(StringUtil.isEmpty(password)){
			return ResultUtil.GoResponseValidateError(resultJson,"登录密码不能为空");
		}

		// 登录Session超时时间间隔键值参数(秒)
		//String timeout = "28800"; springboot框架自带Redis

		//超时时间为8小时
		Date timeout = DateUtil.addHours(new Date(), 8);

		// 鉴权通过后设置session到缓存
		String uuid = GenUUId.uuid();

		//password = Des.encrytWithBase64("DESede", password, "123456565643450987657689876543267676787651234567");

		AnMenberDTO menber = anMenberService.findByUserName(loginName);
		// 用户信息存在，并且密码匹配正确
		if (menber != null && menber.getPassword().equals(password)) {
			SessionUser user = new SessionUser();
			user.setId(menber.getIndex());
			user.setLoginName(menber.getUserName());
			user.setMobile(menber.getPhoneNo());
			user.setEmail(menber.getEmail());
			user.setRealName(menber.getOwnerName());
			user.setJsessionid(uuid);
			user.setType("1");
			try {
				// 缓存用户登录信息
				//redisUtil.set(uuid, user, Long.valueOf(timeout));

				cache.set(uuid, user, timeout);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

			menber.setPassword("");
			dataMap.put("menber", menber);
			dataMap.put("jsessionid", uuid);
		}else{
			return ResultUtil.GoResponseValidateError(resultJson,"用户不存在或密码错误");
		}
		resultJson.setDataMap(dataMap);
		return ResultUtil.GoResponseSuccess(resultJson);
	}

	/**
	 * 提供客户端登出
	 *
	 * @param loginName
	 * @param jsessionid
	 * @return
	 */
	@ApiOperation(value = "登录退出", notes = "用户退出")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout(@RequestParam String loginName, @RequestParam String jsessionid) {
		log.debug("用户退出 - UserController.logout");
		// 初始化返回结果对象JsonResult
		JsonResult<String, Object> resultJson = new JsonResult<String, Object>();

		if(StringUtil.isEmpty(loginName)){
			return ResultUtil.GoResponseValidateError(resultJson,"登录名不能为空");
		}

		if (CommonUtil.isEmpty(jsessionid)) {
			return ResultUtil.GoResponseValidateError(resultJson,"jsessionid不能为空");

		} else {
			// 清除缓存用户登录信息
			//redisUtil.remove(jsessionid);

			cache.delete(jsessionid);
			return ResultUtil.GoResponseSuccess(resultJson);
		}
	}
}
