package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taammar.R;
import com.example.taammar.adapter.ViewPagerAdapter;
import com.example.taammar.db.DataHelper;
import com.example.taammar.model.MappingGizi;
import com.example.taammar.model.Produk;
import com.example.taammar.view.dialog.AddProductDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Map;

public class ProdukChartActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    private DataHelper dataHelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_chart);

        MappingGizi mappingGizi =  (MappingGizi) getIntent().getSerializableExtra("mappinggizi");
        dataHelper = DataHelper.getInstance(this);
        Button tambahProductButton = findViewById(R.id.tambah_produk);
        Produk produk = dataHelper.getAllProduk().get(1);
        textViewVitATitle = findViewById(R.id.tv_VitATitle);
        textViewVitAValue = findViewById(R.id.tv_VitAValue);
        textViewVitB9Title = findViewById(R.id.tv_VitB9Title);
        textViewVitB9Value = findViewById(R.id.tv_VitB9Value);
        textViewVitDTitle = findViewById(R.id.tv_VitDTitle);
        textViewVitDValue = findViewById(R.id.tv_VitDValue);
        textViewVitETitle = findViewById(R.id.tv_VitETitle);
        textViewVitEValue = findViewById(R.id.tv_VitEValue);
        textViewVitKTitle = findViewById(R.id.tv_VitKTitle);
        textViewVitKValue = findViewById(R.id.tv_VitKValue);
        textViewVitB1Title = findViewById(R.id.tv_VitB1Title);
        textViewVitB1Value = findViewById(R.id.tv_VitB1Value);
        textViewVitB2Title = findViewById(R.id.tv_VitB2Title);
        textViewVitB2Value = findViewById(R.id.tv_VitB2Value);
        textViewVitB3Title = findViewById(R.id.tv_VitB3Title);
        textViewVitB3Value = findViewById(R.id.tv_VitB3Value);
        textViewVitB6Title = findViewById(R.id.tv_VitB6Title);
        textViewVitB6Value = findViewById(R.id.tv_VitB6Value);
        textViewVitB5Title = findViewById(R.id.tv_VitB5Title);
        textViewVitB5Value = findViewById(R.id.tv_VitB5Value);
        textViewVitHTitle = findViewById(R.id.tv_VitHTitle);
        textViewVitHValue = findViewById(R.id.tv_VitHValue);
        textViewVitB12Title = findViewById(R.id.tv_VitB12Title);
        textViewVitB12Value = findViewById(R.id.tv_VitB12Value);
        textViewVitCTitle = findViewById(R.id.tv_VitCTitle);
        textViewVitCValue = findViewById(R.id.tv_VitCValue);
        mBarChart = findViewById(R.id.chart);
        initData(mappingGizi);
        initChart();

        tambahProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductDialog addProductDialog = AddProductDialog.newInstance();
                addProductDialog.setListProduk(dataHelper.getAllProduk());
                addProductDialog.setDialogListener(new AddProductDialog.AddProductDialogListener() {
                    @Override
                    public void onSubmit(Map<Produk, Integer> listAddProduct) {
                        for (Map.Entry<Produk, Integer> entry : listAddProduct.entrySet()) {
                            Log.wtf("Tri", "get : " + entry.getKey().getNamaProduk());
//                            Toast.makeText(this, "Produk : " + entry.getKey().getNamaProduk() +
//                                    ", Jumlah : " + entry.getValue(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                addProductDialog.show(getSupportFragmentManager(), AddProductDialog.TAG);

            }
        });

    }

    public void initData(MappingGizi mMappingGizi){
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
    }

    public void initChart(){
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
