package com.jpastu.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpastu.model.Pedido;
import com.jpastu.vo.RelatorioDeVendasVo;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select sum(p.valorTotal) from Pedido p")
	public BigDecimal valorTotalPedido();

	@Query("SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome, item.quantidade ORDER BY SUM(item.quantidade) DESC")
	public List<Object[]> relatorioDeVendas();

	@Query("SELECT new com.jpastu.vo.RelatorioDeVendasVo( produto.nome, SUM(item.quantidade), MAX(pedido.data)) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome, item.quantidade ORDER BY SUM(item.quantidade) DESC, produto.nome ASC")
	public List<RelatorioDeVendasVo> relatorioDeVendasVo();
}
