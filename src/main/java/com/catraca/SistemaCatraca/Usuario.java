package com.catraca.SistemaCatraca;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String cpf;
    private boolean ativo;

    // Construtor vazio - obrigatório pro JPA
    public Usuario() {}

    // Construtor principal
    public Usuario(String nome, String cpf) {
        this.id    = UUID.randomUUID().toString();
        this.nome  = nome;
        this.cpf   = cpf;
        this.ativo = true;
    }

    public String getId()    { return id; }
    public String getNome()  { return nome; }
    public String getCpf()   { return cpf; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}