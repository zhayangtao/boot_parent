package com.example.boot_mybatis_plus.config;

import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/23
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public ApiResult<Object> handleBadRequest(Exception e) {
        if (e instanceof ApiException) {
            IErrorCode errorCode = ((ApiException) e).getErrorCode();
            if (errorCode != null) {
                return ApiResult.failed(errorCode);
            }
            return ApiResult.failed(e.getMessage());
        }
        if (e instanceof BindException) {
            BindingResult bindResult = ((BindException) e).getBindingResult();
            if (bindResult.hasErrors()) {
                List<Object> objectList = new ArrayList<>();
                bindResult.getFieldErrors().forEach(fieldError -> {
                    Map<String, Object> jsonObject = new HashMap<>();
                    jsonObject.put("name", fieldError.getField());
                    jsonObject.put("msg", fieldError.getDefaultMessage());
                    objectList.add(jsonObject);
                });
                return ApiResult.restResult(objectList, ApiErrorCode.FAILED);
            }
        }
        return ApiResult.failed(ApiErrorCode.FAILED);
    }
}
