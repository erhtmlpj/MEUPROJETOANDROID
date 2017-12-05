package com.projeto.aparicio.oneforall;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.aparicio.oneforall.modelos.MembroRede;
import com.projeto.aparicio.oneforall.modelos.Rede;
import com.projeto.aparicio.oneforall.modelos.Usuario;

public class Criar_Entrar extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        final android.view.View myFragmentView = inflater.inflate(R.layout.activity_criar_rede, container, false);

        Button criar = (Button)myFragmentView.findViewById(R.id.button10);

        View.OnClickListener criaRede = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nome = (EditText)myFragmentView.findViewById(R.id.editText2);
                EditText senha = (EditText)myFragmentView.findViewById(R.id.editText3);
                EditText senha2 = (EditText)myFragmentView.findViewById(R.id.editText);

                Rede rede = new Rede();
                rede.setNome(nome.getText().toString());

                Usuario usuario = new Usuario();
                BancoController bct = new BancoController(getContext());
                Cursor cursor = bct.carregaUsuarioPrincipal();

                String s1,s2;
                s1 = senha.getText().toString();
                s2 = senha2.getText().toString();

                if(s1.equals(s2))
                {
                    rede.setSenha(senha.getText().toString());
                }

                rede.setNomeAdmin(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                rede.setIPv4Admin(cursor.getString(cursor.getColumnIndexOrThrow("IPv4")));

                rede.setCodigo(cursor.getString(cursor.getColumnIndexOrThrow("codigoUsuario"))+rede.getIPv4Admin()+rede.getNome());
                rede.setnMembros(1);

                String result = bct.insereDadosRede(rede);

                MembroRede membroRede = new MembroRede();

                membroRede.setCodigoMembroRede(rede.getCodigo()+""+cursor.getString(cursor.getColumnIndexOrThrow("codigoUsuario")));
                membroRede.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                membroRede.setIPv4(cursor.getString(cursor.getColumnIndexOrThrow("IPv4")));
                membroRede.setCodigoRede(rede.getCodigo());

                String retorno = bct.insereDadosMembroRede(membroRede);
                System.out.println(retorno);

                Fragment fragment= new minha_rede();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        };

        criar.setOnClickListener(criaRede);
        return myFragmentView;
    }
}
