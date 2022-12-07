package com.domain.controllers;

import com.domain.models.entities.Result;
import com.domain.models.entities.Sample;
import com.domain.models.repos.SampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SampleController {


    @Autowired
    SampleRepo sampleRepo;

    @GetMapping("/sample")
    public ResponseEntity<List<Sample>> getAllSample(@RequestParam(required = false) String sample) {
        try {
            List<Sample> samples = new ArrayList<>();

            if (sample == null)
                sampleRepo.findAll().forEach(samples::add);
            else
                sampleRepo.findByCustomer_idContaining(sample).forEach(samples::add);

            if (samples.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(samples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //result
    @GetMapping("/result")
    public ResponseEntity<List<Result>> getAllResult(@RequestParam(required = false) String result) {
        try {
            List<Result> results = new ArrayList<>();

            if (result == null)
                sampleRepo.findAllResult().forEach(results::add);

            if (results.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sample/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable("id") long id) {
        Sample sample = sampleRepo.findById(id);

        if (sample != null) {
            return new ResponseEntity<>(sample, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sample")
    public ResponseEntity<String> createSample(@RequestBody Sample sample) {
        try {
            sampleRepo.save(new Sample(sample.getCustomer_id(), sample.getMsisdn(), sample.getValue()));
            return new ResponseEntity<>("Sample was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sample/{id}")
    public ResponseEntity<String> updateSample(@PathVariable("id") long id, @RequestBody Sample sample) {
        Sample _sample = sampleRepo.findById(id);

        if (_sample != null) {
            _sample.setId(id);
            _sample.setCustomer_id(sample.getCustomer_id());
            _sample.setMsisdn(sample.getMsisdn());
            _sample.setValue(sample.getValue());

            sampleRepo.update(_sample);
            return new ResponseEntity<>("Tutorial was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sample/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable("id") long id) {
        try {
            int result = sampleRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Sample with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sample was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete sample.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sample")
    public ResponseEntity<String> deleteAllSample() {
        try {
            int numRows = sampleRepo.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Sample(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete sample.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
