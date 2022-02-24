package com.doanhung.asm_hungndph16420_cp16306.Databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;

@Database(entities = {LoaiThu.class, KhoanThu.class, LoaiChi.class, KhoanChi.class}, version = 1)
public abstract class ThuChiDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "thu_chi.db";
    public static ThuChiDatabase instance;

    public static ThuChiDatabase getInstance(Context context) {
        instance = Room.databaseBuilder(context.getApplicationContext(), ThuChiDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        return instance;
    }

    public abstract LoaiThuDAO loaiThuDAO();
    public abstract KhoanThuDAO khoanThuDAO();
    public abstract KhoanChiDao khoanChiDao();
    public abstract LoaiChiDao loaiChiDao();
}
