package com.gustavocastro.testebancointer.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.gustavocastro.testebancointer.bo.JobBO;
import com.gustavocastro.testebancointer.bo.TaskBO;
import com.gustavocastro.testebancointer.controller.MainController;
import com.gustavocastro.testebancointer.entity.Job;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MainControllerTest {
	
	//@Autowired
	private MockMvc mockMvc;
	     
    @Mock
    private JobBO jobBO;
    
    @InjectMocks
    private MainController mainController;
    
    @Mock
    private TaskBO taskBO;
    
    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.    	
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
        

    }
    
	@Test
	public void getAllJobsTest() throws Exception {
		
		Job first = new Job(99L, "name", false, null, null);
		Job second = new Job(98L, "name", false, null, first);
				
		List<Job> jobs = new ArrayList<Job>();
		
		jobs.add(second);

		Mockito.when(this.jobBO.getJobs(null, null)).thenReturn(jobs);
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/jobs")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		mockMvc.perform(get("/jobs"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", is(98)))
		.andExpect(jsonPath("$[0].name", is("name")));

	}
	
	
	@Test
	public void putJobTest() throws Exception {
		
		Job first  = new Job(99, "name1", false, null, null);
    	Job second = new Job(98, "name2", false, null, first);
    	
    	Gson gson = new Gson(); 
    	String json = gson.toJson(second);
    	
    	
		mockMvc.perform(put("/jobs/1").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void postTest() throws Exception {
		
		Job first  = new Job(99, "name1", false, null, null);
    	Job second = new Job(98, "name2", false, null, first);
    	
    	Gson gson = new Gson(); 
    	String json = gson.toJson(second);
    	
		mockMvc.perform(post("/jobs").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
}
