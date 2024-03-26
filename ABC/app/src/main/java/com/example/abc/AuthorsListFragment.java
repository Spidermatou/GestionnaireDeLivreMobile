package com.example.abc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorsListFragment extends Fragment implements HasClickableItem {

    public AuthorsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment authorsList.
     */
    public static AuthorsListFragment newInstance() {
        return new AuthorsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors_list, container, false);
    }

    @Override
    public void onClick(int index) { // TODO - is just a test yet
        Toast toast = Toast.makeText(getActivity(),"CLICKED",Toast.LENGTH_SHORT);
        toast.show();
    }
}