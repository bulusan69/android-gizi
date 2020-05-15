package com.example.taammar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taammar.R;
import com.example.taammar.model.Produk;

import java.util.ArrayList;
import java.util.List;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.ProductViewHolder> {

    private List<Produk> itemProduk;
    private Context context;
    private ItemListener itemListener;
    private List<Produk> itemProdukCart;

    public AddProductAdapter(List<Produk> itemProduk, List<Produk> itemProdukCart, Context context, ItemListener itemListener) {
        this.itemProduk = itemProduk;
        this.context = context;
        this.itemListener = itemListener;
        this.itemProdukCart = itemProdukCart;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_produk_item, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(context, itemProduk.get(position), itemProdukCart, itemListener);
    }

    @Override
    public int getItemCount() {
        return itemProduk.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewUpdate, decreaseButton, increaseButton, jmlEditText;
        Button tambahButton;
        View jmlProdukView;

        ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textName);
            textViewUpdate = (TextView) itemView.findViewById(R.id.textUpdate);
            tambahButton = (Button) itemView.findViewById(R.id.tambah_produk);
            jmlProdukView = (View) itemView.findViewById(R.id.jml_produk_container);
            decreaseButton = (TextView) itemView.findViewById(R.id.decrease);
            increaseButton = (TextView) itemView.findViewById(R.id.increase);
            jmlEditText = (TextView) itemView.findViewById(R.id.jml_produk);
        }

        public void bind(Context context, final Produk produk, final List<Produk> productAddedList, final ItemListener itemListener) {
            textViewName.setText(produk.getNamaProduk());
            textViewUpdate.setText("Kandungan Gizi");
            jmlEditText.setText(String.valueOf(produk.getJmlProduk()));

            if (produk.getJmlProduk() > 0) {
                jmlProdukView.setVisibility(View.VISIBLE);
                tambahButton.setVisibility(View.GONE);
            } else {
                jmlProdukView.setVisibility(View.GONE);
                tambahButton.setVisibility(View.VISIBLE);
            }

            tambahButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tambahButton.setVisibility(View.GONE);
                    jmlProdukView.setVisibility(View.VISIBLE);
                    jmlEditText.setText(String.valueOf(0));
                    increaseJmlProduk(produk, productAddedList);
                    itemListener.onClicked();
                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decreaseJmlProduk(produk, productAddedList);
                    itemListener.onClicked();
                }
            });

            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    increaseJmlProduk(produk, productAddedList);
                    itemListener.onClicked();
                }
            });

//            Glide
//                    .with(context)
//                    .load(news.getEnclosure().getAttributes().getUrl())
//                    .transform(new PositionedCropTransformation(context, 0.5f, 0))
//                    .dontAnimate()
//                    .placeholder(R.drawable.placeholder)
//                    .into(holder.imageView);
        }

        private void increaseJmlProduk(Produk produk, List<Produk> productAddedList) {
            int itemCount = Integer.parseInt(jmlEditText.getText().toString()) + 1;
            jmlEditText.setText(String.valueOf(itemCount));
            produk.setJmlProduk(itemCount);
            if (productAddedList.contains(produk)) {
                productAddedList.set(productAddedList.indexOf(produk), produk);
            } else {
                productAddedList.add(produk);
            }
            if (itemCount > 99) {
                increaseButton.setEnabled(false);
            }
        }

        private void decreaseJmlProduk(Produk produk, List<Produk> productAddedList) {
            int itemCount = Integer.parseInt(jmlEditText.getText().toString()) - 1;
            produk.setJmlProduk(itemCount);

            if (itemCount <= 0) {
                jmlProdukView.setVisibility(View.GONE);
                tambahButton.setVisibility(View.VISIBLE);
                productAddedList.remove(produk);
            } else {
                jmlEditText.setText(String.valueOf(itemCount));
                if (productAddedList.contains(produk)) {
                    productAddedList.set(productAddedList.indexOf(produk), produk);
                } else {
                    productAddedList.add(produk);
                }
            }
        }
    }

    public interface ItemListener{
        void onClicked();
    }
}
