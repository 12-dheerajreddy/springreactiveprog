package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Emp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeServ {
	
	public Mono<String> SaveEmp(Emp e);
	public Mono<Emp> RetriveEmp(int id);
	public Mono<String> UpdateEmp(Emp e);
	public Mono<String> deleteEmp(int id);
	//public Flux<Emp> FindAllEmp();

}
