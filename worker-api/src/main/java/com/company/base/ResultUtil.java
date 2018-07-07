package com.company.base;

import net.sf.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.comtom.bc.common.result.JsonResult;
import com.comtom.bc.common.utils.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 请求返回结果公用帮助类
 * 
 * @author chenxin(keepwork512@163.com)
 * @date 2017年4月11日 下午4:07:08
 */
public class ResultUtil {

	/**
	 * 返回对象完整json：操作成功(日期格式化 yyyy-MM-dd HH:mm:ss)
	 * @param resultJson
	 * @return
	 */
	public static ResponseEntity<String> GoResponseFullSuccess(JsonResult<String, Object> resultJson){
		resultJson.setResultCode(Constants.SUCCESSFUL);
		resultJson.setResultMsg("操作成功");
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();;
		return new ResponseEntity<String>(gson.toJson(resultJson).replaceAll("null","\"\""), HttpStatus.OK);
	}
	
	/**
	 * 返回对象完整json：操作成功(日期不格式化)
	 * @param resultJson
	 * @return
	 */
	public static ResponseEntity<String> GoFullSuccess(JsonResult<String, Object> resultJson){
		resultJson.setResultCode(Constants.SUCCESSFUL);
		resultJson.setResultMsg("操作成功");
		Gson gson = new GsonBuilder().serializeNulls().create();;
		return new ResponseEntity<String>(gson.toJson(resultJson).replaceAll("null","\"\""), HttpStatus.OK);
	}
	
	/**
	 * 返回对象属性无值时json不包含该属性：操作成功(日期格式化 yyyy-MM-dd HH:mm:ss)
	 * @param resultJson
	 * @return
	 */
	public static ResponseEntity<String> GoResponseSuccess(JsonResult<String, Object> resultJson){
		resultJson.setResultCode(Constants.SUCCESSFUL);
		resultJson.setResultMsg("操作成功");
		return new ResponseEntity<String>(JsonUtil.toJson(resultJson,""), HttpStatus.OK);
	}
	
	/**
	 * 返回对象属性无值时json不包含该属性：操作成功(日期不格式化)
	 * @param resultJson
	 * @return
	 */
	public static ResponseEntity<String> GoSuccess(JsonResult<String, Object> resultJson){
		resultJson.setResultCode(Constants.SUCCESSFUL);
		resultJson.setResultMsg("操作成功");
		return new ResponseEntity<String>(JsonUtil.toJson(resultJson), HttpStatus.OK);
	}
	
	/**
	 * 返回json：操作失败
	 * @param resultJson
	 * @return
	 */
	public static ResponseEntity<String> GoResponseFailure(JsonResult<String, Object> resultJson){
		resultJson.setResultCode(Constants.FAILURE);
		resultJson.setResultMsg("操作失败");
		return new ResponseEntity<String>(JsonUtil.toJson(resultJson), HttpStatus.OK);
	}
	
	/**
	 * 返回json：参数验证失败
	 * 
	 * @param resultJson
	 * @param message
	 * @return
	 */
	public static ResponseEntity<String> GoResponseValidateError(JsonResult<String, Object> resultJson,String message){
		resultJson.setResultCode(Constants.VALIDATE_ERROR);
		resultJson.setResultMsg(message);
		return new ResponseEntity<String>(JsonUtil.toJson(resultJson), HttpStatus.OK);
	}
	
	
	public static ResponseEntity<String> GoResponse(String result){
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity<String> GoResponseJson(JSONArray resultJson){
		return new ResponseEntity<String>(JsonUtil.toJson(resultJson), HttpStatus.OK);
	}
   
}
