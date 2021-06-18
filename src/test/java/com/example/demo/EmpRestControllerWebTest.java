package com.example.demo;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.example.demo.Rest.EmpRestController;
import com.example.demo.entity.Emp;
import com.example.demo.service.EmpServiceImpl;

import reactor.core.publisher.Mono;

@WebFluxTest(controllers =EmpRestController.class )
//@Import(EmpServiceImpl.class)
public class EmpRestControllerWebTest {
	
	@MockBean
	EmpServiceImpl empServ;

	@Autowired
	private WebTestClient webClient;
	
	@Test
	void testCreateEmployee() {
		Emp emp = new Emp();
		emp.setEmpId(501);
		emp.setName("Dheer");
		emp.setCity("hydrabad");
		emp.setSalary(500);
		
		Mockito.when(empServ.SaveEmp(emp)).thenReturn(Mono.just("Employee saved sucessfully!!"));
		
		webClient.post()
		.uri("/emp/save")
		.contentType(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(emp))
		.exchange()
		.expectStatus().isCreated();

	//Mockito.verify(empServ, times(1)).SaveEmp(emp);
}
	@Test
    void testGetEmployeeById() 
	{
		Emp emp = new Emp();
		emp.setEmpId(501);
		emp.setName("Dheer");
		emp.setCity("hydrabad");
		emp.setSalary(500);
		
			
        Mockito
            .when(empServ.RetriveEmp(501))
            .thenReturn(Mono.just(emp));

        webClient.get().uri("/emp/find/{empId}", 501)
	        .exchange()
	        .expectStatus().isOk()
	        .expectBody();
	    //    .jsonPath("$.name").isNotEmpty()
	      //  .jsonPath("$.id").isEqualTo(100)
	       // .jsonPath("$.name").isEqualTo("Test")
	       // .jsonPath("$.salary").isEqualTo(1000);
        
        //Mockito.verify(repository, times(1)).findById(100);
    }
	@Test
    void testDeleteEmployee() 
	{
		Mono<String> voidReturn  = Mono.just("Employee deleted sucessfully");
        Mockito
            .when(empServ.deleteEmp(501))
            .thenReturn(voidReturn);

        webClient.delete().uri("/emp/del/{empId}", 501)
	        .exchange()
	        .expectStatus().isOk();
    }
	


}
