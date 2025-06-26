package com.example.oke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.adapter.AmSanPhamMoiAdapter;
import com.example.oke.model.SanPhamMoi;
import com.example.oke.retrofit.ApiBanHang;
import com.example.oke.retrofit.RetrofitClient;
import com.example.oke.utils.Utils;
import com.example.oke.model.EventBus.SuaXoaEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuanliActivity extends AppCompatActivity {
    ImageView btnAdd;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> list;
    AmSanPhamMoiAdapter adapter;
    SanPhamMoi sanPhamSuaxoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanli);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        initView(); // Ánh xạ các view từ XML
        initControl(); // Gắn sự kiện cho các nút
        getSpMoi();
    }


    private void initControl() {

        //Khi bấm vào nut them, sẽ chuyển sang ThemSPActivity để nhập thông tin sản phẩm.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanliActivity.this, ThemSPActivity.class);
                startActivity(intent);
            }
        });
    }

    //lấy danh sách sản phẩm từ server và hiển thị:
    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                list = sanPhamMoiModel.getResult();
                                // Gọi API để lấy danh sách sản phẩm mới và hiển thị lên RecyclerView bằng AmSanPhamMoiAdapter
                                adapter = new AmSanPhamMoiAdapter(getApplicationContext(),list);
                                recyclerView.setAdapter(adapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }


    private void initView() {
        btnAdd = findViewById(R.id.btn_add);
        recyclerView =  findViewById(R.id.recycleview_ql);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    //khi nao Xoa Sua nhấn giữ sản phẩm, menu hiện ra
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Sửa")){
            suaSanPham();
        }else if (item.getTitle().equals("Xoá")){
            xoaSanPham();
        }
        return super.onContextItemSelected(item);
    }
    //Mở ThemSPActivity và truyền sản phẩm cần sửa qua bằng intent.putExtra
    private void suaSanPham() {
        Intent intent = new Intent(getApplicationContext(),ThemSPActivity.class);
        intent.putExtra("sua",sanPhamSuaxoa);

        startActivity(intent);
    }
    private void xoaSanPham() {
        compositeDisposable .add(apiBanHang.xoaSanPham(sanPhamSuaxoa.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messageModel -> {
                            if (messageModel.isSuccess()){
                                Toast.makeText(getApplicationContext(),messageModel.getMessage(),Toast.LENGTH_LONG).show();
                                getSpMoi();
                            //Gọi API xoá sản phẩm theo id. Sau khi xoá thành công thì gọi lại getSpMoi() để cập nhật danh sách.
                            }else {
                                Toast.makeText(getApplicationContext(),messageModel.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable -> {
                            Log.d("long",throwable.getMessage());
                        }
                ));
    }

    // Khi adapter phát ra SuaXoaEvent (ví dụ khi nhấn giữ một sản phẩm),
    // QuanliActivity sẽ nhận thông tin sản phẩm cần thao tác và lưu vào biến sanPhamSuaxoa
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void evenSuaXoa(SuaXoaEvent event){
        if (event !=null){
            sanPhamSuaxoa = event.getSanPhamMoi();
            //lay duoc sp moi
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
