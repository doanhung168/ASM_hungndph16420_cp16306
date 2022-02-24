package com.doanhung.asm_hungndph16420_cp16306.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiChi;

import java.util.List;

@Dao
public interface LoaiChiDao {

    @Insert
    void insertLoaiChi(LoaiChi loaiChi);

    @Update
    void updateLoaiChi(LoaiChi loaiChi);

    @Delete
    void deleteLoaiChi(LoaiChi loaiChi);

    @Query("SELECT * FROM `Loai Chi`")
    List<LoaiChi> getAllLoaiChi();


}
