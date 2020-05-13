package com.example.taammar.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taammar.R;
import com.example.taammar.model.MappingGizi;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private MappingGizi mMappingGizi;
    private TextView textViewVitATitle;
    private TextView textViewVitAValue;
    private TextView textViewVitB9Title;
    private TextView textViewVitB9Value;
    private TextView textViewVitDTitle;
    private TextView textViewVitDValue;
    private TextView textViewVitETitle;
    private TextView textViewVitEValue;
    private TextView textViewVitKTitle;
    private TextView textViewVitKValue;
    private TextView textViewVitB1Title;
    private TextView textViewVitB1Value;
    private TextView textViewVitB2Title;
    private TextView textViewVitB2Value;
    private TextView textViewVitB3Title;
    private TextView textViewVitB3Value;
    private TextView textViewVitB5Title;
    private TextView textViewVitB5Value;
    private TextView textViewVitB6Title;
    private TextView textViewVitB6Value;
    private TextView textViewVitHTitle;
    private TextView textViewVitHValue;
    private TextView textViewVitB12Title;
    private TextView textViewVitB12Value;
    private TextView textViewVitCTitle;
    private TextView textViewVitCValue;
    private BarChart mBarChart;
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
        textViewVitB9Title = view.findViewById(R.id.tv_VitB9Title);
        textViewVitB9Value = view.findViewById(R.id.tv_VitB9Value);
        textViewVitDTitle = view.findViewById(R.id.tv_VitDTitle);
        textViewVitDValue = view.findViewById(R.id.tv_VitDValue);
        textViewVitETitle = view.findViewById(R.id.tv_VitETitle);
        textViewVitEValue = view.findViewById(R.id.tv_VitEValue);
        textViewVitKTitle = view.findViewById(R.id.tv_VitKTitle);
        textViewVitKValue = view.findViewById(R.id.tv_VitKValue);
        textViewVitB1Title = view.findViewById(R.id.tv_VitB1Title);
        textViewVitB1Value = view.findViewById(R.id.tv_VitB1Value);
        textViewVitB2Title = view.findViewById(R.id.tv_VitB2Title);
        textViewVitB2Value = view.findViewById(R.id.tv_VitB2Value);
        textViewVitB3Title = view.findViewById(R.id.tv_VitB3Title);
        textViewVitB3Value = view.findViewById(R.id.tv_VitB3Value);
        textViewVitB6Title = view.findViewById(R.id.tv_VitB6Title);
        textViewVitB6Value = view.findViewById(R.id.tv_VitB6Value);
        textViewVitB5Title = view.findViewById(R.id.tv_VitB5Title);
        textViewVitB5Value = view.findViewById(R.id.tv_VitB5Value);
        textViewVitHTitle = view.findViewById(R.id.tv_VitHTitle);
        textViewVitHValue = view.findViewById(R.id.tv_VitHValue);
        textViewVitB12Title = view.findViewById(R.id.tv_VitB12Title);
        textViewVitB12Value = view.findViewById(R.id.tv_VitB12Value);
        textViewVitCTitle = view.findViewById(R.id.tv_VitCTitle);
        textViewVitCValue = view.findViewById(R.id.tv_VitCValue);
        mBarChart = view.findViewById(R.id.chart);


        textViewVitATitle.setText("Vit A");
        textViewVitAValue.setText(mMappingGizi.getVitA() + " Mg");
        textViewVitB9Title.setText("Vit B9");
        textViewVitB9Value.setText(mMappingGizi.getVitB9() + " Mg");
        textViewVitDTitle.setText("Vit D");
        textViewVitDValue.setText(mMappingGizi.getVITD() + " Mg");
        textViewVitETitle.setText("Vit E");
        textViewVitEValue.setText(mMappingGizi.getVITE() + " Mg");
        textViewVitKTitle.setText("Vit K");
        textViewVitKValue.setText(mMappingGizi.getVitK() + " Mg");
        textViewVitB1Title.setText("Vit B1");
        textViewVitB1Value.setText(mMappingGizi.getVitB1() + " Mg");
        textViewVitB2Title.setText("Vit B2");
        textViewVitB2Value.setText(mMappingGizi.getVitB2() + " Mg");
        textViewVitB3Title.setText("Vit B3");
        textViewVitB3Value.setText(mMappingGizi.getVitB3() + " Mg");
        textViewVitB5Title.setText("Vit B5");
        textViewVitB5Value.setText(mMappingGizi.getVitB5() + " Mg");
        textViewVitB6Title.setText("Vit B6");
        textViewVitB6Value.setText(mMappingGizi.getVitB6() + " Mg");
        textViewVitHTitle.setText("Vit H");
        textViewVitHValue.setText(mMappingGizi.getVitH() + " Mg");
        textViewVitB12Title.setText("Vit B12");
        textViewVitB12Value.setText(mMappingGizi.getVitB12() + " Mg");
        textViewVitCTitle.setText("Vit C");
        textViewVitCValue.setText(mMappingGizi.getVitC() + " Mg");
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Todo : Ganti value berdasarkan produk
        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        NoOfEmp.add(new BarEntry(1578f, 8));
        NoOfEmp.add(new BarEntry(1695f, 9));
        NoOfEmp.add(new BarEntry(1695f, 10));
        NoOfEmp.add(new BarEntry(1695f, 11));
        NoOfEmp.add(new BarEntry(1695f, 12));

        ArrayList xAxis = new ArrayList();
        xAxis.add("A");
        xAxis.add("D");
        xAxis.add("E");
        xAxis.add("K");
        xAxis.add("B1");
        xAxis.add("B2");
        xAxis.add("B3");
        xAxis.add("B5");
        xAxis.add("B6");
        xAxis.add("H");
        xAxis.add("B9");
        xAxis.add("B12");
        xAxis.add("C");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Vitamin (Mg)");
        mBarChart.animateY(5000);
        BarData data = new BarData(xAxis, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mBarChart.setDescription("");
        mBarChart.setData(data);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM.BOTTOM);
        mBarChart.getXAxis().setLabelsToSkip(0);
        mBarChart.invalidate();

    }

}
