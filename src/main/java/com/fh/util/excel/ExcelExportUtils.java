package com.fh.util.excel;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Lenovo
 */
public class ExcelExportUtils {


    public static void excelExport(List<?> data, Class c, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取到excelbean对象
        ExcelBean bean = ResolverObject.getObjectFiled(c);
        //创建工作表
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建sheet页
        XSSFSheet sheet = workbook.createSheet();
        //创建标题行
        XSSFRow titleRow = sheet.createRow(0);
        //创建标题单元格
        XSSFCell titleCell = titleRow.createCell(0);
        //给标题单元格赋值
        titleCell.setCellValue(bean.getTitleName());
        //给标题单元格赋值样式
        titleCell.setCellStyle(PoiCellStyle.titleStyle(workbook));
        //给标题单元格合并
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0,0,0,bean.getHeaders().size()-1);
        sheet.addMergedRegion(cellRangeAddress);
        //创建头部行
        XSSFRow headerRow = sheet.createRow(1);
        //遍历头部的集合
        for (int i = 0; i < bean.getHeaders().size(); i++) {
            //创建头部的单元格
            XSSFCell headerCell = headerRow.createCell(i);
            //给头部单元格赋值
            headerCell.setCellValue(bean.getHeaders().get(i));
            //给头部单元格添加样式
            headerCell.setCellStyle(PoiCellStyle.colunmStyle(workbook));
        }
        //遍历参数的list集合
        for (int i = 0; i < data.size(); i++) {
            //创建数据的行
            XSSFRow dataRow = sheet.createRow(i + 2);
            //获取到对象
            Object obj = data.get(i);
            //遍历所获取的属性名
            for (int j = 0; j < bean.getFileds().size(); j++) {
                //创建数据的单元格
                XSSFCell dataCell = dataRow.createCell(j);
                //获取单个属性名
                String filedName = bean.getFileds().get(j);
                //获取到拼接以后的get方法的名称
                String fieldMethod = getFieldMethod(filedName);
                //获取到根据方法名获取的方法
                Method method = c.getMethod(fieldMethod);
                //调用对象里面的方法
                Object invoke = method.invoke(obj);
                //判断属性
                if (invoke instanceof Date){
                    dataCell.setCellValue(DateFormatUtils.format((Date) invoke,"yyyy-MM-dd"));
                }else if (invoke instanceof Integer){
                    dataCell.setCellValue((Integer)invoke);
                }else if (invoke instanceof Double){
                    dataCell.setCellValue((Double)invoke);
                }else if (invoke instanceof Long){
                    dataCell.setCellValue((Long)invoke);
                }else if (invoke instanceof Short){
                    dataCell.setCellValue((Short)invoke);
                }else if (invoke instanceof Float){
                    dataCell.setCellValue((Float)invoke);
                }else{
                    if (invoke != null){
                        dataCell.setCellValue(String.valueOf(invoke));
                    }else {
                        dataCell.setCellValue("");
                    }
                }
                //给单元格添加style样式
                dataCell.setCellStyle(PoiCellStyle.dataStyle(workbook));
            }
        }

        for(int i=0;i<bean.getFileds().size();i++){
            sheet.autoSizeColumn((short) i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        //使用response进行导出
        response.setContentType("application/octet-stream");
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+UUID.randomUUID().toString() +".xlsx");
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //拼接get方法
    public static  String getFieldMethod(String fieldName){
        return "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
}
