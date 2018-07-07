package com.company.base;

public class Constants {

	public static final String CONTENT_TYPE = "application/json; charset=utf-8";
	
	public static final String CHARACTER_ENCODING = "utf-8";
	
	// sessionId
	public static final String JSESSIONID = "jsessionid";
	// 登录后的用户id
	public static final String LOGIN_USER_ID = "loginUserid";
	
	
	// 操作模块（1功能模块，2部门管理，3用户管理，4角色管理，5数据字典）
	public static final String LOG_OP_MODE = "logOpMode";
	// 操作类型（1查询，2新增，3修改，4删除，5导入，6导出）
	public static final String LOG_OP_TYPE = "logOpType";
	// 备注
	public static final String LOG_REMARK = "logRemark";
	
	
	//
	public static final String LIST_KEY = "list";
	public static final String TOTAL_COUNT_KEY = "total";
	// 返回成功
	public static final int SUCCESSFUL = 0;
    // 无数据
    public static final int NODATA = 1;
    // 超时
    public static final int TIMEOUT = 2;
    // 参数错误
    public static final int VALIDATE_ERROR = 3;
    // 系统异常
    public static final int FAILURE = 9;
	
}
