package com.doanhung.asm_hungndph16420_cp16306.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;

import java.util.List;

@Dao
public interface KhoanChiDao {

    @Insert
    void insertKhoanChi(KhoanChi khoanChi);

    @Update
    void updateKhoanChi(KhoanChi khoanChi);

    @Delete
    void deleteKhoanChi(KhoanChi khoanChi);

    @Query("SELECT * FROM `Khoan Chi`")
    List<KhoanChi> getAllKhoanChi();

    @Query("SELECT * FROM `Khoan Chi` WHERE idLoaiChi LIKE :id")
    List<KhoanChi> getAllKhoanChiByLoaiChi(int id);

    @Query("DELETE FROM `Khoan Chi` WHERE idKhoanChi LIKE :id")
    void deleteAllKhoanChiByLoaiChi(int id);

    @Query("SELECT * FROM `Khoan Chi` WHERE thoiGianChi LIKE DATE('now')")
    List<KhoanChi> getKhoanChiToday();

    @Query("SELECT * FROM `Khoan Chi` WHERE thoiGianChi <= DATE('now') AND thoiGianChi >= DATE('now', ''||:sql||'')")
    List<KhoanChi> getKhoanChiByTuanNay(String sql);

    @Query("SELECT * FROM `Khoan Chi` WHERE thoiGianChi BETWEEN :ngay1 AND :ngay2")
    List<KhoanChi> getKhoanChiTheoNgay(String ngay1, String ngay2);
}
