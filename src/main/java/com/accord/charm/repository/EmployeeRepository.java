package com.accord.charm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accord.charm.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    
//List<User> findByLastname(String lastname);
}
