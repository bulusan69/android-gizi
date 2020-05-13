package com.example.taammar.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taammar.R;
import com.example.taammar.db.DataHelper;
import com.example.taammar.model.Produk;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdukFragment extends Fragment {

    private DataHelper dataHelper;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHelper = DataHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produk, container, false);
        TextView namaProductTV = view.findViewById(R.id.namaProduk);
        Produk produk = dataHelper.getAllProduk().get(1);
        namaProductTV.setText(produk.getNamaProduk());
        return view;
    }
}
