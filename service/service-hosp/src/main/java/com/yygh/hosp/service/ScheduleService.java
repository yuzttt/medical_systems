package com.yygh.hosp.service;

import com.yygh.model.hosp.Department;
import com.yygh.model.hosp.Schedule;
import com.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created on 2022/7/3.
 *
 * @author Ann Zhou
 */

public interface ScheduleService {


    void save(Map<String, Object> paramMap);

    Page<Schedule> findPage(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
