package com.example.oke.model;

import java.util.List;

public class LoaiSpModel {
    boolean  success;
    String message;



    List<LoaiSp> result ;   // ~ List<LoaiSp> data = loaiSpModel.getResult();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoaiSp> getResult() {
        return result;
    }

    public void setResult(List<LoaiSp> result) {
        this.result = result;
    }
}