package com.example.oke.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oke.R;
import com.example.oke.model.SanPhamMoi; // Thêm import này

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnthem, btnyoutobe;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    SanPhamMoi sanPhamMoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();
        initControl();
    }

    private void initControl() {

        btnyoutobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietActivity.this, YouTubeActivity.class);
                intent.putExtra("linkvideo", sanPhamMoi.getLinkvideo());
                startActivity(intent);



            }
        });


    }
    private void initData() {
        try {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("chitiet")) {
                sanPhamMoi = (SanPhamMoi) intent.getSerializableExtra("chitiet");
                if (sanPhamMoi != null) {
                    tensp.setText(sanPhamMoi.getTensp());
                    mota.setText(sanPhamMoi.getMota());
                    Glide.with(getApplicationContext()).load(sanPhamMoi.getHinhanh()).into(imghinhanh);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasp())) + "Đ");

                    Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
                    ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
                    spinner.setAdapter(adapterspin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi hiển thị chi tiết sản phẩm", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        imghinhanh = findViewById(R.id.imgchitiet);
        spinner = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toobar);
        btnyoutobe =findViewById(R.id.btnyoutube);
    }
} // Thêm dấu đóng ngoặc này