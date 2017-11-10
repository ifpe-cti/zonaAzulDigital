/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Samuel
 */
@Entity
public class Motorista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false, length = 40)
    private String senha;
    private BigDecimal credito;

    public Motorista() {
        this.credito = new BigDecimal(0);
    }

    public Motorista(String nome, String cpf, String senha) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;

    }

    public Motorista(int id, String nome, String cpf, BigDecimal credito, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.credito = credito;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    /**
     * Debita um valor do crédito atual do motorista, se o valor for maior que 0
     * e se houver credito suficiente.
     *
     * @param valor - Valor maior que '0' (zero) a ser debitado
     * @return True - Se foi possível debitar; False - Se não foi possível
     * debitar
     *
     */
    public boolean debitar(BigDecimal valor) {
        if (credito.compareTo(valor) > -1) {
            this.credito = this.credito.subtract(valor);
            return true;
        } else {

            return false;
        }
    }

    /**
     * Credita um valor no crédito atual do motorista, se o valor for maior que
     * 0.
     *
     * @param valor Valor maior que '0' (zero) a ser creditado
     * @return True - Se foi possível debitar; False - Se não foi possível
     * debitar
     *
     */
    public boolean creditar(BigDecimal valor) {
        BigDecimal creditoAnt = this.credito;
        this.credito = this.credito.add(valor);
        if (this.credito.compareTo(creditoAnt) == 1) {
            return true;
        } else {
            this.credito = creditoAnt;
            return false;
        }
    }

}
