package com.example.abc.Author;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.R;
import com.example.abc.ui.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

public class CreateAuthorFragment extends Fragment {
    private EditText textViewFirstName;
    private EditText textViewLastName;
    private Button buttonCreate;

    public CreateAuthorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.create_author_fragment, container, false);
        textViewFirstName = root.findViewById(R.id.editTextFirstName);
        textViewLastName = root.findViewById(R.id.editTextLastName);
        buttonCreate = root.findViewById(R.id.buttonCreateAuthor);
        buttonCreate.setOnClickListener(v -> {
            // Create a new author
            String firstName = textViewFirstName.getText().toString();
            String lastName = textViewLastName.getText().toString();
            // Create a new author
            CreateAnAuthor(firstName, lastName);
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, new AuthorsListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return root;
    }

    public void CreateAnAuthor(String firstName, String lastName) {
        // Create a new author
        String URL = "http://192.168.10.52:3000/authors";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    // Réponse de la requête POST
                    Log.d("Response", response);
                },
                error -> {
                    // Gestion des erreurs
                    Log.e("Error", "Erreur lors de la requête POST: " + error.toString());
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Paramètres à envoyer dans la requête POST
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstName);
                params.put("lastname", lastName);
                // Ajoutez d'autres paramètres si nécessaire
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
