package com.gustavocastro.testebancointer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavocastro.testebancointer.entity.Job;

/*
 * Classe DAO para o job do teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.com"> Gustavo Castro </a>
 * 
 */

@Repository
public interface JobDAO extends JpaRepository<Job, Long>{
	

}