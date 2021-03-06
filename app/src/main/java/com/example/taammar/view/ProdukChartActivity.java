package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.taammar.R;
import com.example.taammar.adapter.ProductListAdapter;
import com.example.taammar.db.DataHelper;
import com.example.taammar.helper.Utility;
import com.example.taammar.model.MappingGizi;
import com.example.taammar.model.Produk;
import com.example.taammar.view.dialog.AddProductDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdukChartActivity extends AppCompatActivity {

    private ProductListAdapter mAdapter;
    private RecyclerView mRecyclerView;
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
    private TextView textViewProductVitA;
    private TextView textViewProductVitB9;
    private TextView textViewProductVitD;
    private TextView textViewProductVitE;
    private TextView textViewProductVitK;
    private TextView textViewProductVitB1;
    private TextView textViewProductVitB2;
    private TextView textViewProductVitB3;
    private TextView textViewProductVitB5;
    private TextView textViewProductVitB6;
    private TextView textViewProductVitH;
    private TextView textViewProductVitB12;
    private TextView textViewProductVitC;
    private BarChart mBarChart;
    private View viewTambahan;
    private View rangkumanContainer;
    private Button tambahProductButton;
    private List<Produk> produkList = new ArrayList<>();
    private List<Produk> itemProdukCart = new ArrayList<>();
    private MappingGizi mappingGizi;
    private float A, D, E, K, B1, B2, B3, B5, B6, H, B9, B12, C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_chart);

         mappingGizi =  (MappingGizi) getIntent().getSerializableExtra("mappinggizi");
        dataHelper = DataHelper.getInstance(this);
        View floatingContainer = findViewById(R.id.float_button_container);
        tambahProductButton = floatingContainer.findViewById(R.id.tambah_produk);
        viewTambahan = findViewById(R.id.view_tambahan);
        mRecyclerView = findViewById(R.id.product_list_recyclerview);
        rangkumanContainer = findViewById(R.id.rangkuman_produk);

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

        textViewProductVitA = findViewById(R.id.tv_ProductVitAValue);
        textViewProductVitB9 = findViewById(R.id.tv_ProductVitB9Value);
        textViewProductVitD = findViewById(R.id.tv_ProductVitDValue);
        textViewProductVitE = findViewById(R.id.tv_ProductVitEValue);
        textViewProductVitK = findViewById(R.id.tv_ProductVitKValue);
        textViewProductVitB1 = findViewById(R.id.tv_ProductVitB1Value);
        textViewProductVitB2 = findViewById(R.id.tv_ProductVitB2Value);
        textViewProductVitB3 = findViewById(R.id.tv_ProductVitB3Value);
        textViewProductVitB5 = findViewById(R.id.tv_ProductVitB5Value);
        textViewProductVitB6 = findViewById(R.id.tv_ProductVitB6Value);
        textViewProductVitH = findViewById(R.id.tv_ProductVitHValue);
        textViewProductVitB12 = findViewById(R.id.tv_ProductVitB12Value);
        textViewProductVitC = findViewById(R.id.tv_ProductVitCValue);
        mBarChart = findViewById(R.id.chart);
        mAdapter = new ProductListAdapter(itemProdukCart, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        initData(mappingGizi);
        initChart();

        produkList.addAll(dataHelper.getAllProduk());

        tambahProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductDialog addProductDialog = AddProductDialog.newInstance();
                addProductDialog.setListProduk(produkList, itemProdukCart);
                addProductDialog.setDialogListener(new AddProductDialog.AddProductDialogListener() {
                    @Override
                    public void onSubmit(List<Produk> listAddProduct) {
                        List<Produk> itemProdukList = new ArrayList<>(listAddProduct);
                        hitungKandunganGizi(itemProdukList);
                        tambahRangkumanProduk(itemProdukList);

                        for (Produk produk : listAddProduct) {
                            if (produkList.contains(produk)) {
                                produkList.set(produkList.indexOf(produk), produk);
                            }
                        }
                    }

                    @Override
                    public void onCancel() {
                        resetKandunganGizi();
                        initChart();
                        if (rangkumanContainer.getVisibility() == View.VISIBLE) {
                            itemProdukCart.clear();
                            mAdapter.notifyDataSetChanged();
                            rangkumanContainer.setVisibility(View.GONE);
                        }
                        setDefaultColor();
                        inputkandungangizi();
                        settextColor();
                    }
                });
                addProductDialog.show(getSupportFragmentManager(), AddProductDialog.TAG);

            }
        });
    }

    private void tambahRangkumanProduk(List<Produk> itemProdukList) {
        itemProdukCart.clear();
        itemProdukCart.addAll(itemProdukList);
        mAdapter.notifyDataSetChanged();
        if (itemProdukCart.isEmpty()) {
            rangkumanContainer.setVisibility(View.GONE);
        } else {
            rangkumanContainer.setVisibility(View.VISIBLE);
        }
    }

    private void hitungKandunganGizi(List<Produk> listProduk) {
        resetKandunganGizi();
        for (Produk produk: listProduk) {
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
            formatter.setGroupingUsed(false);
            formatter.setMaximumFractionDigits(4);
            formatter.setMinimumFractionDigits(1);
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            A += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitA()) * produk.getJmlProduk()));
            D += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitD()) * produk.getJmlProduk()));
            E += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitE()) * produk.getJmlProduk()));
            K += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitK()) * produk.getJmlProduk()));
            B1 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB1()) * produk.getJmlProduk()));
            B2 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB2()) * produk.getJmlProduk()));
            B3 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB3()) * produk.getJmlProduk()));
            B5 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB5()) * produk.getJmlProduk()));
            B6 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB6()) * produk.getJmlProduk()));
            H += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitH()) * produk.getJmlProduk()));
            B9 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB9()) * produk.getJmlProduk()));
            B12 += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitB12()) * produk.getJmlProduk()));
            C += Float.parseFloat(formatter.format(Double.parseDouble(produk.getVitC()) * produk.getJmlProduk()));
        }

        initChart();
        setDefaultColor();
        inputkandungangizi();
        settextColor();

    }

    private void resetKandunganGizi() {
        A = 0f;
        D = 0f;
        E = 0f;
        K = 0f;
        B1 = 0f;
        B2 = 0f;
        B3 = 0f;
        B5 = 0f;
        B6 = 0f;
        H = 0f;
        B9 = 0f;
        B12 = 0f;
        C = 0f;
    }

    private void inputkandungangizi(){
        textViewProductVitA.setText(String.format(Locale.US,"%.4f",A));
        textViewProductVitB9.setText(String.format(Locale.US,"%.4f",B9));
        textViewProductVitD.setText(String.format(Locale.US,"%.4f",D));
        textViewProductVitE.setText(String.format(Locale.US,"%.4f",E));
        textViewProductVitK.setText(String.format(Locale.US,"%.4f",K));
        textViewProductVitB1.setText(String.format(Locale.US,"%.4f",B1));
        textViewProductVitB2.setText(String.format(Locale.US,"%.4f",B2));
        textViewProductVitB3.setText(String.format(Locale.US,"%.4f",B3));
        textViewProductVitB5.setText(String.format(Locale.US,"%.4f",B5));
        textViewProductVitB6.setText(String.format(Locale.US,"%.4f",B6));
        textViewProductVitH.setText(String.format(Locale.US,"%.4f",H));
        textViewProductVitB12.setText(String.format(Locale.US,"%.4f",B12));
        textViewProductVitC.setText(String.format(Locale.US,"%.4f",C));
    }

    private void setDefaultColor(){
        textViewProductVitA.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB9.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitD.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitE.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitK.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB1.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB2.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB3.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB5.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB6.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitH.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitB12.setTextColor(getResources().getColor(R.color.black));
        textViewProductVitC.setTextColor(getResources().getColor(R.color.black));
    }

    private void settextColor(){
        if (A < Utility.stringToFloat(mappingGizi.getVitA())){
            textViewProductVitA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B9 < Utility.stringToFloat(mappingGizi.getVitB9())){
            textViewProductVitB9.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (D < Utility.stringToFloat(mappingGizi.getVITD())){
            textViewProductVitD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (E < Utility.stringToFloat(mappingGizi.getVITE())){
            textViewProductVitE.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (K < Utility.stringToFloat(mappingGizi.getVitK())){
            textViewProductVitK.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B1 < Utility.stringToFloat(mappingGizi.getVitB1())){
            textViewProductVitB1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B2 < Utility.stringToFloat(mappingGizi.getVitB2())){
            textViewProductVitB2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B3 < Utility.stringToFloat(mappingGizi.getVitB3())){
            textViewProductVitB3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B5 < Utility.stringToFloat(mappingGizi.getVitB5())){
            textViewProductVitB5.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B6 < Utility.stringToFloat(mappingGizi.getVitB6())){
            textViewProductVitB6.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (H < Utility.stringToFloat(mappingGizi.getVitH())){
            textViewProductVitH.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (B12 < Utility.stringToFloat(mappingGizi.getVitB12())){
            textViewProductVitB12.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        if (C < Utility.stringToFloat(mappingGizi.getVitC())){
            textViewProductVitC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //untuk desain tampilan agar button tidak menutupi tampilan
        ViewGroup.LayoutParams params = viewTambahan.getLayoutParams();
        params.height = tambahProductButton.getHeight() + 40;
        viewTambahan.setLayoutParams(params);
        ViewGroup.LayoutParams params1 = mRecyclerView.getLayoutParams();

        if (mRecyclerView.getHeight() > mBarChart.getHeight()) {
            params1.height = mBarChart.getHeight();
        } else {
            params1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        mRecyclerView.setLayoutParams(params1);
    }

    public void initData(MappingGizi mMappingGizi){
        textViewVitATitle.setText("A");
        textViewVitAValue.setText(mMappingGizi.getVitA());
        textViewVitB9Title.setText("B9");
        textViewVitB9Value.setText(mMappingGizi.getVitB9());
        textViewVitDTitle.setText("D");
        textViewVitDValue.setText(mMappingGizi.getVITD());
        textViewVitETitle.setText("E");
        textViewVitEValue.setText(mMappingGizi.getVITE());
        textViewVitKTitle.setText("K");
        textViewVitKValue.setText(mMappingGizi.getVitK());
        textViewVitB1Title.setText("B1");
        textViewVitB1Value.setText(mMappingGizi.getVitB1());
        textViewVitB2Title.setText("B2");
        textViewVitB2Value.setText(mMappingGizi.getVitB2());
        textViewVitB3Title.setText("B3");
        textViewVitB3Value.setText(mMappingGizi.getVitB3());
        textViewVitB5Title.setText("B5");
        textViewVitB5Value.setText(mMappingGizi.getVitB5());
        textViewVitB6Title.setText("B6");
        textViewVitB6Value.setText(mMappingGizi.getVitB6());
        textViewVitHTitle.setText("H");
        textViewVitHValue.setText(mMappingGizi.getVitH());
        textViewVitB12Title.setText("B12");
        textViewVitB12Value.setText(mMappingGizi.getVitB12());
        textViewVitCTitle.setText("C");
        textViewVitCValue.setText(mMappingGizi.getVitC());
    }

    public void initChart(){
        //Todo : Ganti value berdasarkan produk
        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new BarEntry(A, 0));
        NoOfEmp.add(new BarEntry(D, 1));
        NoOfEmp.add(new BarEntry(E, 2));
        NoOfEmp.add(new BarEntry(K, 3));
        NoOfEmp.add(new BarEntry(B1, 4));
        NoOfEmp.add(new BarEntry(B2, 5));
        NoOfEmp.add(new BarEntry(B3, 6));
        NoOfEmp.add(new BarEntry(B5, 7));
        NoOfEmp.add(new BarEntry(B6, 8));
        NoOfEmp.add(new BarEntry(H, 9));
        NoOfEmp.add(new BarEntry(B9, 10));
        NoOfEmp.add(new BarEntry(B12, 11));
        NoOfEmp.add(new BarEntry(C, 12));

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
        mBarChart.animateY(2000);
        BarData data = new BarData(xAxis, bardataset);
        data.setValueFormatter(new MyValueFormatter());
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mBarChart.setDescription("");
        mBarChart.setData(data);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.getAxisLeft().setValueFormatter(new MyYAxisValueFormatter());
        mBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM.BOTTOM);
        mBarChart.getXAxis().setLabelsToSkip(0);
        mBarChart.invalidate();
    }

    private class MyValueFormatter implements ValueFormatter {

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
            formatter.setGroupingUsed(false);
            formatter.setMaximumFractionDigits(4);
            formatter.setMinimumFractionDigits(1);
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            return formatter.format(value);
        }
    }

    private class MyYAxisValueFormatter implements YAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, YAxis yAxis) {
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
            formatter.setGroupingUsed(false);
            formatter.setMaximumFractionDigits(4);
            formatter.setMinimumFractionDigits(1);
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            return formatter.format(value);
        }
    }
}
