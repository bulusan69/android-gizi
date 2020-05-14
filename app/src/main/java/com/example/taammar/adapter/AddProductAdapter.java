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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.ProductViewHolder> {

    private List<Produk> itemProduk = new ArrayList<>();
    private Map<Produk, Integer> productAddedList = new HashMap<>();
    private Context context;
    private ItemListener itemListener;

    public AddProductAdapter(List<Produk> itemProduk, Context context, ItemListener itemListener) {
        this.itemProduk = itemProduk;
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_produk_item, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(context, itemProduk.get(position), productAddedList, itemListener);
    }

    @Override
    public int getItemCount() {
        return itemProduk.size();
    }

    public Map<Produk, Integer> getProductAddedList() {
        return productAddedList;
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

        public void bind(Context context, final Produk produk, final Map<Produk, Integer> productAddedList, final ItemListener itemListener) {
            textViewName.setText(produk.getNamaProduk());
            textViewUpdate.setText("Kandungan Gizi");
            tambahButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tambahButton.setVisibility(View.GONE);
                    jmlProdukView.setVisibility(View.VISIBLE);
                    jmlEditText.setText("1");
                    productAddedList.put(produk, 1);
                    itemListener.onClicked();
                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decreaseJmlProduk(produk, productAddedList);
                }
            });

            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    increaseJmlProduk(produk, productAddedList);
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

        private void increaseJmlProduk(Produk produk, Map<Produk, Integer> productAddedList) {
            int itemCount = Integer.parseInt(jmlEditText.getText().toString()) + 1;
            jmlEditText.setText(String.valueOf(itemCount));
            productAddedList.put(produk, Integer.parseInt(jmlEditText.getText().toString()));
        }

        private void decreaseJmlProduk(Produk produk, Map<Produk, Integer> productAddedList) {
            int itemCount = Integer.parseInt(jmlEditText.getText().toString()) - 1;
            if (itemCount <= 0) {
                jmlProdukView.setVisibility(View.GONE);
                tambahButton.setVisibility(View.VISIBLE);
            } else {
                jmlEditText.setText(String.valueOf(itemCount));
                productAddedList.put(produk, Integer.parseInt(jmlEditText.getText().toString()));
            }
        }
    }

    public interface ItemListener{
        void onClicked();
    }
}
