package org.example.spu;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface EmployerRepository extends CrudRepository<Employer, Long> {

    public List<Employer> findByDepartment(Department department);
    
}
