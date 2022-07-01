package com.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * Created on 2022/6/30.
 *
 * @author Ann Zhou
 */
public class TestRead {
    public static void main(String[] args) {
        String fileName="D:\\yygh_log\\excel\\01.xlsx";
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
