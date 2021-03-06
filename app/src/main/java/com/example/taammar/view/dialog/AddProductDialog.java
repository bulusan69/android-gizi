package com.example.taammar.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taammar.R;
import com.example.taammar.adapter.AddProductAdapter;
import com.example.taammar.helper.CustomBottomSheetDialog;
import com.example.taammar.model.Produk;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class AddProductDialog extends CustomBottomSheetDialog {
    public static final String TAG = "AddProductDialog";

    private AddProductAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private AddProductDialogListener dialogListener;
    private List<Produk> listProduk;
    private List<Produk> itemProdukCart;
    private boolean isSubmit = false;

    public static AddProductDialog newInstance() {
        AddProductDialog fragment = new AddProductDialog();

//        Bundle args = new Bundle();
//        args.putBoolean(EXTRA_MATCH_CONDITIONS_KEY, matchConditions);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog createdDialog = (BottomSheetDialog) super.onCreateDialog(
                savedInstanceState);
        createdDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final BottomSheetBehavior<FrameLayout> behavior = createdDialog.getBehavior();
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }

                        if (newState == BottomSheetBehavior.STATE_HIDDEN)
                        {
                            dismiss();
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    }
                });
            }
        });

        return createdDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_add_product, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_recyclerview);
        ImageButton closeButton = (ImageButton) view.findViewById(R.id.closeSheetButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        final Button submitButton = (Button) view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogListener != null) {
                    dialogListener.onSubmit(itemProdukCart);
                    isSubmit = true;
                }
                dismiss();
            }
        });

        mAdapter = new AddProductAdapter(listProduk, itemProdukCart, getActivity().getApplicationContext(), new AddProductAdapter.ItemListener() {
            @Override
            public void onClicked() {
                if (itemProdukCart.isEmpty()) {
                    submitButton.setEnabled(false);
                } else {
                    submitButton.setEnabled(true);
                }

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        if (itemProdukCart.isEmpty()) {
            submitButton.setEnabled(false);
        } else {
            submitButton.setEnabled(true);
        }

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        if (dialogListener!= null && !isSubmit && itemProdukCart.isEmpty()) {
            dialogListener.onCancel();
        }
        super.onDismiss(dialog);
    }

    public void setListProduk(List<Produk> listProduk, List<Produk> itemProdukCart) {
        this.listProduk = listProduk;
        this.itemProdukCart = itemProdukCart;
    }

    public void setDialogListener(AddProductDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public interface AddProductDialogListener{
        void onSubmit(List<Produk> listAddProduct);
        void onCancel();
    }
}
