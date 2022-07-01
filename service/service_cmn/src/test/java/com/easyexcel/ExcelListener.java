package com.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * Created on 2022/6/30.
 *
 * @author Ann Zhou
 */
public class ExcelListener extends AnalysisEventListener<UserData> {
    //一行一行读（从第二行开始
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }
   //读表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
