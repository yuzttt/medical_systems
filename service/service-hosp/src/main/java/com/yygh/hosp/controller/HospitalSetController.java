package com.yygh.hosp.controller;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yygh.common.result.Result;
import com.yygh.common.utils.MD5;
import com.yygh.hosp.service.HospitalSetService;
import com.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yygh.model.hosp.HospitalSet;

import java.util.List;
import java.util.Random;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
@Api(tags = "医院设置管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value="获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }



    @ApiOperation(value="逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        Boolean isRemove= hospitalSetService.removeById(id);
        if(isRemove){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value="条件查询带分页")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
       Page<HospitalSet> page=new Page<>(current,limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(hospitalSetQueryVo.getHosname())){
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode())) {
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospitalSet);
    }



    @PostMapping("saveHospitalSet")
    @ApiOperation(value = "添加医院设置")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
       hospitalSet.setStatus(1);

        Random random=new Random();
        String signKey=MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000));
        hospitalSet.setSignKey(signKey);

        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }




    @ApiOperation(value = "通过id获取医院设置")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable long id){
        HospitalSet hospitalSet=hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量删除医院")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<String> idList){
        boolean flag = hospitalSetService.removeByIds(idList);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }


    @ApiOperation(value = "发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }
}
