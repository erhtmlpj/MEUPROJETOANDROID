package com.projeto.aparicio.oneforall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.aparicio.oneforall.conexao.Client;
import com.projeto.aparicio.oneforall.modelos.Rede;
import com.projeto.aparicio.oneforall.modelos.Requisito;
import com.projeto.aparicio.oneforall.modelos.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Rede_Entrar extends Fragment {
    String msgToServer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View myFragmentView = inflater.inflate(R.layout.activity_entrar_rede, container, false);

        Button entrar = (Button) myFragmentView.findViewById(R.id.button21);

        View.OnClickListener conectar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nome = (EditText) myFragmentView.findViewById(R.id.editText2);
                EditText ipv4 = (EditText) myFragmentView.findViewById(R.id.editText3);
                EditText senha = (EditText) myFragmentView.findViewById(R.id.editText);
                //Client client = new Client(ipv4.getText().toString(),4444,senha.getText().toString());
                //client.execute();

                //String tMsg = welcomeMsg.getText().toString();
                /*if(tMsg.equals("")){
                    tMsg = null;
                    Toast.makeText(MainActivity.this, "No Welcome Msg sent", Toast.LENGTH_SHORT).show();
                }*/

                MyClientTask myClientTask = new MyClientTask(ipv4.getText().toString(), 4444,senha.getText().toString(),nome.getText().toString());
                myClientTask.execute();



            }
        };

        return myFragmentView;
    }

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "";
        String TextResponse;
        String password;
        String name;
        Requisito requisito = new Requisito();
        MyClientTask(String addr, int port, String s,String Name) {

            dstAddress = addr;
            dstPort = port;
            password = s;
            name = Name;
            requisito.setNome(name);
            requisito.setSenha(password);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Socket socket = null;
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                if (msgToServer != null) {
                    objectOutputStream.writeObject(requisito);
                }

                Rede rede = new Rede();
                rede = (Rede) objectInputStream.readObject();

                BancoController bct = new BancoController(getContext());
                bct.insereDadosRede(rede);
                Usuario usuario =  new Usuario();
                for(int x=0;x<rede.getnMembros();x++)
                {
                    usuario = (Usuario) objectInputStream.readObject();
                    bct.insereDadosUsuario(usuario);

                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            TextResponse = response;
            super.onPostExecute(result);
        }

        public String getTextResponse() {
            return TextResponse;
        }
    }

}

