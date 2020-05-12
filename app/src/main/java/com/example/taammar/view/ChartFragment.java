package com.example.taammar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taammar.R;
import com.example.taammar.model.MappingGizi;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private MappingGizi mMappingGizi;
    TextView textViewVitATitle;
    TextView textViewVitAValue;

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance(MappingGizi mappinggizi) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putSerializable("mappinggizi",mappinggizi);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        textViewVitATitle = view.findViewById(R.id.tv_VitATitle);
        textViewVitAValue = view.findViewById(R.id.tv_VitAValue);
        textViewVitATitle.setText("Vit A");
        textViewVitAValue.setText(mMappingGizi.getVitA());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMappingGizi = (MappingGizi)getArguments().getSerializable("mappinggizi");
            Log.e("cek value",mMappingGizi.getVitA());
        }

    }


}
