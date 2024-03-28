package com.example.abc.Author;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.Book.BookAdapter;
import com.example.abc.Book.DescriptionFragment;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorViewHolder extends RecyclerView.ViewHolder {
    private JSONArray authorDataSet;
    private  TextView authorName;
    private FragmentManager fragmentManager;

    public AuthorViewHolder(@NonNull View itemView, FragmentManager fragmentManagerParent) {
        super(itemView);
        authorName = (TextView) itemView.findViewById(R.id.authorName);
        this.fragmentManager = fragmentManagerParent;
        authorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorDataSet = ((AuthorsDataAdapter) ((RecyclerView) itemView.getParent()).getAdapter()).getBookDataSet();
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Récupérer les informations sur le livre sélectionné
                    JSONObject selectedAuthor = null;
                    try {
                        selectedAuthor =  authorDataSet.getJSONObject(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Passer les informations au fragment parent
                    if (selectedAuthor != null && itemView.getContext() instanceof FragmentActivity) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Créer et configurer le DescriptionFragment avec les informations sur le livre sélectionné
                        AuthorDescription authorFragment = AuthorDescription.newInstance(selectedAuthor.toString());
                        // Remplacer le contenu du FragmentContainerView par le DescriptionFragment
                        fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, authorFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }

    public TextView getauthorName() {
        return authorName;
    }




}
