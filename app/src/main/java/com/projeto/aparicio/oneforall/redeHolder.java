package com.projeto.aparicio.oneforall;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Aparicio on 22/11/2017.
 */

public class redeHolder extends RecyclerView.ViewHolder {

    public TextView nomeRede;
    public ImageButton btnExcluir;

    public redeHolder(View itemView) {
        super(itemView);
        nomeRede = (TextView) itemView.findViewById(R.id.nomeRede);
        btnExcluir = (ImageButton)itemView.findViewById(R.id.btnDelete);
        setIsRecyclable(false);
    }
}
