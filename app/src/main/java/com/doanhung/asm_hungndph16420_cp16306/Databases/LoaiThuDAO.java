package com.doanhung.asm_hungndph16420_cp16306.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;

import java.util.List;

@Dao
public interface LoaiThuDAO {

    @Insert
    void insertLoai(LoaiThu loaiThu);

    @Query("SELECT * FROM `Loai Thu`")
    List<LoaiThu> getLoais();

    @Update
    void updateLoaiThu(LoaiThu loaiThu);

    @Delete
    void deleteLoaiThu(LoaiThu loaiThu);

}
