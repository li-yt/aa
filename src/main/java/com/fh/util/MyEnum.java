package com.fh.util;

/**
 * @author Y7000
 * 自己的枚举类
 */

public enum MyEnum {

  SUCCESS(200,"成功"),
  FAILURE(201,"失败"),
  ERROR(202,"错误"),
  LOGIN_SUCCESS(2000,"登录成功"),
  LOGIN_STATUS(3002,"该账户已失效"),
  LOGIN_FAILURE(3000,"登录失败，账号/密码不正确"),
  LOGIN_USERNAME_NOTFIND(3001,"登录失败，用户名不存在");

  //私有化属性
  private Integer code;

  private String msg;

  //get方法

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  //私有化有参构造

  private MyEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  //公共静态获取枚举对象的方法

  public static MyEnum getMyEnumByCode(Integer code){
      for(MyEnum e: MyEnum.values()){
          if(e.getCode().equals(code)){
              return e;
          }
      }
      return null;
  }

  //测试枚举 判断
  public static void main(String[] args) {
    Integer code =3000;
    MyEnum myEnum = MyEnum.getMyEnumByCode(code);
    if(myEnum!=null){
      switch (myEnum){
        case SUCCESS:
          System.out.println(myEnum.getMsg());
          break;
        case FAILURE:
          System.out.println(myEnum.getMsg());
          break;
          case ERROR:
          System.out.println(myEnum.getMsg());
          break;
          case LOGIN_SUCCESS:
          System.out.println(myEnum.getMsg());
          break;
          case LOGIN_FAILURE:
          System.out.println(myEnum.getMsg());
          break;
          case LOGIN_USERNAME_NOTFIND:
          System.out.println(myEnum.getMsg());
          break;
        default:
          break;
      }
    }else{
      System.out.println("不存在该code对应的信息");
    }
  }


}
