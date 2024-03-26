package com.example.abc;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AuthorsViewModel extends AndroidViewModel {

    public AuthorsViewModel(@NonNull Application application) {
        super(application);
        try {
            loadData(application.getApplicationContext());
        }
        catch (Exception e){
            System.out.println("Error during data loading");
        }
    }

    private final MutableLiveData<JSONArray> authors =
            new MutableLiveData<>(new JSONArray());


    // V1.0 en dur
    private void loadData(Context context) throws IOException, JSONException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.rawauthors);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // get entire text
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ( (line =  reader.readLine()) != null  ){
            stringBuilder.append(line);
        }

        // parse to json
        JSONArray data = new JSONArray(stringBuilder.toString());

        System.out.println(data); // test
        authors.setValue(data);
    }

}
