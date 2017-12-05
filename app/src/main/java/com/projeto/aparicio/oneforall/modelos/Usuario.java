package com.projeto.aparicio.oneforall.modelos;

/**
 * Created by Aparicio on 16/11/2017.
 */

public class Usuario {
    String codigoUsuario;
    String IPv4;
    String nome;
    String caminhoD;
    String caminhoP;
    String principal;

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getIPv4() {
        return IPv4;
    }

    public void setIPv4(String IPv4) {
        this.IPv4 = IPv4;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoD() {
        return caminhoD;
    }

    public void setCaminhoD(String caminhoD) {
        this.caminhoD = caminhoD;
    }

    public String getCaminhoP() {
        return caminhoP;
    }

    public void setCaminhoP(String caminhoP) {
        this.caminhoP = caminhoP;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
