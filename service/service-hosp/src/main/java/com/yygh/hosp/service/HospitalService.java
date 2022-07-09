package com.yygh.hosp.service;

import com.yygh.model.hosp.Hospital;
import com.yygh.vo.hosp.HospitalQueryVo;
import com.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created on 2022/7/2.
 *
 * @author Ann Zhou
 */

public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHospPage(int page, int limit, HospitalQueryVo hospitalQueryVo);

    void updateStatus(String id, Integer status);

    Map<String, Object> show(String id);

    String getHospName(String hoscode);

    //根据医院名称获取医院列表
    List<Hospital> findByHosname(String hosname);

    Map<String, Object> item(String hoscode);
}
