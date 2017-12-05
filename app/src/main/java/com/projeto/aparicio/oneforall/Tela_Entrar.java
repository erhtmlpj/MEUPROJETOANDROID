package com.projeto.aparicio.oneforall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.aparicio.oneforall.dao.usuarioDAO;
import com.projeto.aparicio.oneforall.modelos.Usuario;

import java.io.File;
import java.util.Locale;
import java.util.UUID;


public class Tela_Entrar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragmentView = inflater.inflate(R.layout.activity_tela_entrar, container, false);

        Button entrar = (Button)myFragmentView.findViewById(R.id.button10);

        View.OnClickListener cadUsuario = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pega o id do android

                BancoController bct = new BancoController(getContext());
                Usuario usuario = new Usuario();

                String android_id = UUID.randomUUID().toString();

                usuario.setCodigoUsuario(android_id);

                File folder = new File(getContext().getFilesDir(),"PublicoO4A");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                usuario.setCaminhoP(folder.getAbsolutePath());

                File folder2 = new File(getContext().getFilesDir(),"DownloadO4A");
                if (!folder2.exists()) {
                    folder2.mkdir();
                }
                usuario.setCaminhoD(folder2.getAbsolutePath());

                WifiManager wifiMan = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInf = wifiMan.getConnectionInfo();
                int ipAddress = wifiInf.getIpAddress();
                String ip = String.format(Locale.ENGLISH,"%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));

                usuario.setIPv4(ip);
                EditText Nome = (EditText)myFragmentView.findViewById(R.id.editText);

                usuario.setNome(Nome.getText().toString());

                usuario.setPrincipal("sim");

                bct.insereDadosUsuario(usuario);

                Fragment fragment= new minha_conta();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        };
        entrar.setOnClickListener(cadUsuario);
        return myFragmentView;
    }
}