package com.yygh.cmn.controller;


import com.yygh.cmn.service.DictService;
import com.yygh.common.result.Result;
import com.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
@Api(tags = "数据字典管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出数据字典接口")
    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response){
        dictService.exportDictData(response);
    }


    @ApiOperation(value = "导入数据字典")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }

}
