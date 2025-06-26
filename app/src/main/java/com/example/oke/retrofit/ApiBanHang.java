package com.example.oke.retrofit;

<<<<<<< HEAD
import io.reactivex.rxjava3.core.Observable;

import com.example.oke.model.LoaiSpModel;
import com.example.oke.model.SanPhamMoiModel;
import com.example.oke.model.MessageModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable <SanPhamMoiModel> getSpMoi();

    @POST("insertsp.php")
    @FormUrlEncoded
    Observable<MessageModel> insertSp(
            @Field("tensp") String tensp,
            @Field("gia") int gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("loai") int loai,
            @Field("linkvideo") String linkvideo,
            @Field("sltonkho") int sltonkho
    );

    @Multipart
    @POST("upload.php")
    Call<MessageModel> uploadFile(
            @Part MultipartBody.Part file);


    @POST("xoa.php")
    @FormUrlEncoded
    Observable<MessageModel> xoaSanPham(
            @Field("id") int id
    );


    @FormUrlEncoded
    @POST("updatesp.php")
    Observable<MessageModel> updatesp(
            @Field("tensp") String tensp,
            @Field("gia") int gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("loai") int loai,
            @Field("linkvideo") String linkvideo,
            @Field("sltonkho") int sltonkho,
            @Field("id") int id
=======

import io.reactivex.rxjava3.core.Observable;


import com.example.oke.model.KhuyenMaiModel;
import com.example.oke.model.LoaiSpModel;
import com.example.oke.model.SanPhamMoiModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    // GET DATA
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();
    @GET("getspmoi.php")
    Observable <SanPhamMoiModel> getSpMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(

            @Field("loai") int  loai
    );

    @GET("khuyenmai.php")
    Observable<KhuyenMaiModel> getKhuyenMai();

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    );

}
