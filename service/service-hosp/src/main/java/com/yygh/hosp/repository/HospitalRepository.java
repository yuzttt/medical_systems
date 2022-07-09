package com.yygh.hosp.repository;

import com.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2022/7/2.
 *
 * @author Ann Zhou
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    Hospital getHospitalByHoscode(String hoscode);

    List<Hospital> findHospitalByHosnameLike(String hosname);
}
