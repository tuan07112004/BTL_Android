package com.example.oke.retrofit;


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
    );

}
