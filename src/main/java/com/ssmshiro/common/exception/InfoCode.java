package com.ssmshiro.common.exception;

/**
 * 系统错误码，规范系统异常的错误码
 */
public enum InfoCode {
    ESYS0001,//会话失效，请重新登陆
    ESYS0002,//Token不能为空
    ESYS0003,//您无权进行该操作
    ISYS0004,//用户登陆成功
    ESYS0004,//用户登陆失败
    ESYS0005,//账号不存在
    ESYS0006,//密码不正确
    ISYS0007,//注册成功
    ESYS0007,//注册失败
    ISYS0008,//退出成功
    ESYS0008,//退出失败
    ESYS0009,//该账户已被注册
    ESYS9999,//系统内部异常

    IMSG0001,//保存信息成功
    EMSG0001,//保存信息失败
    IMSG0002,//删除信息成功
    EMSG0002,//删除信息失败
    IMSG0003,//修改信息成功
    EMSG0003,//修改信息失败
    IMSG0004,//查询信息成功
    EMSG0004,//查询信息失败
    IMSG0005,//分页查询信息成功
    EMSG0005,//分页查询信息失败

}
