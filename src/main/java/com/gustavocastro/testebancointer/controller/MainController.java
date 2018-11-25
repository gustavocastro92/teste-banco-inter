package com.gustavocastro.testebancointer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustavocastro.testebancointer.bo.JobBO;
import com.gustavocastro.testebancointer.bo.TaskBO;
import com.gustavocastro.testebancointer.entity.Job;
import com.gustavocastro.testebancointer.entity.Task;

/*
 * Classe Controller do teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.comr"> Gustavo Castro </a>
 * 
 * Referencial tecnico:
 * https://spring.io/guides/gs/rest-service/
 * https://mycuteblog.com/h2-database-example-hibernate-spring-boot/
 * https://spring.io/guides/tutorials/bookmarks/
 * https://www.baeldung.com/spring-request-response-body
 */

@RestController
@EnableAutoConfiguration
public class MainController {
	
	@Autowired
	private JobBO jobBO;	
	
	@Autowired
	private TaskBO taskBO;
	
	@GetMapping(value = "/jobs/{id}")
	public List<Job> getJobById(@PathVariable("id") Long id
							, @RequestParam(required = false) Boolean sortByWeight ) {
		return this.jobBO.getJobs(id, sortByWeight);
	}
	
	@GetMapping(value = "/jobs")
	public List<Job> getJobs(@RequestParam(required = false) Boolean sortByWeight ) {
		return this.jobBO.getJobs(null, sortByWeight);
	}
	
	@PutMapping(value = "/jobs/{id}")
	public void putJob(@PathVariable("id") Long id,
			@RequestBody Job job) throws Exception{		
		this.jobBO.create(job);
	}
	
	@PostMapping(value = "/jobs/{id}")
	public void postJob(@RequestBody Job job) throws Exception{		
		this.jobBO.create(job);
	}
	
	@DeleteMapping(value = "/jobs/{id}")
	public void deleteJob(@PathVariable("id") Long id) {
		this.jobBO.deleteJob(id);
	}
	
	@PutMapping(value = "/tasks/{id}")
	public void createJob(@PathVariable("id") Long id,
			@RequestBody Task task) {
		this.taskBO.create(task);
	}
	
	@GetMapping(value = "/tasks")
	public List<Task> getTask(@RequestParam(required = false) Date createdAt){
		return this.taskBO.getTasks(createdAt);
		
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
}
