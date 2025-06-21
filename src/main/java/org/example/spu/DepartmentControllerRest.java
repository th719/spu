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
@RequestMapping(path="/api/spu/department", produces="application/json")
// @CrossOrigin(origins="http://spu:8080") 
// TODO
public class DepartmentControllerRest {

    private DepartmentRepository departmentRepository;

    public DepartmentControllerRest(DepartmentRepository repository){
        departmentRepository = repository;
    }

    @GetMapping()
    public Iterable<Department> getAll() {
        return departmentRepository.findAll();
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Department> getById(@RequestParam Long id) { // ResponseEntity for return 404
        Optional<Department> optTaco = departmentRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Department postDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping(path="/{id}", consumes="application/json")
    public Department putDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        department.setId(id);
        return departmentRepository.save(department);
    }

    @PatchMapping(path = "/{id}",  consumes="application/json")
    public Department patchDepartment(@PathVariable("id") Long id, @RequestBody Department patch){
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()){
            Department department = opt.get();
            if (patch.getName() != null){
                department.setName(patch.getName());
            }
            if (patch.getParentId() != null){
                department.setParentId(patch.getParentId());
            }
            return departmentRepository.save(department);
        } else {
            return null;
        }
    }
    
    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable("id") long id){
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            
        }
    }
}
