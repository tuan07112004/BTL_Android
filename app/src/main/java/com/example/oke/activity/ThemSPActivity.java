package com.example.oke.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.oke.R;
import com.example.oke.databinding.ActivityThemspBinding;
import com.example.oke.model.MessageModel;
import com.example.oke.model.SanPhamMoi;
import com.example.oke.retrofit.ApiBanHang;
import com.example.oke.retrofit.RetrofitClient;
import com.example.oke.utils.Utils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.database.Cursor;
import android.provider.MediaStore;


public class ThemSPActivity extends AppCompatActivity {
    Spinner spinner;
    private int loai = 0;
    ActivityThemspBinding binding;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String mediaPath;
    SanPhamMoi sanPhamsSua;
    boolean flag =false; //tao co kiem tra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sử dụng ViewBinding để thao tác trực tiếp với view thay vì findViewById
        binding = ActivityThemspBinding.inflate(getLayoutInflater());
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        setContentView(binding.getRoot());

        initView();
        initData();

        Intent intent = getIntent();
        sanPhamsSua = (SanPhamMoi) intent.getSerializableExtra("sua");
        //xu li khi nao la sua/them
        if (sanPhamsSua == null){
            //themmoi
            flag = false;
        }else {
            //sua
            flag = true;
            binding.btnThem.setText("Sửa sản phẩm");
            //show data
            binding.mota.setText(sanPhamsSua.getMota());
            binding.gia.setText(sanPhamsSua.getGia()+"");
            binding.tensp.setText(sanPhamsSua.getTensp());
            binding.hinhanh.setText(sanPhamsSua.getHinhanh());
            binding.spinnerLoai.setSelection(sanPhamsSua.getLoai());
            binding.sltonkho.setText(sanPhamsSua.getSltonkho()+"");
            binding.linkvideo.setText(sanPhamsSua.getLinkvideo());
        }
    }

    //Gán vào Spinner bằng ArrayAdapter
    private void initData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Vui lòng chọn loại");
        stringList.add("Loại 1");
        stringList.add("Loại 2");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                stringList
        );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loai = i; // Gán vị trí loại vào biến loai (0 = chọn loại, 1 = loại 1, 2 = loại 2)
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Không làm gì khi không chọn
            }
        });

        //them moi sp/ Su kien khi an nut Sua
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == false){
                    themsanpham();
                }else {
                    suaSanPham();
                }
            }
        });
        binding.imgcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chọn ảnh bằng ImagePicker
                ImagePicker.with(ThemSPActivity.this)
                        .crop()                           // cắt ảnh
                        .compress(1024)                   // Giảm dung lượng xuống dưới 1MB
                        .maxResultSize(1080, 1080)        // Kích thước tối đa
                        .start();                         // Bắt đầu chọn ảnh
            }
        });
    }

    private void suaSanPham() {
        String str_ten = binding.tensp.getText().toString().trim();
        String str_gia = binding.gia.getText().toString().trim();
        String str_mota = binding.mota.getText().toString().trim();
        String str_hinhanh = binding.hinhanh.getText().toString().trim();
        String str_link = binding.linkvideo.getText().toString().trim();
        String str_sltonkho = binding.sltonkho.getText().toString().trim();

        int gia = TextUtils.isEmpty(str_gia) ? 0 : Integer.parseInt(str_gia);
        int sltonkho = TextUtils.isEmpty(str_sltonkho) ? 0 : Integer.parseInt(str_sltonkho);

        //ktra Đảm bảo người dùng đã nhập đầy đủ thông tin trước khi gửi lên server.
        if (TextUtils.isEmpty(str_ten) || gia == 0 || TextUtils.isEmpty(str_hinhanh)
                || TextUtils.isEmpty(str_mota) || TextUtils.isEmpty(str_link) || loai == 0) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            compositeDisposable.add(apiBanHang.updatesp(
                            str_ten,
                            gia,
                            str_hinhanh,
                            str_mota,
                            loai,
                            str_link,
                            sltonkho,
                            sanPhamsSua.getId()
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if (messageModel.isSuccess()) {
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                    ));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                mediaPath = getPath(uri); //  Chuyển URI thành đường dẫn thật
                Log.d("Log", "mediaPath: " + mediaPath);
                if (mediaPath != null) {
                    uploadMultipleFiles(); // Gửi ảnh lên server
                } else {
                    Toast.makeText(this, "Không tìm thấy đường dẫn ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //chuyển URI → đường dẫn thực, rồi gán cho mediaPath
    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null){
            result = uri.getPath(); // Chỉ dùng được với Uri file://
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }


    //Upload file ảnh lên server
    private void uploadMultipleFiles() {

        Uri uri = Uri.parse(mediaPath);
        File file = new File(getPath(uri));
        // Đường dẫn file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        //Gửi file ảnh qua API uploadFile() sử dụng kiểu Multipart.
        Call<MessageModel> call = apiBanHang.uploadFile(fileToUpload1);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                MessageModel serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.isSuccess()) {
                        binding.hinhanh.setText(serverResponse.getName()); //Gán tên ảnh trả về từ server vào ô nhập ảnh
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.v("Response", response.toString());
                }
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }



    //xu li Thêm sản phẩm (gọi API)
    private void themsanpham() {
        String str_ten = binding.tensp.getText().toString().trim();
        String str_gia = binding.gia.getText().toString().trim();
        String str_mota = binding.mota.getText().toString().trim();
        String str_hinhanh = binding.hinhanh.getText().toString().trim();
        String str_link = binding.linkvideo.getText().toString().trim();
        String str_sltonkho = binding.sltonkho.getText().toString().trim();

        int gia = TextUtils.isEmpty(str_gia) ? 0 : Integer.parseInt(str_gia);
        int sltonkho = TextUtils.isEmpty(str_sltonkho) ? 0 : Integer.parseInt(str_sltonkho);

        //ktra Đảm bảo người dùng đã nhập đầy đủ thông tin trước khi gửi lên server.
        if (TextUtils.isEmpty(str_ten) || gia == 0 || TextUtils.isEmpty(str_hinhanh)
                || TextUtils.isEmpty(str_mota) || TextUtils.isEmpty(str_link) || loai == 0) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            compositeDisposable.add(apiBanHang.insertSp(
                            str_ten,
                            gia,
                            str_hinhanh,
                            str_mota,
                            loai,
                            str_link,
                            sltonkho
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if (messageModel.isSuccess()) {
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                    ));
        }
    }


    private void initView() {
        spinner = findViewById(R.id.spinner_loai);
    }
    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }

}

