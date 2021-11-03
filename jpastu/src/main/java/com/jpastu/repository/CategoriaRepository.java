package com.jpastu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpastu.model.CategoriaFlexivel;

public interface CategoriaRepository extends JpaRepository<CategoriaFlexivel, Long> {

	CategoriaFlexivel findByDescricao(String descricao);

}
