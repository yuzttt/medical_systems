package com.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yygh.hosp.mapper.HospitalSetMapper;
import com.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yygh.model.hosp.HospitalSet;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Autowired
    private HospitalSetMapper hospitalSetMapper;

}