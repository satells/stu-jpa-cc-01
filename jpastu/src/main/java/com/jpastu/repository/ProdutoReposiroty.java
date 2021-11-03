package com.jpastu.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpastu.model.Produto;

public interface ProdutoReposiroty extends JpaRepository<Produto, Long> {

	@Query("select p from Produto p")
	List<Produto> buscarTodos();

	@Query("select p from Produto p where p.nome like %:nome% order by p.nome, p.id")
	List<Produto> buscarPorNome(@Param("nome") String nome);

	@Query("select p from Produto p where p.categoria.descricao = :categoria")
	List<Produto> buscarPorNomeDaCategoria(@Param("categoria") String categoria);

	@Query("select p.preco from Produto p where p.nome like %:nome%")
	BigDecimal buscaPrecoProdutoComNome(@Param("nome") String nome);

}
