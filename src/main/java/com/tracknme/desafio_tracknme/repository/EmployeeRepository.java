package com.tracknme.desafio_tracknme.repository;

import com.tracknme.desafio_tracknme.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
       List<Employee> findByCep(String cep);

       Page<Employee> findAllByCep(String cep, Pageable pageable);
}
