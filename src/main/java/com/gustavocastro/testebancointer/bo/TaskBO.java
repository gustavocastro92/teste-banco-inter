package com.gustavocastro.testebancointer.bo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavocastro.testebancointer.dao.TaskDAO;
import com.gustavocastro.testebancointer.entity.Task;

@Service
@Transactional
public class TaskBO {
	
	@Autowired
	private TaskDAO taskDAO;
	
	public void create(Task task) {
		this.taskDAO.save(task);
	}
	
	public Task getTaskById(Long id) {
		Optional<Task> task = this.taskDAO.findById(id);
		return task.get();
	}
	
	public void deleteTask(List<Task> listTask) {
		this.taskDAO.deleteAll(listTask);
	}
	
	public List<Task> getByDate(Date createdAt){
		return this.taskDAO.findAllByCreatedAt(createdAt);				
	}
	
	public List<Task> getTasks(Date createdAt){
		if(createdAt != null) {
			return getByDate(createdAt);
		}else {
			return getTasks();
		}
	}
	
	public List<Task> getTasks(){
		return this.taskDAO.findAll();
	}
	
}
