package com.example.jfinaldemo2.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/27
 * jfinal 校验器
 */
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateRequiredString("name", "nameMsg", "请输入用户名");
        validateRequiredString("password", "passMsg", "请输入密码");
    }

    /**
     * 在校验有误时才会调用
     * @param c
     */
    @Override
    protected void handleError(Controller c) {
        c.keepPara("name");
        c.renderError(404);
//        c.render("login.html");
    }
}
