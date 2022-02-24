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
public interface KhoanThuDAO {

    @Insert
    void insertKhoanThu(KhoanThu khoanThu);

    @Query("SELECT * FROM `khoan thu`")
    List<KhoanThu> getAllKhoanThu();

    @Update
    void updateKhoanThu(KhoanThu khoanThu);

    @Delete
    void deleteKhoanThu(KhoanThu khoanThu);

    @Query("SELECT * FROM `khoan thu` WHERE idLoaiThu LIKE :id ")
    List<KhoanThu> getAllKhoanThuByLoaiThu(int id);

    @Query("DELETE FROM `khoan thu`WHERE idKhoanThu LIKE :id")
    void deleteKhoanThuByLoaiThu(int id);


    @Query("SELECT * FROM `khoan thu` WHERE thoiGianThu LIKE DATE('now')")
    List<KhoanThu> getKhoanThuToday();

    @Query("SELECT * FROM `khoan thu` WHERE thoiGianThu >= DATE('now', ''||:sql||'') AND thoiGianThu <= DATE('now')")
    List<KhoanThu> getKhoanThuByTuanNay(String sql);

    @Query("SELECT * FROM `khoan thu` WHERE thoiGianThu BETWEEN :ngay1 AND :ngay2 ")
    List<KhoanThu> getKhoanThuTheoNgay(String ngay1, String ngay2);
}
