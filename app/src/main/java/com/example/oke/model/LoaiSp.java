package com.example.oke.model;

public class LoaiSp {
    int id;
    String tensanpham;
    String hinhanh;

    public LoaiSp(String hinhanh, String tensanpham) {
        this.hinhanh = hinhanh;
        this.tensanpham = tensanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
