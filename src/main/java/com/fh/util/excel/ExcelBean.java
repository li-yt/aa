package com.fh.util.excel;

import lombok.Data;

import java.util.List;

/**
 * @author Lenovo
 */
@Data
public class ExcelBean {

    private String titleName;

    private List<String> headers;

    private List<String> fileds;

}
