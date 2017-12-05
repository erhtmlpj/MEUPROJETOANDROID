package com.projeto.aparicio.oneforall.modelos;

import java.util.List;

/**
 * Created by Aparicio on 23/11/2017.
 */

public class Info {
    List<Usuario> usuario = null;
    List<Membro> membro =  null;
    Rede rede = null;

    public Info(List<Usuario> usuario, List<Membro> membro, Rede rede) {
        this.usuario = usuario;
        this.membro = membro;
        this.rede = rede;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public List<Membro> getMembro() {
        return membro;
    }

    public void setMembro(List<Membro> membro) {
        this.membro = membro;
    }

    public Rede getRede() {
        return rede;
    }

    public void setRede(Rede rede) {
        this.rede = rede;
    }
}
