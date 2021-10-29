package com.jpastu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpastu.model.Produto;

public interface ProdutoReposiroty extends JpaRepository<Produto, Long> {

}
