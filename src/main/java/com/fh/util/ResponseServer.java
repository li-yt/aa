package com.fh.util;


/**
 * @author Y7000
 * 统一的返回参数处理
 */
public class ResponseServer {

  private Integer code;

  private String msg;

  private Object data;


  /**
   * 请求处理成功且不需要返回数据和消息时使用的工具方法
   * @return
   */
  public static ResponseServer successWithoutData() {
    return new ResponseServer(MyEnum.SUCCESS.getCode(), MyEnum.SUCCESS.getMsg(),null);
  }
  /**
   * 请求处理成功且不需要返回数据时使用的工具方法
   * @return
   */
  public static ResponseServer successWithData(Object data) {
    return new ResponseServer(MyEnum.SUCCESS.getCode(), MyEnum.SUCCESS.getMsg(),data);
  }


  /**
   * 请求处理失败后使用的工具方法
   *
   * @return
   */
  public static ResponseServer failedWithoutData(){
    return new ResponseServer(MyEnum.FAILURE.getCode(), MyEnum.FAILURE.getMsg(),null);
  }
  /**
   * 请求处理失败后扔需要传递参数使用的工具方法
   *
   * @return
   */
  public static ResponseServer failedWithData(Object data){
    return new ResponseServer(MyEnum.FAILURE.getCode(), MyEnum.FAILURE.getMsg(),data);
  }


  public ResponseServer() {
  }

  public ResponseServer(Integer code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
