package com.projeto.aparicio.oneforall.dao;

import android.content.ContentValues;
import android.content.Context;

import com.projeto.aparicio.oneforall.DbGateway;
import com.projeto.aparicio.oneforall.modelos.Usuario;

/**
 * Created by Aparicio on 19/11/2017.
 */

public class usuarioDAO {
    private final String Usuarios = "usuario";

    private DbGateway gw;

    public usuarioDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);

    }
    public void salvar(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put("codigoUsuario",usuario.getCodigoUsuario());
        valores.put("IPv4",usuario.getIPv4());
        valores.put("nome",usuario.getNome());
        valores.put("caminhoD",usuario.getCaminhoD());
        valores.put("caminhoP",usuario.getCaminhoP());
        valores.put("principal",usuario.getPrincipal());
        gw.getDatabase().insert("usuario", null, valores);
    }

}
