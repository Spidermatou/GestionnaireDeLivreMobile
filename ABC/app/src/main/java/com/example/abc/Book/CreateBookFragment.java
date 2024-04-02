package com.example.abc.Book;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.Author.Author;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.abc.CustomAdapterSpinner;
import com.example.abc.ui.home.HomeFragment;

public class CreateBookFragment extends Fragment {
    private String AdresseURL = "http://192.168.10.52:3000/";
    private EditText textViewTitle;
    private Spinner spinner;
    private Button buttonCreate;
    private Button back;
    private FragmentManager fragmentManager;
    public CreateBookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        RequestQueue queue = Volley.newRequestQueue(getContext());
        ArrayList<Author> authorsList = new ArrayList<>();

        String url = AdresseURL + "authors";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject authorObject = response.getJSONObject(i);
                            int id = authorObject.getInt("id");
                            String name = authorObject.getString("firstname") + " " + authorObject.getString("lastname");
                            // Création d'un objet Auteur et ajout à la liste des auteurs
                            authorsList.add(new Author(id, name));
                        }

                        // Création de l'adaptateur pour le Spinner
                        CustomAdapterSpinner adapter = new CustomAdapterSpinner(getContext(), authorsList);
                        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Gérer l'erreur de la requête
                });



        queue.add(request);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.create_book_fragment, container, false);
        textViewTitle = root.findViewById(R.id.editTextTitle);
        spinner = root.findViewById(R.id.spinnerAuthors);
        buttonCreate = root.findViewById(R.id.buttonSubmit);
        back = root.findViewById(R.id.buttonBackBook);


        buttonCreate.setOnClickListener(v -> {
            // Create a new book
            String title = textViewTitle.getText().toString();
            // Create a new book
            Author author = (Author) spinner.getSelectedItem();
            CreateABook(title, author.getAuthorId());

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerView, new BookFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        back.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerView, new BookFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return root;
    }

    public void CreateABook(String title, Integer author) {
        // Create a new book


    }
}
