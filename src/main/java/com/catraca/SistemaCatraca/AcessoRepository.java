package com.catraca.SistemaCatraca;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    List<Acesso> findAllByOrderByDataHoraDesc();
}