package com.doanhung.asm_hungndph16420_cp16306.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Loai Thu")
public class LoaiThu {

    public LoaiThu() {
    }

    public LoaiThu(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Ten Loai")
    private String tenLoai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
