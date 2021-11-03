package com.jpastu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpastu.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
