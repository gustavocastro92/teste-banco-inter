package com.gustavocastro.testebancointer.dao;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavocastro.testebancointer.entity.Job;

/*
 * Classe DAO para o job do teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.comr"> Gustavo Castro </a>
 * 
 */

@Repository
public interface JobDAO extends JpaRepository<Job, Long>{
	

}