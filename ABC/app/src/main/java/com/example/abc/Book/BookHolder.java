package com.example.abc.Book;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.R;

public class BookHolder extends RecyclerView.ViewHolder {
    private TextView book;
    public BookHolder(View itemView) {
        super(itemView);
        book = (TextView) itemView.findViewById(R.id.textViewBook);
    }

    public TextView getTextViewBook() {
        return book;
    }




}
