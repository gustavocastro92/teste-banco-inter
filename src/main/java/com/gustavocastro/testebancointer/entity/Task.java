package com.gustavocastro.testebancointer.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Classe que define as tasks no teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.com"> Gustavo Castro </a>
 */

@Entity
@Table(name="TASKS")

public class Task{
	@Id
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "name", length = 64, nullable = false)
	private String name;
	
	@Column(name = "weight", nullable = false)
	private int weight;
	
	@Column(name = "completed", nullable = false)
	private boolean completed;
	
	@ManyToOne(optional = true
			, fetch = FetchType.LAZY
			, cascade = CascadeType.MERGE)
    @JoinColumn(name="id_job", nullable=true)
	@JsonIgnore
	private Job job;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	public Task() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
