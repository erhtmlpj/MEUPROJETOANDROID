package com.projeto.aparicio.oneforall;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Aparicio on 29/11/2017.
 */

public class membroRedeHolder extends RecyclerView.ViewHolder {
    public Button nomeUsuario;


    public membroRedeHolder (View itemView) {
        super(itemView);
        nomeUsuario = (Button) itemView.findViewById(R.id.nomeUsuario);
        setIsRecyclable(false);
    }
}
