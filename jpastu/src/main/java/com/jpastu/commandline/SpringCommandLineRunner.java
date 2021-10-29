package com.jpastu.commandline;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jpastu.model.CategoriaFlexivel;
import com.jpastu.model.Produto;
import com.jpastu.repository.CategoriaRepository;
import com.jpastu.repository.ProdutoReposiroty;

@Component
public class SpringCommandLineRunner implements CommandLineRunner {

	private ProdutoReposiroty produtoReposiroty;
	private CategoriaRepository categoriaRepository;

	public SpringCommandLineRunner(ProdutoReposiroty produtoReposiroty, CategoriaRepository categoriaRepository) {
		this.produtoReposiroty = produtoReposiroty;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		String nome = "SAMSUNG A5";
		String descricao = "CELULAR";
		BigDecimal preco = new BigDecimal("800.11");

		CategoriaFlexivel categoria = new CategoriaFlexivel("generica");

		CategoriaFlexivel celulares = categoriaRepository.findByDescricao("celulares");

//		Produto celular = new Produto(nome, descricao, preco, categoria);

//		produtoReposiroty.save(celular);
		Produto produto = produtoReposiroty.findById((long) 2).get();

		produtoReposiroty.delete(produto);
	}
}