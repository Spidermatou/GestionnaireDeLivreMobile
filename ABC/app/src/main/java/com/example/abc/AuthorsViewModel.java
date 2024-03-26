package com.example.abc;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
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


    // V1 en dur
    private void loadData(Context context) throws IOException, JSONException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.rawauthors);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null ) {
            sb.append( line );
            sb.append( '\n' );
        }
        authors.setValue(new JSONArray(sb.toString()));
    }

}
