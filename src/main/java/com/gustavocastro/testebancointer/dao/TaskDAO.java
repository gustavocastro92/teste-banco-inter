package com.gustavocastro.testebancointer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavocastro.testebancointer.entity.Task;

/*
 * Classe DAO para o job do teste do Banco Inter
 * @author <a href="mailto:gustavogcastro1992@gmail.comr"> Gustavo Castro </a>
 * 
 */

@Repository
public interface TaskDAO extends JpaRepository<Task, Long> {

}
