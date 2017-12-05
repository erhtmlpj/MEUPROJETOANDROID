package com.projeto.aparicio.oneforall;

        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.DividerItemDecoration;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import com.projeto.aparicio.oneforall.modelos.Rede;

        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.InetAddress;
        import java.net.NetworkInterface;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.net.SocketException;
        import java.util.Enumeration;

public class rede_Tela extends Fragment {

    TextView info, infoip, msg;
    String message = "";
    ServerSocket serverSocket;
    public View myF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.rede, container, false);

        TextView nome = (TextView)myFragmentView.findViewById(R.id.textView25);
        TextView senha= (TextView)myFragmentView.findViewById(R.id.textView23);
        TextView ip   = (TextView)myFragmentView.findViewById(R.id.textView24);

        BancoController bct = new BancoController(getContext());
        Rede rede = new Rede();
        Cursor cursor = bct.getRedeAtiva();

        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        senha.setText(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        ip.setText(cursor.getString(cursor.getColumnIndexOrThrow("IPv4Admin")));

        myF = myFragmentView;

        configurarRecycler();

        return myFragmentView;
    }

    public RecyclerView recyclerView;
    public membroRedeAdapter adapter;

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)myF.findViewById(R.id.membrosdaRede);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        BancoController bct = new BancoController(getContext());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        adapter = new membroRedeAdapter(bct.listarMembroRede(),getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }



    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 8080;
        int count = 0;

        @Override
        public void run() {
            Socket socket = null;
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;

            try {
                serverSocket = new ServerSocket(SocketServerPORT);
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        info.setText("I'm waiting here: "
                                + serverSocket.getLocalPort());
                    }
                });

                while (true) {
                    socket = serverSocket.accept();
                    dataInputStream = new DataInputStream(
                            socket.getInputStream());
                    dataOutputStream = new DataOutputStream(
                            socket.getOutputStream());

                    String messageFromClient = "";

                    //If no message sent from client, this code will block the program
                    messageFromClient = dataInputStream.readUTF();

                    count++;
                    message += "#" + count + " from " + socket.getInetAddress()
                            + ":" + socket.getPort() + "\n"
                            + "Msg from client: " + messageFromClient + "\n";

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            msg.setText(message);
                        }
                    });

                    String msgReply = "Hello from Android, you are #" + count;
                    dataOutputStream.writeUTF(msgReply);

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                final String errMsg = e.toString();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        msg.setText(errMsg);
                    }
                });

            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }

                }

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
    }



}
