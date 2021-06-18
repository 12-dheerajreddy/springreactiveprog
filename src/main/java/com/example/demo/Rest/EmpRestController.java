package com.example.demo.Rest;

import java.time.Duration
;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Emp;
import com.example.demo.service.EmpServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/emp")
public class EmpRestController {
	
	@Autowired
	private EmpServiceImpl empServ;
		
	@GetMapping(value = "/find/{empId}",produces = "application/json")
	public ResponseEntity<Mono<Emp>> FindById(@PathVariable int empId){
		
		Mono<Emp> retriveEmp = empServ.RetriveEmp(empId);
		//Mono<Emp> retemp=Mono.just(retriveEmp);
		
		
		return new ResponseEntity<Mono<Emp>>(retriveEmp, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/save", consumes = "application/json")
	public ResponseEntity<Mono<String>> EmpSaved(@RequestBody Emp emp){
		Mono<String> saveEmp = empServ.SaveEmp(emp);
		//Mono<String> just = Mono.just(saveEmp);
		
		return new ResponseEntity<Mono<String>>(saveEmp,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/Update",consumes = "application/json")
	public ResponseEntity<Mono<String>> EmpUpdate(@RequestBody Emp emp){
		 Mono<String> updateEmp = empServ.UpdateEmp(emp);
		//Mono<String> just = Mono.just(uEmp);
		return new ResponseEntity<Mono<String>>(updateEmp,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/del/{empId}")
	public ResponseEntity<Mono<String>> DelById(@PathVariable int empId){
	//	String deleteEmp =
				Mono<String> deleteEmp = empServ.deleteEmp(empId);
		//Mono<String> just = Mono.just(deleteEmp);
		return new ResponseEntity<Mono<String>>(deleteEmp,HttpStatus.OK);
	}
	@PutMapping(value="/Update",consumes = "application/json")
	public ResponseEntity<Mono<String>> EmpUpdate(@RequestBody Emp emp){
		 Mono<String> updateEmp = empServ.UpdateEmp(emp);
		//Mono<String> just = Mono.just(uEmp);
		return new ResponseEntity<Mono<String>>(updateEmp,HttpStatus.ACCEPTED);
	}
	
	
	
}
	

