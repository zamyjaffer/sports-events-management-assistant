package com.example.wk001764.finalproject.Match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wk001764.finalproject.Messaging.ChatActivity;
import com.example.wk001764.finalproject.R;

/**
 * Created by wk001764 on 07/02/2018.
 */

public class MatchViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mMatchId, mMatchName;
    public ImageView mMatchImage;
    public MatchViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mMatchId = (TextView) itemView.findViewById(R.id.matchId);
        mMatchName = (TextView) itemView.findViewById(R.id.matchName);

        mMatchImage = (ImageView) itemView.findViewById(R.id.matchImage);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("matchId", mMatchId.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}