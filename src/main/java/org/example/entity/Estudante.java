package org.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "estudante", schema = "universidade")
public class Estudante {

    // A chave primária agora é a matrícula, e não um ID gerado automaticamente
    @Id
    @Column(name = "mat_estudante", length = 7)
    private String matEstudante;

    // Relação de 1 para 1 com a tabela Usuario baseada no CPF
    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Usuario usuario;

    @Column(name = "mc", precision = 3, scale = 1)
    private BigDecimal mc;

    @Column(name = "ano_ingresso")
    private Integer anoIngresso;

    // Construtor Vazio (Exigência do JPA/Hibernate)
    public Estudante() {
    }

    // Construtor com todos os argumentos
    public Estudante(String matEstudante, Usuario usuario, BigDecimal mc, Integer anoIngresso) {
        this.matEstudante = matEstudante;
        this.usuario = usuario;
        this.mc = mc;
        this.anoIngresso = anoIngresso;
    }

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    public String getMatEstudante() {
        return matEstudante;
    }

    public void setMatEstudante(String matEstudante) {
        this.matEstudante = matEstudante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getMc() {
        return mc;
    }

    public void setMc(BigDecimal mc) {
        this.mc = mc;
    }

    public Integer getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(Integer anoIngresso) {
        this.anoIngresso = anoIngresso;
    }
}