package com.yygh.hosp.controller;

import com.yygh.common.result.Result;
import com.yygh.hosp.service.HospitalService;
import com.yygh.model.hosp.Hospital;
import com.yygh.vo.hosp.HospitalQueryVo;
import com.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created on 2022/7/3.
 *
 * @author Ann Zhou
 */
@RestController
@RequestMapping("/admin/hosp/hospital")

public class HospitalController {
@Autowired
    private HospitalService hospitalService;

//医院列表
    @GetMapping("list/{page}/{limit}")
    public Result listHosp (@PathVariable int page,
                            @PathVariable int limit,
                            HospitalQueryVo hospitalQueryVo){
        Page<Hospital> pageModel =hospitalService.selectHospPage(page,limit,hospitalQueryVo);
        return Result.ok(pageModel);

    }

    @ApiOperation(value = "更新上线状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result lock(@PathVariable String id,@PathVariable Integer status){
        hospitalService.updateStatus(id,status);
        return Result.ok();
    }

    @ApiOperation(value = "获取医院详情")
    @GetMapping("showHospitalDetail/{id}")
    public Result show(@PathVariable String id) {
        Map<String, Object> map = hospitalService.show(id);
        return Result.ok(map);
    }
}
