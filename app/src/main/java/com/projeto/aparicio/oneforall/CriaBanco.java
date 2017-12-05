package com.projeto.aparicio.oneforall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aparicio on 17/10/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {


    private static final String NOME_DB = "one4all.db";

    private static final int VERSAO_BD = 1;

    private static final String LOG_TAG = "one4all";

    private final Context contexto;


    public CriaBanco(Context context)
    {
        super(context, NOME_DB,null,VERSAO_BD);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE rede ( codigo text primary key, IPv4Admin text, nome text, nomeAdmin text, senha text , nMembros text, ativa text);";
        db.execSQL(sql);

        sql = "CREATE TABLE membro ( codigoMembro text primary key, codigoRede text, codigoUsuario text );";
        db.execSQL(sql);

        sql = "CREATE TABLE usuario (codigoUsuario text primary key, IPv4 text, nome text, caminhoD text, caminhoP text, principal text);";
        db.execSQL(sql);

        sql = "CREATE TABLE arqUsuario (codigoArquivo text primary key, caminho text, tamanho text, nome text, codigoUsuario text );";
        db.execSQL(sql);

        sql = "CREATE TABLE membroRede (codigoMembroRede text primary key, nome text, IPv4 text, codigoRede text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
