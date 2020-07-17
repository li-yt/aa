package com.fh.shiro.bean.user;

import lombok.Data;

@Data
public class PageBean extends UserVo {

    private Long count;

    private Long page;

    private Long limit;

    private Integer code;

    private String msg;

    private Object data;

    public PageBean(Object data, Long count){
        this.code=0;
        this.msg= "查询成功";
        this.data=data;
        this.count = count;
    }

}
