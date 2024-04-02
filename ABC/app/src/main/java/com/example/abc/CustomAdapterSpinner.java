package com.example.abc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abc.Author.Author;

import java.util.ArrayList;

public class CustomAdapterSpinner extends ArrayAdapter<Author> {
    private Context context;
    private ArrayList<Author> authors;
    private LayoutInflater inflater;

    public CustomAdapterSpinner(Context context, ArrayList<Author> authors) {
        super(context, 0, authors);
        this.context = context;
        this.authors = authors;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        TextView textView = view.findViewById(android.R.id.text1);
        Author author = authors.get(position);
        textView.setText(author.getAuthorName());
        textView.setTextSize(24); // Définir la taille du texte ici
        return view;
    }
}
