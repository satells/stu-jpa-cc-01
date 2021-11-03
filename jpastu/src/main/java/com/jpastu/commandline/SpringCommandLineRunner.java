package com.jpastu.commandline;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jpastu.model.CategoriaFlexivel;
import com.jpastu.model.Cliente;
import com.jpastu.model.Pedido;
import com.jpastu.model.PedidoItem;
import com.jpastu.model.Produto;
import com.jpastu.repository.CategoriaRepository;
import com.jpastu.repository.ClienteRepository;
import com.jpastu.repository.PedidoRepository;
import com.jpastu.repository.ProdutoReposiroty;

@Component
public class SpringCommandLineRunner implements CommandLineRunner {

	private ProdutoReposiroty produtoReposiroty;
	private CategoriaRepository categoriaRepository;
	private ClienteRepository clienteRepository;
	private PedidoRepository pedidoRepository;

	public SpringCommandLineRunner(ProdutoReposiroty produtoReposiroty, CategoriaRepository categoriaRepository, ClienteRepository clienteRepository,
			PedidoRepository pedidoRepository) {
		this.produtoReposiroty = produtoReposiroty;
		this.categoriaRepository = categoriaRepository;
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		cadastrarCliente();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		cadastrarCategoria();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		cadastrarProduto();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Produto> produtos = produtoReposiroty.buscarPorNome("A");
		produtos.forEach(p -> System.out.println(p));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		produtos = produtoReposiroty.buscarPorNomeDaCategoria("eletronicos");
		produtos.forEach(p -> System.out.println(p));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		BigDecimal preco = produtoReposiroty.buscaPrecoProdutoComNome("NOKIA");
		System.out.println(String.format("<<<<<< Preco do produto: %f >>>>>>>>", preco == null ? 0 : preco.doubleValue()));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		cadastrarPedido();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

	private void cadastrarPedido() {
		Cliente cliente = this.clienteRepository.buscaPorCpf("019.035.560-31");
		System.out.println(cliente);

		Pedido pedido = new Pedido(cliente);

		Produto leitorDeLivro = this.produtoReposiroty.findById(2l).get();
		Produto adaptador = this.produtoReposiroty.findById(5l).get();

		pedido.adicionarItem(new PedidoItem(10, pedido, leitorDeLivro));
		pedido.adicionarItem(new PedidoItem(2, pedido, adaptador));

		this.pedidoRepository.save(pedido);

	}

	private void cadastrarCliente() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		clientes.forEach(c -> System.out.println(c));
		if (clientes.size() == 0) {
			this.clienteRepository.save(new Cliente("Miguel", "019.035.560-31"));
			this.clienteRepository.save(new Cliente("Arthur", "422.488.180-25"));
			this.clienteRepository.save(new Cliente("Heitor", "023.908.140-42"));
			this.clienteRepository.save(new Cliente("Helena", "348.832.870-84"));
			this.clienteRepository.save(new Cliente("Alice", "226.708.910-63"));
			this.clienteRepository.save(new Cliente("Laura", "726.667.910-92"));
		}
	}

	private void cadastrarProduto() {
		List<Produto> produtos = produtoReposiroty.buscarTodos();

		produtos.forEach(p -> System.out.println(p));
		if (produtos.size() == 0) {
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			CategoriaFlexivel eletronico = this.categoriaRepository.findById(1l).get();
			this.produtoReposiroty.save(new Produto("Kindle 10a. geração com iluminação", "Leitor de livro", new BigDecimal("399.00"), eletronico));
			this.produtoReposiroty.save(new Produto("Pilha Alcalina AA, Duracell C/16", "Pilha Duracell", new BigDecimal("59.54"), eletronico));
			this.produtoReposiroty.save(new Produto("Câmera de Monitoramento Wi-Fi", "Câmera de Segurança", new BigDecimal("252.53"), eletronico));
			this.produtoReposiroty.save(new Produto("Adaptador de energia", "Adaptador", new BigDecimal("99.91"), eletronico));
			this.produtoReposiroty.save(new Produto("Fire TV Stick 4K com Controle", "Controle remoto", new BigDecimal("449.82"), eletronico));
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			CategoriaFlexivel celular = this.categoriaRepository.findById(2l).get();

			this.produtoReposiroty.save(new Produto("CELULAR SAMSUNG A7", "CELULAR SAMSUNG", new BigDecimal("1567.64"), celular));
			this.produtoReposiroty.save(new Produto("IPHONE 7", "CELULAR IPHONE", new BigDecimal("5974.54"), celular));
			this.produtoReposiroty.save(new Produto("MOTOROLA E20", "CELULAR MOTOROLA", new BigDecimal("978.98"), celular));
			this.produtoReposiroty.save(new Produto("MOTOROLA E20", "CELULAR MOTOROLA", new BigDecimal("978.98"), celular));
			this.produtoReposiroty.save(new Produto("NOKIA G300", "CELULAR NOKIA", new BigDecimal("365.97"), celular));
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			CategoriaFlexivel livro = this.categoriaRepository.findById(3l).get();

			this.produtoReposiroty.save(new Produto("Os quatro amores", "Livro de Literatura", new BigDecimal("17.55"), livro));
			this.produtoReposiroty.save(new Produto("A Divina Comédia", "Livro de Literatura", new BigDecimal("58.46"), livro));
			this.produtoReposiroty.save(new Produto("Ilíada", "Livro de Literatura", new BigDecimal("165.29"), livro));
			this.produtoReposiroty.save(new Produto("Os Lusíadas", "Livro de Literatura", new BigDecimal("86.49"), livro));
			this.produtoReposiroty.save(new Produto("As Mil e Uma Noites", "Livro de Literatura", new BigDecimal("106.35"), livro));
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			CategoriaFlexivel livroDidatico = this.categoriaRepository.findById(4l).get();

			this.produtoReposiroty.save(new Produto("Cartilha Caminho Suave", "Livro de Alfabetização", new BigDecimal("35.49"), livroDidatico));
			this.produtoReposiroty.save(new Produto("Geografia Elementar", "Livro de Geografia", new BigDecimal("16.35"), livroDidatico));
			this.produtoReposiroty.save(new Produto("Matemática Básica O Guia", "Livro de Matemática", new BigDecimal("49.25"), livroDidatico));
			this.produtoReposiroty.save(new Produto("Educação Waldorf em casa", "Livro de Educação", new BigDecimal("24.99"), livroDidatico));
			this.produtoReposiroty.save(new Produto("Tratado Sobre Educação Cristã", "Educação Cristã", new BigDecimal("154.49"), livroDidatico));
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		}
	}

	private void cadastrarCategoria() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<CategoriaFlexivel> categorias = this.categoriaRepository.findAll();

		categorias.forEach(c -> System.out.println(c));

		if (categorias.size() == 0) {
			CategoriaFlexivel categoria = new CategoriaFlexivel();
			categoria.setDescricao("ELETRONICO");
			this.categoriaRepository.save(categoria);

			categoria = new CategoriaFlexivel();
			categoria.setDescricao("CELULAR");
			this.categoriaRepository.save(categoria);

			categoria = new CategoriaFlexivel();
			categoria.setDescricao("LIVRO");
			this.categoriaRepository.save(categoria);

			categoria = new CategoriaFlexivel();
			categoria.setDescricao("LIVRO DIDATICO");
			this.categoriaRepository.save(categoria);
		}
	}
}