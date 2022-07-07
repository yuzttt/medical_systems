package com.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yygh.cmn.client.DictFeignClient;
import com.yygh.hosp.repository.HospitalRepository;
import com.yygh.hosp.service.HospitalService;
import com.yygh.model.hosp.Department;
import com.yygh.model.hosp.Hospital;
import com.yygh.vo.hosp.HospitalQueryVo;
import com.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created on 2022/7/2.
 *
 * @author Ann Zhou
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DictFeignClient dictFeignClient;
    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数map集合转换对象Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap),Hospital.class);

        //判断是否存在
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        //修改
        if(null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //添加
//0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Page<Hospital> selectHospPage(int page, int limit, HospitalQueryVo hospitalQueryVo) {
        Pageable pageable= PageRequest.of(page-1,limit);

        ExampleMatcher matcher=ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Hospital hospital=new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo,hospital);

        Example<Hospital> example = Example.of(hospital,matcher);

        Page<Hospital> all = hospitalRepository.findAll(example, pageable);
        //缺少等级
        all.getContent().stream().forEach(item->{
            this.setHospitalHospType(item);
        });
        //
        return all;
    }


    private Hospital setHospitalHospType(Hospital item) {
        String hostype = dictFeignClient.getName("hostype", item.getHostype());
        String province = dictFeignClient.getName(item.getProvinceCode());
        String city=dictFeignClient.getName(item.getCityCode());
        String district = dictFeignClient.getName(item.getDistrictCode());

        item.getParam().put("fullAdress",province+city+district);
        item.getParam().put("hostypeString",hostype);
        return item;
    }

    @Override
    public void updateStatus(String id, Integer status) {
        Hospital hospital = hospitalRepository.findById(id).get();
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);

    }

    @Override
    public Map<String, Object> show(String id) {
        Map<String, Object> result = new HashMap<>();
        Hospital hosp = hospitalRepository.findById(id).get();
        Hospital hospital = this.setHospitalHospType(hosp);
        result.put("hospital", hospital);

//单独处理更直观
        result.put("bookingRule", hospital.getBookingRule());
//不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }

    //获取医院名称
    @Override
    public String getHospName(String hoscode) {
        Hospital hospitalByHoscode = hospitalRepository.getHospitalByHoscode(hoscode);
        if(hospitalByHoscode!=null){
            return hospitalByHoscode.getHosname();
        }
        return null;
    }
}
