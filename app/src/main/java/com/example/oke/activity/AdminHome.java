package com.example.oke.activity;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.adapter.AmLoaiSpAdapter;
import com.example.oke.adapter.AmSanPhamMoiAdapter;
import com.example.oke.model.LoaiSp;
import com.example.oke.model.SanPhamMoi;
import com.example.oke.retrofit.ApiBanHang;
import com.example.oke.retrofit.RetrofitClient;
import com.example.oke.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdminHome extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    AmLoaiSpAdapter loaiSpAdapter;
    AmSanPhamMoiAdapter spAdapter;

    List<LoaiSp> mangloaisp; // Danh sách loại sản phẩm
    List<SanPhamMoi> mangSpMoi;  // Danh sách sản phẩm mới

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    FirebaseFirestore db; // Kết nối Firestore


=======
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.oke.R;

public class AdminHome extends AppCompatActivity {
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
<<<<<<< HEAD

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        db = FirebaseFirestore.getInstance(); // Khởi tạo Firebase Firestore

        Anhxa(); // Ánh xạ view
        ActionBar(); // Thiết lập toolbar

        if(isConnected(this)){
            getLoaiSanPham();
            getSpMoi();
            getEventclick();
        }else {
            Toast.makeText(getApplicationContext(),"không có internet,vui lòng kết nối " ,Toast.LENGTH_LONG).show();
        }
    }private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new AmSanPhamMoiAdapter(getApplicationContext(),mangSpMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Không kết nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }


    //click các item trong menu trái
    private void getEventclick() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), AdminHome.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(),DienThoaiActivity.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(),DienThoaiActivity.class);
                        laptop.putExtra("loai",2);
                        startActivity(laptop);
                        break;
                    case 5:
                        Intent donhang = new Intent(getApplicationContext(),XemDonActivity.class);
                        startActivity(donhang);
                        break;
                    case 6:
                        Intent quanli = new Intent(getApplicationContext(),QuanliActivity.class);
                        startActivity(quanli);
                        break;
                    case 7:
                        Intent chat = new Intent(getApplicationContext(),UserActivity.class);
                        startActivity(chat);
                        break;
                    case 8:
                        Intent tk = new Intent(getApplicationContext(),ThongKeActivity.class);
                        startActivity(tk);
                        break;
                    case 9:
                        //xoa key
                        Paper.book().delete("user");
                        FirebaseAuth.getInstance().signOut();
                        Intent dangnhap = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(dangnhap);
                        finish();
                        break;
                }
            }
        });
    }


    //Lấy danh sách sản phẩm mới từ API



    //Lấy danh sách loại sản phẩm + thêm item menu phụ
    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel-> {
                            if (loaiSpModel.isSuccess()){
                                mangloaisp = loaiSpModel.getResult();
                                mangloaisp.add(new LoaiSp("https://davicom.com.vn/wp-content/uploads/2023/12/lien-ket-nhieu-cong-cu-khac.jpg","Quản lí"));
                                mangloaisp.add(new LoaiSp("https://cdn-icons-png.flaticon.com/512/2899/2899298.png","ChatBox"));
                                mangloaisp.add(new LoaiSp("https://img.lovepik.com/element/45004/6149.png_860.png","Thống kê sản phẩm"));
                                mangloaisp.add(new LoaiSp("https://png.pngtree.com/png-vector/20231115/ourmid/pngtree-logout-icon-circle-png-image_10610262.png","Đăng xuất"));
                                loaiSpAdapter = new AmLoaiSpAdapter(getApplicationContext(), mangloaisp);
                                listViewManHinhChinh.setAdapter(loaiSpAdapter);
                            }

                        }, throwable -> {
                            // Xử lý lỗi
                        }));
    }


    private void Anhxa() {
        toolbar = findViewById(R.id.toobarmanhinhchinh);

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

    }

    //Cài đặt Toolbar có nút menu trái
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


    //Kiểm tra kết nối Internet
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


    // Xoá các kết nối RxJava khi thoát Activity
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();  // Ngăn rò rỉ bộ nhớ
        super.onDestroy();
    }

}
=======
    }
}
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
