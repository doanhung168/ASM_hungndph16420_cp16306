package com.doanhung.asm_hungndph16420_cp16306.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Loai Chi")
public class LoaiChi {

    @PrimaryKey(autoGenerate = true)
    private int idLoaiChi;
    private String tenLoaiChi;

    public LoaiChi() {
    }

    public LoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }

    public int getIdLoaiChi() {
        return idLoaiChi;
    }

    public void setIdLoaiChi(int idLoaiChi) {
        this.idLoaiChi = idLoaiChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }
}
