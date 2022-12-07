package com.domain.models.repos;

import com.domain.models.entities.Result;
import com.domain.models.entities.Sample;

import java.util.List;

public interface SampleRepo {

    int save(Sample sample);

    int update(Sample sample);

    Sample findById(Long id);

    int deleteById(Long id);

    List<Sample> findAll();

    List<Sample> findByMsisdn(String msisdn);

    List<Sample> findByCustomer_idContaining(String customer_id);

    int deleteAll();

    //result
    List<Result> findAllResult();

}
