package com.springboot.springbootlogindemo.service;

import com.springboot.springbootlogindemo.domain.Caller;
public interface CallerService {
    /**
     * 登录业务逻辑
     * @param uname 账户名
     * @param text 密码
     * @return
     */
    Caller loginService(String uname, String text);

    /**
     * 注册业务逻辑
     * @param caller 要注册的Caller对象，属性中主键uid要为空，若uid不为空可能会覆盖已存在的caller
     * @return
     */
    Caller registService(Caller caller);
}
