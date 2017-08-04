package me.moosecanswim.resumegen.repository;

import me.moosecanswim.resumegen.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
