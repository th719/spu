package org.example.spu;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path="/api/employer", produces="application/json")

public class EmployerControllerRest {

    private EmployerRepository employerRepository;

    public EmployerControllerRest(EmployerRepository repository) {
        employerRepository = repository;
    }

    @GetMapping()
    public Iterable<Employer> getAll() {
        return employerRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces="application/json")
    public ResponseEntity<Employer> getById(@PathVariable Long id) { // ResponseEntity for return 404
        Optional<Employer> opt = employerRepository.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employer post(@RequestBody Employer employer) {
        return employerRepository.save(employer);
    }

    @PutMapping(path="/{id}", consumes="application/json")
    public Employer put(@PathVariable("id") Long id, @RequestBody Employer employer) {
        employer.setId(id);
        return employerRepository.save(employer);
    }


    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        try {
            employerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}