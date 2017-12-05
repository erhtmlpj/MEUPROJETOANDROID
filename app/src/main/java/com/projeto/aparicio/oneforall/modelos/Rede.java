package com.projeto.aparicio.oneforall.modelos;

import java.io.Serializable;

/**
 * Created by Aparicio on 16/11/2017.
 */

public class Rede implements Serializable{
    String codigo;
    String IPv4Admin;
    String nome;
    String nomeAdmin;
    String senha;
    int nMembros;
    String ativa;

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }

    public int getnMembros() {
        return nMembros;
    }

    public void setnMembros(int nMembros) {
        this.nMembros = nMembros;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIPv4Admin() {
        return IPv4Admin;
    }

    public void setIPv4Admin(String IPv4Admin) {
        this.IPv4Admin = IPv4Admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Rede{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", nomeAdmin='" + nomeAdmin + '\'' +
                '}';
    }
}
