package com.example.abc.Author;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.Author.AuthorViewHolder;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Connait le view holder et modifie ses elements graphiques
 */
public class AuthorsDataAdapter extends RecyclerView.Adapter {
    private  JSONArray authorsData;

    private Context appContext;
    private FragmentManager fragmentManager;

    public AuthorsDataAdapter(JSONArray array, FragmentManager fragmentManager){
        super();
        authorsData = array;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_author, parent, false);

        appContext = parent.getContext();

        return new AuthorViewHolder(view, fragmentManager);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
           String firstname;
           String lastname;
            try {
                // retrieve name
                lastname = authorsData.getJSONObject(position).getString("lastname");
                firstname = authorsData.getJSONObject(position).getString("firstname");
                ((AuthorViewHolder) holder).getauthorName().setText(lastname + " " + firstname);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public int getItemCount() {
        System.out.println(authorsData.length());
        return authorsData.length();
    }

    public JSONArray getBookDataSet() {
        return authorsData;
    }
}
