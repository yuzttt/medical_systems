package com.yygh.hosp.service;

import com.yygh.model.hosp.Department;
import com.yygh.vo.hosp.DepartmentQueryVo;
import com.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created on 2022/7/2.
 *
 * @author Ann Zhou
 */
public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);
    //查询科室接口
    Page<Department> selectPage(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);
    //删除科室接口
    void remove(String hoscode, String depcode);
    //根据医院编号查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);
    //根据科室编号和医院编号，查询科室名称
    String getDepName(String hoscode, String depcode);
}
