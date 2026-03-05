package com.catraca.SistemaCatraca;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Spring cria essa busca automático pelo nome do método!
    Optional<Usuario> findByCpf(String cpf);
}