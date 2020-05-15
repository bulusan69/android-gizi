package com.example.taammar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taammar.R;
import com.example.taammar.model.Produk;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private Context context;
    private List<Produk> itemProdukCart;

    public ProductListAdapter(List<Produk> itemProdukCart, Context context) {
        this.context = context;
        this.itemProdukCart = itemProdukCart;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_produk_item, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(context, itemProdukCart.get(position));
    }

    @Override
    public int getItemCount() {
        return itemProdukCart.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewUpdate, jmlProdukTextView;

        ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textName);
            textViewUpdate = (TextView) itemView.findViewById(R.id.textUpdate);
            jmlProdukTextView = (TextView) itemView.findViewById(R.id.jml_produk);
        }

        public void bind(Context context, final Produk produk) {
            textViewName.setText(produk.getNamaProduk());
            textViewUpdate.setText("Kandungan Gizi");
            jmlProdukTextView.setText(produk.getJmlProduk() + "x");
        }
    }
}
