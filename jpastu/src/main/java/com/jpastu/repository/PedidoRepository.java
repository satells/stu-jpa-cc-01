package com.jpastu.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpastu.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select sum(p.valorTotal) from Pedido p")
	public BigDecimal valorTotalPedido();

	@Query("select produto.nome, SUM(item.quantidade), MAX(pedido.data) FROM Pedido pedido JOIN Pedido.itens item JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC")
	public List<Object[]> relatorioDeVendas();

}
