package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.EmployeeRepo;
import com.example.demo.entity.Emp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpServiceImpl implements IEmployeServ {
	
	@Autowired
	private EmployeeRepo empRepo;

	@Override
	public Mono<String> SaveEmp(Emp e) {
		Emp save = empRepo.save(e);
		
			String msg="Employee saved Sucessfully";
			Mono<String> just = Mono.just(msg);
			
			return just;
		
	}

	@Override
	public Mono<Emp> RetriveEmp(int id) {
		//Mono<Emp> emp1=null;
		Optional<Emp> findById = empRepo.findById(id);
		if(findById.isPresent()) {
			Mono<Emp> just = Mono.just(findById.get());
			//emp=findById.get();
			return just;
		}
		return null;
	}

	@Override
	public Mono<String> UpdateEmp(Emp e) {
		Emp save = empRepo.save(e);
		
		String msg="Employee Updated Sucessfully";
		Mono<String> just = Mono.just(msg);
		return just;
	}

	@Override
	public Mono<String> deleteEmp(int id) {
		empRepo.deleteById(id);
		String status="Employee deleted sucessfully";
		Mono<String> just = Mono.just(status);
		return just;
	}

	

	
}
