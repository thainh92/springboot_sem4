package com.example.demo.controller;

import com.example.demo.entity.Education;
import com.example.demo.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EducationController {
    @Autowired
    EducationRepository repository;

    @RequestMapping(value = "/education", method = RequestMethod.GET)
    public ResponseEntity<List<Education>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody Education education){
        return new ResponseEntity<>(repository.save(education), HttpStatus.OK);
    }

    @RequestMapping(value = "/education/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") int id) {
        Optional optional = repository.findById(id);
        if (optional.isPresent()){
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/education/{id}", method = RequestMethod.PUT)
    public ResponseEntity<? extends Object> edit(@PathVariable int id, @RequestBody Education education){
        Optional<Education> optional = repository.findById(id);
        if (optional.isPresent()){
            return optional.map(education1 -> {education.setId(education1.getId());
                return new ResponseEntity<>(repository.save(education), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/education/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable int id){
        Optional optional = repository.findById(id);
        if (optional.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
