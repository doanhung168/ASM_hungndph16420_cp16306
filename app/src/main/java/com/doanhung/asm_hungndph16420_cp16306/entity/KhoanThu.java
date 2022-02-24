package com.doanhung.asm_hungndph16420_cp16306.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "khoan thu")
public class KhoanThu {

    @PrimaryKey(autoGenerate = true)
    private int idKhoanThu;
    private String tenKhoanThu;
    private double soTienThu;
    private int idLoaiThu;
    private String thoiGianThu;
    private String noteKhoanThu;

    public KhoanThu() {
    }

    public KhoanThu(String tenKhoanThu, double soTienThu, int idLoaiThu, String thoiGianThu, String noteKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
        this.soTienThu = soTienThu;
        this.idLoaiThu = idLoaiThu;
        this.thoiGianThu = thoiGianThu;
        this.noteKhoanThu = noteKhoanThu;
    }

    public int getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(int idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public double getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(double soTienThu) {
        this.soTienThu = soTienThu;
    }

    public int getIdLoaiThu() {
        return idLoaiThu;
    }

    public void setIdLoaiThu(int idLoaiThu) {
        this.idLoaiThu = idLoaiThu;
    }

    public String getThoiGianThu() {
        return thoiGianThu;
    }

    public void setThoiGianThu(String thoiGianThu) {
        this.thoiGianThu = thoiGianThu;
    }

    public String getNoteKhoanThu() {
        return noteKhoanThu;
    }

    public void setNoteKhoanThu(String noteKhoanThu) {
        this.noteKhoanThu = noteKhoanThu;
    }
}
