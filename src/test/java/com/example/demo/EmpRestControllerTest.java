package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.Rest.EmpRestController;
import com.example.demo.entity.Emp;
import com.example.demo.service.EmpServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@WebMvcTest(value=EmpRestController.class)
public class EmpRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmpServiceImpl empServ;
	
	@Test
	public void test_getById() throws Exception {
		
		Emp e=new Emp(106,"dheer","banglouru",2000);
		Mono<Emp> just = Mono.just(e);
		when(empServ.RetriveEmp(106)).thenReturn(just);
	
		MockHttpServletRequestBuilder reqBuilder=MockMvcRequestBuilders.get("/emp/find/106");
		MvcResult mvcResult = mvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void test_addEmp() throws Exception {
	Emp e=new Emp(106,"dheer","banglouru",2000);
//	Mono<Emp> just = Mono.just(e);
	Mono<String> mstr=Mono.just("employee saved sucessfully");
		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(e);
		
		when(empServ.SaveEmp(e)).thenReturn(mstr);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/emp/save")
																		 .contentType("application/json")
																		 .content(bookJson);
		MvcResult mvcResult = mvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
		
		
	}	
	
	@Test
	public void test_deleteByEmp() throws Exception {
		

	Mono<String> mstr=Mono.just("employee deleted sucessfully");
	when(empServ.deleteEmp(105)).thenReturn(mstr);
	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/emp/del/105");
	MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
	MockHttpServletResponse response = mvcResult.getResponse();
	int status = response.getStatus();
	assertEquals(202, status);
	}
	
	@Test
	public void test_updateEmployee() throws Exception {
		Emp e=new Emp(105,"dheer","banglouru",2000);
		Mono<String> just = Mono.just("Employee updated sucessfully");
		when(empServ.UpdateEmp(e)).thenReturn(just);
		
		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(e);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/emp/Update")
																			  .contentType("application/json")
																			  .content(bookJson);
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(202, status);
		
	}
	
	
	
}
