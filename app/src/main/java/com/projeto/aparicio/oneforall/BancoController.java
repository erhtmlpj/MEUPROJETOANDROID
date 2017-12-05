package com.projeto.aparicio.oneforall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.aparicio.oneforall.modelos.Membro;
import com.projeto.aparicio.oneforall.modelos.MembroRede;
import com.projeto.aparicio.oneforall.modelos.Rede;
import com.projeto.aparicio.oneforall.modelos.Usuario;
import com.projeto.aparicio.oneforall.modelos.arqUsuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aparicio on 18/10/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    private DbGateway gw;


    public BancoController(Context context)
    {
        banco = new CriaBanco(context);
        gw = DbGateway.getInstance(context);
    }

    //ESSA É A PARTE REFERENTE À INSERÇÃO DE DADOS NO BANCO DE DADOS

    public String insereDadosRede(Rede rede)
    {
        ContentValues valores;
        long resultado;

        //db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigo",rede.getCodigo());
        valores.put("IPv4Admin",rede.getIPv4Admin());
        valores.put("nome",rede.getNome());
        valores.put("nomeAdmin",rede.getNomeAdmin());
        valores.put("senha",rede.getSenha());
        valores.put("nMembros",rede.getnMembros());
        valores.put("ativa","nao");

        resultado = gw.getDatabase().insert("rede",null,valores);
        //db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosMembroRede(MembroRede membroRede)
    {
        ContentValues valores;
        long resultado;

        //db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoMembroRede",membroRede.getCodigoMembroRede());
        valores.put("IPv4",membroRede.getIPv4());
        valores.put("nome",membroRede.getNome());
        valores.put("codigoRede",membroRede.getCodigoRede());


        resultado = gw.getDatabase().insert("membroRede",null,valores);
        //db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosUsuario(Usuario usuario)
    {
        ContentValues valores;
        long resultado;

        //db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoUsuario",usuario.getCodigoUsuario());
        valores.put("IPv4",usuario.getIPv4());
        valores.put("nome",usuario.getNome());
        valores.put("caminhoD",usuario.getCaminhoD());
        valores.put("caminhoP",usuario.getCaminhoP());
        valores.put("principal",usuario.getPrincipal());

        resultado = gw.getDatabase().insert("usuario",null,valores);
        //db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosMembro(Membro membro)
    {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoMembro",membro.getCodigoMembro());
        valores.put("codigoRede",membro.getCodigoRede());
        valores.put("codigoUsuario",membro.getCodigoUsuario());

        resultado = gw.getDatabase().insert("membro",null,valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosArqUsuario(arqUsuario arqusuario)
    {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoArquivo",arqusuario.getCodigoArquivo());
        valores.put("caminho",arqusuario.getCaminho());
        valores.put("tamanho",arqusuario.getTamanho());
        valores.put("nome",arqusuario.getNome());
        valores.put("codigoUsuario",arqusuario.getCodigoUsuario());

        resultado = gw.getDatabase().insert("arqUsuario",null,valores);
        db.close();

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    //ESSA É A PARTE REFERENTE À LISTAGEM DE DADOS

    public Cursor carregaDadosRede()
    {
        Cursor cursor;
        String[] campos = {"codigo","IPv4Admin","nome","nomeAdmin","senha"};
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("rede",campos,null,null,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public List<Rede> listarRedes()
    {
        List<Rede> redes = new ArrayList<>();
        Rede rede;
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM rede",null);
        while(cursor.moveToNext())
        {
            rede = new Rede();
            rede.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
            rede.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            rede.setNomeAdmin(cursor.getString(cursor.getColumnIndex("nomeAdmin")));
            rede.setIPv4Admin(cursor.getString(cursor.getColumnIndex("IPv4Admin")));
            rede.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            redes.add(rede);
        }
        cursor.close();

        return redes;
    }

    public List<MembroRede> listarMembroRede()
    {
        List<MembroRede> membroRedes = new ArrayList<>();
        MembroRede membroRede;
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM membroRede",null);
        while(cursor.moveToNext())
        {
            membroRede = new MembroRede();
            membroRede.setCodigoMembroRede(cursor.getString(cursor.getColumnIndex("codigoMembroRede")));
            membroRede.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            membroRede.setIPv4(cursor.getString(cursor.getColumnIndex("IPv4")));
            membroRede.setCodigoRede(cursor.getString(cursor.getColumnIndex("codigoRede")));
            membroRedes.add(membroRede);
        }
        cursor.close();

        return membroRedes;
    }

    public Cursor carregaDadosMembro()
    {
        Cursor cursor;
        String[] campos = {"codigoMembro","codigoRede","codigoUsuario"};
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("membro",campos,null,null,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadosUsuario()
    {
        Cursor cursor;
        String[] campos = {"codigoUsuario","IPv4","nome","caminhoD","caminhoP"};
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("usuario",campos,null,null,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadosArqUsuario()
    {
        Cursor cursor;
        String[] campos = {"codigoArquivo","caminho","tamanho","nome","codigoUsuario"};
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("arqUsuario",campos,null,null,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    //ESSA É A PARTE REFERENTE À PESQUISA DE DADOS

    public Cursor carregaDadosRedeById(int codigo)
    {
        Cursor cursor;
        String[] campos = {"codigo","IPv4Admin","nome","nomeAdmin","senha"};
        String where = "codigo" + "=" + codigo;
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("rede",campos,where,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadosMembroById(int codigoMembro)
    {
        Cursor cursor;
        String[] campos = {"codigoMembro","codigoRede","codigoUsuario"};
        String where = "codigoMembro" + "=" + codigoMembro;
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("membro",campos,where,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadosUsuarioById(int codigoUsuario)
    {
        Cursor cursor;
        String[] campos = {"codigoUsuario","IPv4","nome","caminhoD","caminhoP,principal"};
        String where = "codigoUsuario" + "=" + codigoUsuario;
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("usuario",campos,where,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    //PESQUISA O USUARIO DO APLICATIVO
    public Cursor carregaUsuarioPrincipal()
    {
        Cursor cursor;
        Usuario usuario;
        usuario = new Usuario();
        String[] campos = {"codigoUsuario","IPv4","nome","caminhoD","caminhoP,principal"};
        String where = "principal " + "=" + " ?";
        String[] argumento =  {"sim"};
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("usuario",campos,where,argumento,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getRedeAtiva()
    {
        Cursor cursor;
        Rede rede;
        rede = new Rede();
        String[] campos = {"codigo","nome","nomeAdmin","IPv4Admin","senha","nMembros","ativa"};
        String where = "ativa "+"="+" ?";
        String[] argumento = {"sim"};
        cursor = gw.getDatabase().query("rede",campos,where,argumento,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    //CONTINUACAO DO CODIGO ANTERIOR

    public Cursor carregaDadosArqUsuarioById(int codigoArquivo)
    {
        Cursor cursor;
        String[] campos = {"codigoArquivo","caminho","tamanho","nome","codigoUsuario"};
        String where = "codigoArquivo" + "=" + codigoArquivo;
        db = banco.getReadableDatabase();
        cursor = gw.getDatabase().query("arqUsuario",campos,where,null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    //ESSA É A PARTE REFERENTE À ALTERAÇÃO DE DADOS

    public void setRedeDesativa()
    {
        ContentValues valores;
        String where;

        valores = new ContentValues();
        valores.put("ativa","nao");

        gw.getDatabase().update("rede",valores,"ativa = ?",new String[]{"sim"});
        System.out.println("Foi");
    }

    public void setRedeAtiva(Rede rede)
    {
        ContentValues valores;
        String where;

        valores = new ContentValues();
        valores.put("ativa","sim");

        gw.getDatabase().update("rede",valores,"codigo = ?",new String[]{rede.getCodigo()+""});

    }

    public void alteraRegistroMembro(Membro membro)
    {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "codigoMembro" + "=" + membro.getCodigoMembro();

        valores = new ContentValues();
        valores.put("codigoMembro",membro.getCodigoMembro());
        valores.put("codigoRede",membro.getCodigoRede());
        valores.put("codigoUsuario",membro.getCodigoUsuario());

        gw.getDatabase().update("membro",valores,where,null);
        db.close();

    }

    public void alteraRegistroUsuario(Usuario usuario)
    {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "codigoUsuario" + "=" + usuario.getCodigoUsuario();

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoUsuario",usuario.getCodigoUsuario());
        valores.put("IPv4",usuario.getIPv4());
        valores.put("nome",usuario.getNome());
        valores.put("caminhoD",usuario.getCaminhoD());
        valores.put("caminhoP",usuario.getCaminhoP());
        valores.put("principal",usuario.getPrincipal());

        gw.getDatabase().update("usuario",valores,where,null);
        db.close();

    }

    public void alteraRegistroArqUsuario(arqUsuario arqusuario)
    {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "codigoArquivo" + "=" + arqusuario.getCodigoArquivo();

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("codigoArquivo",arqusuario.getCodigoArquivo());
        valores.put("caminho",arqusuario.getCaminho());
        valores.put("tamanho",arqusuario.getTamanho());
        valores.put("nome",arqusuario.getNome());
        valores.put("codigoUsuario",arqusuario.getCodigoUsuario());

        gw.getDatabase().update("arqUsuario",valores,where,null);
        db.close();

    }

    //ESSA É A PARTE REFERENTE À EXCLUSÃO DE DADOS

    public void deletaRegistroRede(String codigo)
    {
        String where = "codigo" + "=" + codigo;
        db = banco.getReadableDatabase();
        gw.getDatabase().delete("rede", "codigo=?", new String[]{ codigo + "" });
        db.close();
    }

    public void deletaRegistroMembro(String codigoMembro)
    {
        String where = "codigoMembro" + "=" + codigoMembro;
        db = banco.getReadableDatabase();
        gw.getDatabase().delete("membro",where,null);
        db.close();
    }

    public void deletaRegistroUsuario(String codigoUsuario)
    {
        String where = "codigoUsuario" + "=" + codigoUsuario;
        db = banco.getReadableDatabase();
        gw.getDatabase().delete("usuario",where,null);
        db.close();
    }

    public void deletaRegistroArqUsuario(String codigoArquivo)
    {
        String where = "codigoArquivo" + "=" + codigoArquivo;
        db = banco.getReadableDatabase();
        gw.getDatabase().delete("arqUsuario",where,null);
        db.close();
    }

}
