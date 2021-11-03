package com.jpastu.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoItem> itens = new ArrayList<PedidoItem>();

	public Pedido() {
	}

	public void adicionarItem(PedidoItem item) {
		item.setPedido(this);
		this.itens.add(item);

		valorTotal = BigDecimal.ZERO;
		for (PedidoItem i : itens) {
			valorTotal = valorTotal.add(i.getPrecoUnitario().multiply(new BigDecimal(i.getQuantidade())));
		}

	}

	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", cliente=" + cliente + ", itens="
				+ StringUtils.join(itens, "|") + "]";
	}

}
