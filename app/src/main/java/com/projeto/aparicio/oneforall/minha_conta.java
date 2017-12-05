package com.projeto.aparicio.oneforall;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projeto.aparicio.oneforall.modelos.Usuario;


public class minha_conta extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragmentView = inflater.inflate(R.layout.fragment_minha_conta, container, false);

        Usuario usuario;
        BancoController bct = new BancoController(getContext());

        Cursor cursor = bct.carregaUsuarioPrincipal();

        TextView nome = (TextView)myFragmentView.findViewById(R.id.textView);
        TextView ipv4 = (TextView)myFragmentView.findViewById(R.id.textView5);
        TextView caminhoD = (TextView)myFragmentView.findViewById(R.id.textView7);
        TextView caminhoP = (TextView)myFragmentView.findViewById(R.id.textView6);
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        ipv4.setText(cursor.getString(cursor.getColumnIndexOrThrow("IPv4")));
        caminhoD.setText(cursor.getString(cursor.getColumnIndexOrThrow("caminhoD")));
        caminhoP.setText(cursor.getString(cursor.getColumnIndexOrThrow("caminhoP")));
        return myFragmentView;
    }
}
