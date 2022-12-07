package com.domain.models.repos;

import com.domain.models.entities.Result;
import com.domain.models.entities.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSampleRepo implements SampleRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Sample sample) {
        return jdbcTemplate.update("INSERT INTO history (customer_id, msisdn, value) VALUES (?,?,?)",
                new Object[] { sample.getCustomer_id(), sample.getMsisdn(), sample.getValue() }
                );
    }

    @Override
    public int update(Sample sample) {
        return jdbcTemplate.update("UPDATE history SET username=?, email=?, address=? WHERE id=?",
                new Object[] { sample.getCustomer_id(), sample.getMsisdn(), sample.getValue() });
    }

    @Override
    public Sample findById(Long id) {
        try {
            Sample sample = jdbcTemplate.queryForObject("SELECT * FROM history WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Sample.class), id);

            return sample;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM history WHERE id=?", id);
    }

    @Override
    public List<Sample> findAll() {
        return jdbcTemplate.query("SELECT * from history", BeanPropertyRowMapper.newInstance(Sample.class));
    }

    @Override
    public List<Sample> findByMsisdn(String msisdn) {
        return jdbcTemplate.query("SELECT * from history WHERE msisdn=?",
                BeanPropertyRowMapper.newInstance(Sample.class), msisdn);
    }

    @Override
    public List<Sample> findByCustomer_idContaining(String customer_id) {
        String q = "SELECT * from history WHERE customer_id LIKE '%" + customer_id + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Sample.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from history");
    }

    //result
    @Override
    public List<Result> findAllResult() {
        return jdbcTemplate.query("SELECT profile.customer_id, profile.msisdn, loan_payment_history.value FROM profile RIGHT JOIN loan_payment_history ON profile.customer_id=loan_payment_history.customer_id",
                BeanPropertyRowMapper.newInstance(Result.class));
    }






}
