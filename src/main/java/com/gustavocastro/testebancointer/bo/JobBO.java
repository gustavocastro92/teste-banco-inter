package com.gustavocastro.testebancointer.bo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavocastro.testebancointer.dao.JobDAO;
import com.gustavocastro.testebancointer.entity.Job;
import com.gustavocastro.testebancointer.entity.Task;

/*
 * Classe que define os jobs no teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.comr"> Gustavo Castro </a>
 * 
 * Referencial tecnico:
 * https://stackoverflow.com/questions/21058570/how-to-save-nested-json-object-in-a-spring-mvc
 */

@Service
@Transactional
public class JobBO {
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private TaskBO taskBO;
	

	public void create(Job job) throws Exception {
		createParentIfNotNull(job);
		
		if(checkHierarchy(job, job.getId())) {
			this.jobDAO.save(job);
			createTasksIfNotNull(job);						
		}else {
			throw new Exception("Cannot have nested job with same parent id");
		}
	}
	
	/*
	 * Verifica se o job nao possui objeto pai com mesmo codigo
	 */

	private boolean checkHierarchy(Job job, long target) {
		Job tempJob = getParentJob(job);
		if(tempJob != null && tempJob.getId() == target) {
			return false;
		}else if(tempJob != null && tempJob.getId() != target) {
			return checkHierarchy(tempJob, target);
		}else {
			return true;
		}
	}
	
	/*
	 * Retorna o job pai
	 */

	private Job getParentJob(Job job) {
		if(job.getParentJob() != null)
			return this.jobDAO.findById(job.getParentJob().getId()).get();
		return null;
	}
	
	/*
	 *  verifica se o job possui tasks, se verdadeiro, as cria no banco de dados
	 */

	private void createTasksIfNotNull(Job job) {
		if(!job.getTasks().isEmpty()) {
			linkJob(job);
			createTasks(job);
		}
	}
	
	/*
	 * verifica se o job possui pai, se verdadeiro, o cria no banco de dados
	 */

	private void createParentIfNotNull(Job job) throws Exception {
		if(job.getParentJob() != null) {
			create(job.getParentJob());
		}
	}

	private void createTasks(Job job) {
		for(Task task : job.getTasks()) {
			taskBO.create(task);
		}
	}
	
	/*
	 * Linka a task ao job antes de criar no banco de dados. Necessario para se ter a foreign key 
	 * na tabela de tasks
	 */

	private void linkJob(Job job) {
		for(Task task : job.getTasks()) {
			task.setJob(job);
		}
	}
	
	/*
	 * Retorna o job com o id informado
	 */

	public List<Job> getJobById(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return this.jobDAO.findAllById(ids);
		 
	}
	
	/*
	 * Retorna todos os jobs
	 */
	
	public List<Job> getAllJobs(){
		return this.jobDAO.findAll();
	}
	
	/*
	 * Retorna todos os jobs. Caso seja passado um id, sera retornado somente o job com aquele id
	 */
	
	private List<Job> getJobs(Long id) {
		if(id != null) {
			return getJobById(id);
		}else {
			return getAllJobs();
		}
	}
	
	/*
	 * Retorna todos os jobs com opcao de ordenar as tasks
	 */
	
	public List<Job> getJobs(Long id, Boolean sort) {
		List<Job> result = getJobs(id);
		if(sort != null && sort == true)
			sortTasks(result);		
		return result;
	}
	
	/*
	 * ordena as tasks dentro de uma lista de jobs
	 */

	private void sortTasks(List<Job> listJobs) {
		for(Job job : listJobs) {
			List<Task> listTask = job.getTasks();
			listTask.sort((t1, t2) -> t1.getWeight() > t2.getWeight() ? +1 
														: t1.getWeight() < t2.getWeight() ? -1 : 0);
		}
	}

	
}
