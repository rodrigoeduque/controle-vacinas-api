package br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}") @NotNull
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    @NotEmpty(message = "{campo.cpf.obrigatorio}") @NotNull @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.email.obrigatorio}") @NotNull @Email(message = "{campo.email.invalido}")
    private String email;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_cadastro",nullable = false, updatable = false)
    private LocalDate dataCadastro = LocalDate.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
