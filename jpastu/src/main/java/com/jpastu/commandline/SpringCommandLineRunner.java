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
import com.jpastu.vo.RelatorioDeVendasVo;

/*select * from 
	(((pedido p inner join cliente c on c.id = p.cliente_id) inner join pedidoitem pi on p.id = pi.pedido_id) inner join produto pr on pr.id = pi.produto_id) inner join categoria ca  on ca.id = pr.categoria_id
	order by p.id
	*/
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
		testaroutros();

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

	private void cadastrarPedido() {
		Cliente miguel = this.clienteRepository.buscaPorCpf("019.035.560-31");
		Cliente arthur = this.clienteRepository.buscaPorCpf("422.488.180-25");
		Cliente heitor = this.clienteRepository.buscaPorCpf("023.908.140-42");
		Cliente helena = this.clienteRepository.buscaPorCpf("348.832.870-84");
		Cliente alice = this.clienteRepository.buscaPorCpf("226.708.910-63");
		Cliente laura = this.clienteRepository.buscaPorCpf("726.667.910-92");

		Pedido pedido1 = new Pedido(miguel);
		Pedido pedido2 = new Pedido(heitor);
		Pedido pedido3 = new Pedido(laura);
		Pedido pedido4 = new Pedido(arthur);
		Pedido pedido5 = new Pedido(heitor);
		Pedido pedido6 = new Pedido(miguel);
		Pedido pedido7 = new Pedido(helena);
		Pedido pedido8 = new Pedido(helena);
		Pedido pedido9 = new Pedido(alice);
		Pedido pedido10 = new Pedido(helena);
		Pedido pedido11 = new Pedido(helena);
		Pedido pedido12 = new Pedido(laura);
		Pedido pedido13 = new Pedido(arthur);
		Pedido pedido14 = new Pedido(miguel);
		Pedido pedido15 = new Pedido(miguel);
		Pedido pedido16 = new Pedido(arthur);
		Pedido pedido17 = new Pedido(arthur);
		Pedido pedido18 = new Pedido(heitor);
		Pedido pedido19 = new Pedido(heitor);
		Pedido pedido20 = new Pedido(helena);
		Pedido pedido21 = new Pedido(alice);
		Pedido pedido22 = new Pedido(alice);
		Pedido pedido23 = new Pedido(alice);
		Pedido pedido24 = new Pedido(laura);
		Pedido pedido25 = new Pedido(laura);
		Pedido pedido26 = new Pedido(heitor);
		Pedido pedido27 = new Pedido(laura);
		Pedido pedido28 = new Pedido(helena);
		Pedido pedido29 = new Pedido(laura);
		Pedido pedido30 = new Pedido(arthur);

		Produto kindle = this.produtoReposiroty.findById(1l).get();
		Produto pilha = this.produtoReposiroty.findById(2l).get();
		Produto camera = this.produtoReposiroty.findById(3l).get();
		Produto adaptador = this.produtoReposiroty.findById(4l).get();
		Produto fireTv = this.produtoReposiroty.findById(5l).get();
		Produto celular = this.produtoReposiroty.findById(6l).get();
		Produto iphone = this.produtoReposiroty.findById(7l).get();
		Produto motorolae20 = this.produtoReposiroty.findById(8l).get();
		Produto motorolae30 = this.produtoReposiroty.findById(9l).get();
		Produto nokiag300 = this.produtoReposiroty.findById(10l).get();
		Produto osquatroamores = this.produtoReposiroty.findById(11l).get();
		Produto adivinacomedia = this.produtoReposiroty.findById(12l).get();
		Produto iliada = this.produtoReposiroty.findById(13l).get();
		Produto oslusiadas = this.produtoReposiroty.findById(14l).get();
		Produto asmileumanoites = this.produtoReposiroty.findById(15l).get();
		Produto cartilhacaminhosuave = this.produtoReposiroty.findById(16l).get();
		Produto geografiaelementar = this.produtoReposiroty.findById(17l).get();
		Produto matematicabasicaoguia = this.produtoReposiroty.findById(18l).get();
		Produto educacaowaldorfemcasa = this.produtoReposiroty.findById(19l).get();
		Produto tratadosobreeducacaocrista = this.produtoReposiroty.findById(20l).get();

		pedido1.adicionarItem(new PedidoItem(15, celular));
		pedido1.adicionarItem(new PedidoItem(2, iphone));
		pedido2.adicionarItem(new PedidoItem(27, fireTv));
		pedido2.adicionarItem(new PedidoItem(21, nokiag300));
		pedido2.adicionarItem(new PedidoItem(10, camera));
		pedido3.adicionarItem(new PedidoItem(26, geografiaelementar));
		pedido3.adicionarItem(new PedidoItem(29, asmileumanoites));
		pedido4.adicionarItem(new PedidoItem(8, nokiag300));
		pedido5.adicionarItem(new PedidoItem(10, oslusiadas));
		pedido5.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido5.adicionarItem(new PedidoItem(30, kindle));
		pedido6.adicionarItem(new PedidoItem(28, adaptador));
		pedido7.adicionarItem(new PedidoItem(4, iliada));
		pedido7.adicionarItem(new PedidoItem(1, adivinacomedia));
		pedido7.adicionarItem(new PedidoItem(20, adivinacomedia));
		pedido7.adicionarItem(new PedidoItem(26, iliada));
		pedido7.adicionarItem(new PedidoItem(30, matematicabasicaoguia));
		pedido7.adicionarItem(new PedidoItem(24, cartilhacaminhosuave));
		pedido8.adicionarItem(new PedidoItem(14, educacaowaldorfemcasa));
		pedido8.adicionarItem(new PedidoItem(2, oslusiadas));
		pedido8.adicionarItem(new PedidoItem(12, motorolae30));
		pedido8.adicionarItem(new PedidoItem(30, iliada));
		pedido8.adicionarItem(new PedidoItem(8, oslusiadas));
		pedido8.adicionarItem(new PedidoItem(1, oslusiadas));
		pedido8.adicionarItem(new PedidoItem(5, kindle));
		pedido8.adicionarItem(new PedidoItem(10, motorolae20));
		pedido9.adicionarItem(new PedidoItem(10, asmileumanoites));
		pedido9.adicionarItem(new PedidoItem(6, matematicabasicaoguia));
		pedido9.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido9.adicionarItem(new PedidoItem(10, motorolae20));
		pedido9.adicionarItem(new PedidoItem(4, fireTv));
		pedido9.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido10.adicionarItem(new PedidoItem(12, kindle));
		pedido10.adicionarItem(new PedidoItem(1, adaptador));
		pedido10.adicionarItem(new PedidoItem(19, pilha));
		pedido10.adicionarItem(new PedidoItem(20, oslusiadas));
		pedido10.adicionarItem(new PedidoItem(27, cartilhacaminhosuave));
		pedido10.adicionarItem(new PedidoItem(17, matematicabasicaoguia));
		pedido11.adicionarItem(new PedidoItem(28, motorolae30));
		pedido11.adicionarItem(new PedidoItem(6, osquatroamores));
		pedido11.adicionarItem(new PedidoItem(29, fireTv));
		pedido11.adicionarItem(new PedidoItem(28, adivinacomedia));
		pedido11.adicionarItem(new PedidoItem(4, iliada));
		pedido11.adicionarItem(new PedidoItem(3, matematicabasicaoguia));
		pedido12.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido12.adicionarItem(new PedidoItem(23, asmileumanoites));
		pedido12.adicionarItem(new PedidoItem(29, camera));
		pedido12.adicionarItem(new PedidoItem(27, kindle));
		pedido12.adicionarItem(new PedidoItem(17, asmileumanoites));
		pedido13.adicionarItem(new PedidoItem(12, educacaowaldorfemcasa));
		pedido13.adicionarItem(new PedidoItem(29, asmileumanoites));
		pedido13.adicionarItem(new PedidoItem(28, kindle));
		pedido13.adicionarItem(new PedidoItem(12, iphone));
		pedido13.adicionarItem(new PedidoItem(6, tratadosobreeducacaocrista));
		pedido13.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido14.adicionarItem(new PedidoItem(28, adaptador));
		pedido14.adicionarItem(new PedidoItem(4, iliada));
		pedido14.adicionarItem(new PedidoItem(1, adivinacomedia));
		pedido14.adicionarItem(new PedidoItem(20, adivinacomedia));
		pedido14.adicionarItem(new PedidoItem(26, iliada));
		pedido14.adicionarItem(new PedidoItem(30, matematicabasicaoguia));
		pedido14.adicionarItem(new PedidoItem(24, cartilhacaminhosuave));
		pedido14.adicionarItem(new PedidoItem(14, educacaowaldorfemcasa));
		pedido14.adicionarItem(new PedidoItem(2, oslusiadas));
		pedido15.adicionarItem(new PedidoItem(12, motorolae30));
		pedido15.adicionarItem(new PedidoItem(30, iliada));
		pedido15.adicionarItem(new PedidoItem(8, oslusiadas));
		pedido15.adicionarItem(new PedidoItem(1, oslusiadas));
		pedido15.adicionarItem(new PedidoItem(5, kindle));
		pedido15.adicionarItem(new PedidoItem(10, motorolae20));
		pedido15.adicionarItem(new PedidoItem(10, asmileumanoites));
		pedido16.adicionarItem(new PedidoItem(6, matematicabasicaoguia));
		pedido16.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido16.adicionarItem(new PedidoItem(10, motorolae20));
		pedido16.adicionarItem(new PedidoItem(4, fireTv));
		pedido17.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido17.adicionarItem(new PedidoItem(12, kindle));
		pedido17.adicionarItem(new PedidoItem(1, adaptador));
		pedido17.adicionarItem(new PedidoItem(19, pilha));
		pedido18.adicionarItem(new PedidoItem(15, celular));
		pedido18.adicionarItem(new PedidoItem(2, iphone));
		pedido18.adicionarItem(new PedidoItem(27, fireTv));
		pedido18.adicionarItem(new PedidoItem(21, nokiag300));
		pedido18.adicionarItem(new PedidoItem(10, camera));
		pedido18.adicionarItem(new PedidoItem(26, geografiaelementar));
		pedido19.adicionarItem(new PedidoItem(29, asmileumanoites));
		pedido19.adicionarItem(new PedidoItem(8, nokiag300));
		pedido19.adicionarItem(new PedidoItem(10, oslusiadas));
		pedido19.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido19.adicionarItem(new PedidoItem(30, kindle));
		pedido19.adicionarItem(new PedidoItem(28, adaptador));
		pedido19.adicionarItem(new PedidoItem(4, iliada));
		pedido19.adicionarItem(new PedidoItem(1, adivinacomedia));
		pedido19.adicionarItem(new PedidoItem(20, adivinacomedia));
		pedido19.adicionarItem(new PedidoItem(26, iliada));
		pedido20.adicionarItem(new PedidoItem(30, matematicabasicaoguia));
		pedido20.adicionarItem(new PedidoItem(24, cartilhacaminhosuave));
		pedido21.adicionarItem(new PedidoItem(14, educacaowaldorfemcasa));
		pedido21.adicionarItem(new PedidoItem(2, oslusiadas));
		pedido21.adicionarItem(new PedidoItem(12, motorolae30));
		pedido21.adicionarItem(new PedidoItem(30, iliada));
		pedido21.adicionarItem(new PedidoItem(8, oslusiadas));
		pedido21.adicionarItem(new PedidoItem(1, oslusiadas));
		pedido21.adicionarItem(new PedidoItem(5, kindle));
		pedido21.adicionarItem(new PedidoItem(10, motorolae20));
		pedido21.adicionarItem(new PedidoItem(10, asmileumanoites));
		pedido21.adicionarItem(new PedidoItem(6, matematicabasicaoguia));
		pedido21.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido21.adicionarItem(new PedidoItem(10, motorolae20));
		pedido21.adicionarItem(new PedidoItem(4, fireTv));
		pedido21.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido22.adicionarItem(new PedidoItem(27, fireTv));
		pedido22.adicionarItem(new PedidoItem(21, nokiag300));
		pedido22.adicionarItem(new PedidoItem(10, camera));
		pedido22.adicionarItem(new PedidoItem(26, geografiaelementar));
		pedido22.adicionarItem(new PedidoItem(29, asmileumanoites));
		pedido22.adicionarItem(new PedidoItem(8, nokiag300));
		pedido23.adicionarItem(new PedidoItem(10, oslusiadas));
		pedido23.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido23.adicionarItem(new PedidoItem(30, kindle));
		pedido23.adicionarItem(new PedidoItem(28, adaptador));
		pedido23.adicionarItem(new PedidoItem(4, iliada));
		pedido24.adicionarItem(new PedidoItem(1, adivinacomedia));
		pedido24.adicionarItem(new PedidoItem(20, adivinacomedia));
		pedido24.adicionarItem(new PedidoItem(26, iliada));
		pedido24.adicionarItem(new PedidoItem(30, matematicabasicaoguia));
		pedido24.adicionarItem(new PedidoItem(24, cartilhacaminhosuave));
		pedido24.adicionarItem(new PedidoItem(14, educacaowaldorfemcasa));
		pedido25.adicionarItem(new PedidoItem(2, oslusiadas));
		pedido25.adicionarItem(new PedidoItem(12, motorolae30));
		pedido25.adicionarItem(new PedidoItem(30, iliada));
		pedido25.adicionarItem(new PedidoItem(8, oslusiadas));
		pedido25.adicionarItem(new PedidoItem(1, oslusiadas));
		pedido25.adicionarItem(new PedidoItem(5, kindle));
		pedido26.adicionarItem(new PedidoItem(10, motorolae20));
		pedido26.adicionarItem(new PedidoItem(10, asmileumanoites));
		pedido26.adicionarItem(new PedidoItem(6, matematicabasicaoguia));
		pedido26.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido27.adicionarItem(new PedidoItem(10, motorolae20));
		pedido27.adicionarItem(new PedidoItem(4, fireTv));
		pedido27.adicionarItem(new PedidoItem(28, geografiaelementar));
		pedido28.adicionarItem(new PedidoItem(15, celular));
		pedido28.adicionarItem(new PedidoItem(2, iphone));
		pedido28.adicionarItem(new PedidoItem(27, fireTv));
		pedido28.adicionarItem(new PedidoItem(21, nokiag300));
		pedido28.adicionarItem(new PedidoItem(10, camera));
		pedido28.adicionarItem(new PedidoItem(26, geografiaelementar));
		pedido28.adicionarItem(new PedidoItem(29, asmileumanoites));
		pedido28.adicionarItem(new PedidoItem(8, nokiag300));
		pedido29.adicionarItem(new PedidoItem(10, oslusiadas));
		pedido29.adicionarItem(new PedidoItem(13, asmileumanoites));
		pedido29.adicionarItem(new PedidoItem(30, kindle));
		pedido29.adicionarItem(new PedidoItem(28, adaptador));
		pedido29.adicionarItem(new PedidoItem(4, iliada));
		pedido29.adicionarItem(new PedidoItem(1, adivinacomedia));
		pedido29.adicionarItem(new PedidoItem(20, adivinacomedia));
		pedido30.adicionarItem(new PedidoItem(26, iliada));
		pedido30.adicionarItem(new PedidoItem(30, matematicabasicaoguia));
		pedido30.adicionarItem(new PedidoItem(24, cartilhacaminhosuave));
		pedido30.adicionarItem(new PedidoItem(14, educacaowaldorfemcasa));
		pedido30.adicionarItem(new PedidoItem(2, oslusiadas));
		pedido30.adicionarItem(new PedidoItem(12, motorolae30));
		pedido30.adicionarItem(new PedidoItem(30, iliada));
		pedido30.adicionarItem(new PedidoItem(8, oslusiadas));
		pedido30.adicionarItem(new PedidoItem(1, oslusiadas));
		pedido30.adicionarItem(new PedidoItem(5, kindle));
		pedido30.adicionarItem(new PedidoItem(10, motorolae20));

		this.pedidoRepository.save(pedido1);
		this.pedidoRepository.save(pedido2);
		this.pedidoRepository.save(pedido3);
		this.pedidoRepository.save(pedido4);
		this.pedidoRepository.save(pedido5);
		this.pedidoRepository.save(pedido6);
		this.pedidoRepository.save(pedido7);
		this.pedidoRepository.save(pedido8);
		this.pedidoRepository.save(pedido9);
		this.pedidoRepository.save(pedido10);
		this.pedidoRepository.save(pedido11);
		this.pedidoRepository.save(pedido12);
		this.pedidoRepository.save(pedido13);
		this.pedidoRepository.save(pedido14);
		this.pedidoRepository.save(pedido15);
		this.pedidoRepository.save(pedido16);
		this.pedidoRepository.save(pedido17);
		this.pedidoRepository.save(pedido18);
		this.pedidoRepository.save(pedido19);
		this.pedidoRepository.save(pedido20);
		this.pedidoRepository.save(pedido21);
		this.pedidoRepository.save(pedido22);
		this.pedidoRepository.save(pedido23);
		this.pedidoRepository.save(pedido24);
		this.pedidoRepository.save(pedido25);
		this.pedidoRepository.save(pedido26);
		this.pedidoRepository.save(pedido27);
		this.pedidoRepository.save(pedido28);
		this.pedidoRepository.save(pedido29);
		this.pedidoRepository.save(pedido30);

	}

	private void testaroutros() {
		BigDecimal valorTotalPedido = this.pedidoRepository.valorTotalPedido();
		System.out.println(valorTotalPedido);

		List<Object[]> vendas = this.pedidoRepository.relatorioDeVendas();

		for (Object[] o : vendas) {
			System.out.println(o[0]);
			System.out.println(o[1]);
			System.out.println(o[2]);

		}
		List<RelatorioDeVendasVo> relatorioDeVendasVo = this.pedidoRepository.relatorioDeVendasVo();
		for (RelatorioDeVendasVo v : relatorioDeVendasVo) {
			System.out.println(v);
		}
		Pedido pedido = this.pedidoRepository.findById(1l).get();
		System.out.println(pedido.getId());
		System.out.println(pedido.getData());
		System.out.println(pedido.getValorTotal());

		Pedido buscarPedidoComCliente = this.pedidoRepository.buscarPedidoComCliente(1l);

		System.out.println(buscarPedidoComCliente.getCliente().getNome());
		System.out.println(buscarPedidoComCliente.getCliente().getCpf());
		System.out.println(buscarPedidoComCliente.getCliente().getId());

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Pedido buscarPedidoComClienteComItens = this.pedidoRepository.buscarPedidoComClienteComItens(1581l);

		System.out.println(buscarPedidoComClienteComItens.getCliente().getId() + "<|>" + buscarPedidoComClienteComItens.getCliente().getNome() + "<|>"
				+ buscarPedidoComClienteComItens.getCliente().getCpf());

		List<PedidoItem> itens = buscarPedidoComClienteComItens.getItens();

		itens.forEach(i -> System.out.println(
				i.getProduto().getNome() + "<|>" + i.getQuantidade() + "<|>" + i.getValor() + "<|>" + i.getProduto().getCategoria().getDescricao()));

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Pedido> pedidos = this.pedidoRepository.buscarPedidoAllComClienteComItens();

		for (Pedido p : pedidos) {
			System.out.print(p.getId() + "<|>" + p.getCliente().getId() + "<|>" + p.getCliente().getNome() + "<|>" + p.getCliente().getCpf());

			List<PedidoItem> itens2 = p.getItens();

			for (PedidoItem i : itens2) {
				System.out.print(i.getProduto().getNome() + "<|>" + i.getQuantidade() + "<|>" + i.getValor() + "<|>"
						+ i.getProduto().getCategoria().getDescricao());
			}

			System.out.println();

		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Pedido> pedidos2 = this.pedidoRepository.buscarPedidoAllComClienteComItensOpcional(3l);

		for (Pedido p : pedidos2) {
			System.out.print(p.getId() + "<|>" + p.getCliente().getId() + "<|>" + p.getCliente().getNome() + "<|>" + p.getCliente().getCpf());

			List<PedidoItem> itens2 = p.getItens();

			for (PedidoItem i : itens2) {
				System.out.print(i.getProduto().getNome() + "<|>" + i.getQuantidade() + "<|>" + i.getValor() + "<|>"
						+ i.getProduto().getCategoria().getDescricao());
			}

			System.out.println();

		}

	}
}