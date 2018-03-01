package com.example.jfinaldemo.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/24
 */
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateRequiredString("name", "nameMsg", "请输入用户名");
        validateRequiredString("password", "passMsg", "请输入密码");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepPara("name");
        c.render("login.html");
    }
}
