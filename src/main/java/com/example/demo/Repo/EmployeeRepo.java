package com.example.demo.Repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Emp;

public interface EmployeeRepo extends JpaRepository<Emp, Serializable> {

}
