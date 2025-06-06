package org.example.spu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

	List<Department> findByLastName(String name);

	Department findById(long id);
}