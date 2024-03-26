package com.example.abc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AuthorViewHolder extends RecyclerView.ViewHolder {

    private final TextView authorName;

    public AuthorViewHolder(@NonNull View itemView) {
        super(itemView);
        authorName = (TextView) itemView.findViewById(R.id.authorName);
    }

    public TextView getauthorName() {
        return authorName;
    }

}
