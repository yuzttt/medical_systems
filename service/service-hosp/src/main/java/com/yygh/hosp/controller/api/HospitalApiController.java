package com.yygh.hosp.controller.api;

import com.yygh.common.result.Result;
import com.yygh.hosp.service.DepartmentService;
import com.yygh.hosp.service.HospitalService;
import com.yygh.model.hosp.Hospital;
import com.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created on 2022/7/8.
 *
 * @author Ann Zhou
 */
@RestController
@RequestMapping("api/hosp/hospital")
public class HospitalApiController {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value="查询医院列表")
    @GetMapping("findHospitalList/{page}/limit}")
    public Result findHospitalList(@PathVariable int page,
                                   @PathVariable int limit,
                                   HospitalQueryVo hospitalQueryVo){

        Page<Hospital> hospitals = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitals);

    }

    @ApiOperation(value = "根据医院名称获取医院列表")
    @GetMapping("findByHosname/{hosname}")
    public Result findByHosname(
            @ApiParam(name = "hosname", value = "医院名称", required = true)
            @PathVariable String hosname) {
       List<Hospital> list =hospitalService.findByHosname(hosname);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取科室列表")
    @GetMapping("department/{hoscode}")
    public Result index(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        return Result.ok(departmentService.findDeptTree(hoscode));
    }

    @ApiOperation(value = "医院预约挂号详情")
    @GetMapping("findHospDetail/{hoscode}")
    public Result item(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        Map<String, Object> map= hospitalService.item(hoscode);
        return Result.ok(map);
    }

}
