package com.example.abc.Author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.abc.R;

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
        return root;
    }
}
