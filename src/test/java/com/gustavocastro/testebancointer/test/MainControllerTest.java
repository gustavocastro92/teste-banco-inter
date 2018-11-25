package com.gustavocastro.testebancointer.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.gustavocastro.testebancointer.bo.JobBO;
import com.gustavocastro.testebancointer.entity.Job;
 
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
 
	@Autowired
    private MockMvc mockMvc;
 
	@MockBean
    private JobBO jobBOMock;
    
    //@Test
    public void putJobTest() throws Exception{
    	
    	Job first  = new Job(99, "name", false, null, null);
    	Job second = new Job(98, "name", false, null, first);
    	
    	Gson gson = new Gson(); 
    	String json = gson.toJson(second);
    	
    	this.mockMvc.perform(put("/jobs/1").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
                        status().isOk());
    }
 
    @Test
    public void getAllJobsTest() throws Exception {
    	
    	//putJobTest();
    	
    	Job first  = new Job(99, "name", false, null, null);
    	Job second = new Job(98, "name", false, null, first);
        
    	Gson gson = new Gson(); 
    	String json = gson.toJson(second);
    	
    	this.mockMvc.perform(put("/jobs/1").contentType(MediaType.APPLICATION_JSON).content(json));
 
        mockMvc.perform(get("/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(99)))
                .andExpect(jsonPath("$[0].name", is("name")));

 
   
    }
}
