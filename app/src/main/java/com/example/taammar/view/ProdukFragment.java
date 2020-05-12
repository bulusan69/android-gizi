package com.example.taammar.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taammar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdukFragment extends Fragment {

    public ProdukFragment() {
        // Required empty public constructor
    }

    public static ProdukFragment newInstance() {
        ProdukFragment fragment = new ProdukFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produk, container, false);


    }
}
