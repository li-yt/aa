package com.fh.util.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lenovo
 */
public class ResolverObject {

    public static ExcelBean getObjectFiled(Class c){
        //创建ExcelBean对象
        ExcelBean excelBean = new ExcelBean();
        //获取到类上的注解
        ExcelAnnotation excelAnnotation = (ExcelAnnotation) c.getAnnotation(ExcelAnnotation.class);
        //判断excelAnnotation是否为空
        if (excelAnnotation != null){
            String title = excelAnnotation.title();
            excelBean.setTitleName(title);
    }
        //创建用来存头和方法名的list集合
        List<String> headers = new ArrayList<>();
        List<String> fileds = new ArrayList<>();
        //获取到标注在方法上的注解
        Field[] declaredFields = c.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ExcelAnnotation fieldAnnotation = declaredField.getAnnotation(ExcelAnnotation.class);
            if (fieldAnnotation != null){
                headers.add(fieldAnnotation.header());
                fileds.add(declaredField.getName());
            }
        }
        excelBean.setFileds(fileds);
        excelBean.setHeaders(headers);
        return excelBean;
    }


}
