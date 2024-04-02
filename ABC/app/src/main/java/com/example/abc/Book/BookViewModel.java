package com.example.abc.Book;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.android.volley.Request;

public class BookViewModel extends AndroidViewModel {
    private String AdresseURL = "http://192.168.10.52:3000/";
    private MutableLiveData<JSONArray> book;

    public MutableLiveData<JSONArray> getBook() {
        return book;
    }


    private void loadData(Context context) throws Exception {
        System.out.println("On est la");
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = AdresseURL + "books";
        // Request a string response from the provided URL.
        System.out.println("Pas de probleme ici");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    book.setValue(response);
                    System.out.println("IL y a pas de probleme ici");
                    System.out.println(response.toString());
                },
                error -> {
                    // Gérer l'erreur ici
                    //Afficher l'erreur
                    System.out.println(error.getMessage());

                    book.setValue(new JSONArray());
                    System.out.println("Il y a un probleme ici");
                });
        queue.add(jsonArrayRequest);


    }
    public BookViewModel(Context context) throws Exception {
        super((Application) context.getApplicationContext());
        book = new MutableLiveData<>();
        try {
            loadData(context);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ici, par exemple, en affichant un message d'erreur ou en initiant book avec un JSONArray vide
            book.setValue(new JSONArray());
        }
    }
}
