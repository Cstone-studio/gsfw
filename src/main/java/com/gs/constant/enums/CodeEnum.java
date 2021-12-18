package com.gs.constant.enums;

public enum CodeEnum {

  /**
   * 操作成功
   */
  IS_SUCCESS("10000", "操作成功"),
  /**
   * 操作失败
   */
  IS_FAIL("10001", "操作失败"),
  /**
   * 请求参数格式不正确
   */
  IS_PARAM("10005", "请求参数格式不正确"),
  /**
   * 数据已经存在
   */
  IS_EXIST("10006", "数据已存在"),
  /**
   * 登录已过期
   */
  IS_LOGIN_EXPIRE("10007", "登录已过期"),
  /**
   * 数据不存在
   */
  IS_NOT_EXIST("10007", "数据不存在"),
  /**
   * 系统异常
   */
  IS_EXCEPTION("99999", "系统异常");

  private String code;
  private String value;

  CodeEnum(String code, String value) {
    this.code = code;
    this.value = value;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
