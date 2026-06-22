package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "curso", schema = "universidade")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso")
    private Long idCurso;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "grau")
    private String grau;

    @Column(name = "turno", nullable = false)
    private String turno;

    @Column(name = "campus", length = 100)
    private String campus;

    @Column(name = "nivel")
    private String nivel;

    // Construtor Vazio (Exigência do JPA/Hibernate)
    public Curso() {
    }

    // Construtor com todos os argumentos
    public Curso(Long idCurso, String nome, String grau, String turno, String campus, String nivel) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.grau = grau;
        this.turno = turno;
        this.campus = campus;
        this.nivel = nivel;
    }

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}