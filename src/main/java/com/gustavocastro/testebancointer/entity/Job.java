package com.gustavocastro.testebancointer.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Classe que define os jobs no teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.comr"> Gustavo Castro </a>
 * 
 * Referencial tecnico:
 * https://www.baeldung.com/hibernate-one-to-many
 */

@Entity
@Table(name="JOBS")

public class Job{
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 64, nullable = false)
	private String name;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@Column(name = "tasks", nullable = true)
	@OneToMany(mappedBy="job"
		, targetEntity = Task.class
		, cascade = CascadeType.ALL)
	private List<Task> tasks = new ArrayList<Task>();
	
	@ManyToOne
	@JoinColumn(name="parent_job", nullable=true)
	private Job parentJob;
	
	public Job() {}

	public Job(long id, String name, boolean active, List<Task> tasks, Job parentJob) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.tasks = tasks;
		this.parentJob = parentJob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Job getParentJob() {
		return parentJob;
	}

	public void setParentJob(Job parentJob) {
		this.parentJob = parentJob;
	}
	
	public boolean haveTasks() {
		return this.getTasks() != null && !this.getTasks().isEmpty();
	}
	
}
