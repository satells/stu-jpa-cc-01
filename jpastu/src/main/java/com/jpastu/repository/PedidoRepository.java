package com.jpastu.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpastu.model.Pedido;
import com.jpastu.vo.RelatorioDeVendasVo;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select sum(p.valorTotal) from Pedido p")
	public BigDecimal valorTotalPedido();

	@Query("SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome, item.quantidade ORDER BY SUM(item.quantidade) DESC")
	public List<Object[]> relatorioDeVendas();

	@Query("SELECT new com.jpastu.vo.RelatorioDeVendasVo( produto.nome, SUM(item.quantidade), MAX(pedido.data)) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome, item.quantidade ORDER BY SUM(item.quantidade) DESC, produto.nome ASC")
	public List<RelatorioDeVendasVo> relatorioDeVendasVo();

	@Query("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id")
	public Pedido buscarPedidoComCliente(@Param("id") Long id);

	@Query("SELECT p FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.itens item JOIN FETCH item.produto produto JOIN FETCH produto.categoria WHERE p.id = :id")
	public Pedido buscarPedidoComClienteComItens(@Param("id") Long id);

	@Query("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.itens item JOIN FETCH item.produto produto JOIN FETCH produto.categoria ORDER BY p.id ASC")
	public List<Pedido> buscarPedidoAllComClienteComItens();

	@Query("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.itens item JOIN FETCH item.produto produto JOIN FETCH produto.categoria WHERE (:cliente_id is null or p.cliente.id=:cliente_id) ORDER BY p.id ASC")
	public List<Pedido> buscarPedidoAllComClienteComItensOpcional(@Param("cliente_id") Long cliente_id);
}
