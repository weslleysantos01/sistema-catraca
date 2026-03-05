package com.catraca.SistemaCatraca;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "acessos")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;
    private String usuarioNome;
    private LocalDateTime dataHora;
    private boolean autorizado;

    // Construtor vazio - obrigatório pro JPA
    public Acesso() {}

    public Acesso(String usuarioId, String usuarioNome, boolean autorizado) {
        this.usuarioId   = usuarioId;
        this.usuarioNome = usuarioNome;
        this.dataHora    = LocalDateTime.now();
        this.autorizado  = autorizado;
    }

    public Long getId()           { return id; }
    public String getUsuarioId()  { return usuarioId; }
    public String getUsuarioNome(){ return usuarioNome; }
    public LocalDateTime getDataHora() { return dataHora; }
    public boolean isAutorizado() { return autorizado; }
}
