package com.example.taammar.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taammar.R;
import com.example.taammar.db.DataHelper;
import com.example.taammar.model.Produk;
import com.example.taammar.view.dialog.AddProductDialog;

import java.util.Map;

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
        Button tambahProductButton = view.findViewById(R.id.tambah_produk);
        Produk produk = dataHelper.getAllProduk().get(1);
        namaProductTV.setText(produk.getNamaProduk());

        tambahProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductDialog addProductDialog = AddProductDialog.newInstance();
                addProductDialog.setListProduk(dataHelper.getAllProduk(), null);

                addProductDialog.show(getFragmentManager(), AddProductDialog.TAG);
            }
        });
        return view;
    }
}
