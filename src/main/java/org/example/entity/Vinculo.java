package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vinculo", schema = "universidade")
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvinculo")
    private Long idVinculo;

    @ManyToOne
    @JoinColumn(name = "mat_estudante", referencedColumnName = "mat_estudante")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "idcurso")
    private Curso curso;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "status")
    private String status;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    public Vinculo() {
    }

    public Vinculo(Long idVinculo, Estudante estudante, Curso curso, LocalDate dataEntrada, String status, LocalDate dataSaida) {
        this.idVinculo = idVinculo;
        this.estudante = estudante;
        this.curso = curso;
        this.dataEntrada = dataEntrada;
        this.status = status;
        this.dataSaida = dataSaida;
    }

    public Long getIdVinculo() {
        return idVinculo;
    }

    public void setIdVinculo(Long idVinculo) {
        this.idVinculo = idVinculo;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
}