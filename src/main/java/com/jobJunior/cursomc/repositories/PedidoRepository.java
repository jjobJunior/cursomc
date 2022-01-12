package com.jobJunior.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobJunior.cursomc.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
