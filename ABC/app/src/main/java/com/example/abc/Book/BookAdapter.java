package com.example.abc.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

public class BookAdapter extends RecyclerView.Adapter{
    private JSONArray BookDataSet;
    private Context context;
    public BookAdapter(JSONArray dataSet) {
        BookDataSet = dataSet;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_view_holder, parent, false);
        context = parent.getContext();

        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //try {

        try {
            ((BookHolder) holder).getTextViewBook().setText(BookDataSet.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //} catch (JSONException e) {
          //  e.printStackTrace();
        //}
    }

    @Override
    public int getItemCount() {
        return BookDataSet.length();
    }
}
