package com.jpastu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpastu.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.dadosPessoais.cpf = :cpf")
	Cliente buscaPorCpf(@Param("cpf") String cpf);

}
