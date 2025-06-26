package com.example.oke.activity;

<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.AdapterView;
import android.widget.ImageView;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
<<<<<<< HEAD
=======
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import com.example.oke.R;
import com.example.oke.adapter.LoaiSpAdapter;
import com.example.oke.adapter.SanPhamMoiAdapter;
import com.example.oke.model.LoaiSp;
import com.example.oke.model.SanPhamMoi;
import com.example.oke.retrofit.ApiBanHang;
import com.example.oke.retrofit.RetrofitClient;
import com.example.oke.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
<<<<<<< HEAD
    Toolbar toolbar;
=======

    Toolbar toolbar;

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
<<<<<<< HEAD
    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
=======
     List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
    ImageSlider imageSlider;
    ImageView imgsearch;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        db = FirebaseFirestore.getInstance();

        Anhxa();
        ActionBar();

<<<<<<< HEAD
        if(isConnected(this)){
            getLoaiSanPham();
            getSpMoi();
//            getEventclick();
=======

        if(isConnected(this)){
            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventclick();
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        }else {
            Toast.makeText(getApplicationContext(),"không có internet,vui lòng kết nối " ,Toast.LENGTH_LONG).show();
        }
    }

<<<<<<< HEAD
    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
=======

    private void ActionViewFlipper() {
        List<SlideModel>imagelist = new ArrayList<>();
        compositeDisposable.add(apiBanHang.getKhuyenMai()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        khuyenMaiModel -> {
                            if (khuyenMaiModel.isSuccess()){
                                for (int i = 0 ;i<khuyenMaiModel.getResult().size(); i++){
                                    imagelist.add(new SlideModel(khuyenMaiModel.getResult().get(i).getUrl(),null));
                                }
                                imageSlider.setImageList(imagelist, ScaleTypes.CENTER_CROP);

                                imageSlider .setItemClickListener(new ItemClickListener() {
                                    @Override
                                    public void onItemSelected(int i) {
                                        Intent km = new Intent(getApplicationContext(), KhuyenMaiActivity.class);
                                        km.putExtra("noidung",khuyenMaiModel.getResult().get(i).getThongtin());
                                        km.putExtra("url",khuyenMaiModel.getResult().get(i).getUrl());
                                        startActivity(km);
                                    }

                                    @Override
                                    public void doubleClick(int i) {


                                    }
                                });




                            }else {
                                Toast.makeText(getApplicationContext(),"lỗi",Toast.LENGTH_LONG).show();

                            }
                        },
                        throwable -> {
                            Log.d("log",throwable.getMessage());

                        }

                ));




    }

    private void getEventclick() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(trangchu);
                        overridePendingTransition(0, 0);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(),DienThoaiActivity.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        overridePendingTransition(0, 0);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(),DienThoaiActivity.class);
                        laptop.putExtra("loai",2);
                        startActivity(laptop);
                        overridePendingTransition(0, 0);
                        break;
                    case 3:
                        Intent thongtin = new Intent(getApplicationContext(),ThongtinActivity.class);
                        startActivity(thongtin);
                        overridePendingTransition(0, 0);
                        break;
                    case 4:
                        Intent lienhe = new Intent(getApplicationContext(),LienHeActivity.class);
                        startActivity(lienhe);
                        overridePendingTransition(0, 0);
                        break;
                    case 5:
                        Intent donhang = new Intent(getApplicationContext(),XemDonActivity.class);
                        startActivity(donhang);
                        overridePendingTransition(0, 0);
                        break;
                    case 6:
                        Intent dangnhap = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(dangnhap);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                .subscribe(

                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(),mangSpMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }
<<<<<<< HEAD
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
=======

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                        }
                ));
    }


<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if (loaiSpModel.isSuccess()) {
                                mangloaisp = loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), mangloaisp);
                                listViewManHinhChinh.setAdapter(loaiSpAdapter);
                            } else {
                                Log.e("API", "Không thành công: " + loaiSpModel.getMessage());
                            }
                        },
                        throwable -> {
                            Log.e("API_ERROR", "Lỗi gọi API: " + throwable.getMessage(), throwable);
                            Toast.makeText(getApplicationContext(), "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    private void Anhxa() {
<<<<<<< HEAD
        toolbar = findViewById(R.id.toobarmanhinhchinh);
=======
        imgsearch = findViewById(R.id.imgsearch);
        toolbar = findViewById(R.id.toobarmanhinhchinh);
        imageSlider = findViewById(R.id.image_slider);

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        listViewManHinhChinh = findViewById(R.id.lisviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        // Khởi tạo LIST
        mangloaisp = new ArrayList<>();
        mangSpMoi = new ArrayList<>();
<<<<<<< HEAD
=======

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });



>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

<<<<<<< HEAD
=======
//Kiểm tra thiết bị có kết nối Internet ko
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    private boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            return capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
<<<<<<< HEAD
}
=======





}
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
