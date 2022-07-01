package com.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/6/30.
 *
 * @author Ann Zhou
 */
public class TestWrite {
    public static void main(String[] args) {
        List<UserData> list = new ArrayList();

        for(int i=0;i<10;i++){
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("lucy"+i);
            list.add(data);
        }

        String fileName="D:\\yygh_log\\excel\\01.xlsx";

        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);
    }
}
