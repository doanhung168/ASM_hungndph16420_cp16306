package com.doanhung.asm_hungndph16420_cp16306.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Khoan Chi")
public class KhoanChi {

    @PrimaryKey(autoGenerate = true)
    private int idKhoanChi;
    private String tenKhoanChi;
    private double soTienChi;
    private int idLoaiChi;
    private String thoiGianChi;
    private String noteKhoanChi;

    public KhoanChi() {
    }

    public KhoanChi(String tenKhoanChi, double soTienChi, int idLoaiChi, String thoiGianChi, String noteKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
        this.soTienChi = soTienChi;
        this.idLoaiChi = idLoaiChi;
        this.thoiGianChi = thoiGianChi;
        this.noteKhoanChi = noteKhoanChi;
    }

    public int getIdKhoanChi() {
        return idKhoanChi;
    }

    public void setIdKhoanChi(int idKhoanChi) {
        this.idKhoanChi = idKhoanChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public double getSoTienChi() {
        return soTienChi;
    }

    public void setSoTienChi(double soTienChi) {
        this.soTienChi = soTienChi;
    }

    public int getIdLoaiChi() {
        return idLoaiChi;
    }

    public void setIdLoaiChi(int idLoaiChi) {
        this.idLoaiChi = idLoaiChi;
    }

    public String getThoiGianChi() {
        return thoiGianChi;
    }

    public void setThoiGianChi(String thoiGianChi) {
        this.thoiGianChi = thoiGianChi;
    }

    public String getNoteKhoanChi() {
        return noteKhoanChi;
    }

    public void setNoteKhoanChi(String noteKhoanChi) {
        this.noteKhoanChi = noteKhoanChi;
    }
}
