package com.gs.utils;

import com.gs.constant.enums.CodeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: sunhl
 * @Description:
 * @Date: Created in 14:04 2020/9/1
 * @Modified By:
 */
@Data
@Builder
@AllArgsConstructor
public class R {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public R() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static R success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static R success(Object data) {
        R rb = new R();
        rb.setCode(CodeEnum.IS_SUCCESS.getCode());
        rb.setMessage("成功");
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static R error(String code, String message) {
        R rb = new R();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static R error(String message) {
        R rb = new R();
        rb.setCode(CodeEnum.IS_FAIL.getCode());
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }}
