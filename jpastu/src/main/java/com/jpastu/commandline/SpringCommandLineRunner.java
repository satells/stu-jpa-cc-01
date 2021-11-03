package com.jpastu.commandline;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Produto> produtos = produtoReposiroty.buscarTodos();

		produtos.forEach(p -> System.out.println(p));

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		produtos = produtoReposiroty.buscarPorNome("A");

		produtos.forEach(p -> System.out.println(p));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		produtos = produtoReposiroty.buscarPorNomeDaCategoria("eletronicos");

		produtos.forEach(p -> System.out.println(p));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		BigDecimal preco = produtoReposiroty.buscaPrecoProdutoComNome("xyz");

		System.out.println(String.format("<<<<<< Preco do produto: %f >>>>>>>>", preco == null ? 0 : preco.doubleValue()));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}
}