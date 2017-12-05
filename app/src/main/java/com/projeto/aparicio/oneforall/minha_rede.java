package com.projeto.aparicio.oneforall;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.projeto.aparicio.oneforall.modelos.Rede;

import java.util.List;


public class minha_rede extends Fragment {

    public View myF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragmentView = inflater.inflate(R.layout.fragment_minha_rede, container, false);

        myF = myFragmentView;
        //SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getContext(),R.layout.redes_layout,cursor,campos,idViews,0);

       // ListView redes = (ListView)myFragmentView.findViewById(R.id.container_rede);
       //redes.setAdapter(adaptador);

        configurarRecycler();

        return myFragmentView;
    }

    public RecyclerView recyclerView;
    public redeAdapter adapter;

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)myF.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        BancoController bct = new BancoController(getContext());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        adapter = new redeAdapter(bct.listarRedes(),getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

}
