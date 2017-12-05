package com.projeto.aparicio.oneforall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.projeto.aparicio.oneforall.modelos.Rede;

import java.util.List;

/**
 * Created by Aparicio on 22/11/2017.
 */

public class redeAdapter extends RecyclerView.Adapter<redeHolder>{
    private final List<Rede> redes;
    private Context context;

    public redeAdapter(List<Rede> redes, Context context ) {
        this.redes = redes;
        this.context =context;
    }


    @Override
    public redeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new redeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false));
    }

    @Override
    public void onBindViewHolder(redeHolder holder, final int position) {
        holder.nomeRede.setText(redes.get(position).getNome());

        //final Rede rede = redes.get(position);
        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação").setMessage("Tem certeza que deseja excluir este cliente?").setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Rede rede = redes.get(position);
                                BancoController bct = new BancoController(view.getContext());
                                bct.deletaRegistroRede(rede.getCodigo());

                                removerCliente(rede);

                            }
                        }).setNegativeButton("Cancelar", null).create().show();
            }
        });

        holder.nomeRede.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                Rede rede = redes.get(position);
                BancoController bct = new BancoController(view.getContext());
                bct.setRedeDesativa();
                bct.setRedeAtiva(rede);


                CharSequence text = "Sua Rede Atual é esta a partir de agr:"+rede.getNome();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return redes != null ? redes.size() : 0;
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }


    public void removerCliente(Rede rede){
        int position = redes.indexOf(rede);
        redes.remove(position);
        notifyItemRemoved(position);
    }
}
