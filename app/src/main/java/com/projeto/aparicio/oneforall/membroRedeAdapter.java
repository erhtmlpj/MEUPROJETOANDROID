package com.projeto.aparicio.oneforall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.projeto.aparicio.oneforall.modelos.MembroRede;
import com.projeto.aparicio.oneforall.modelos.Rede;

import java.util.List;

/**
 * Created by Aparicio on 29/11/2017.
 */

public class membroRedeAdapter extends RecyclerView.Adapter<membroRedeHolder> {
    private final List<MembroRede> membroRedes;
    private Context context;

    public membroRedeAdapter(List<MembroRede> membroredes, Context context ) {
        this.membroRedes = membroredes;
        this.context =context;
    }


    @Override
    public membroRedeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new membroRedeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent,false));
    }

    @Override
    public void onBindViewHolder(membroRedeHolder holder, int position) {
        holder.nomeUsuario.setText(membroRedes.get(position).getNome());

    }

    @Override
    public int getItemCount() {
        return membroRedes != null ? membroRedes.size() : 0;
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
}
