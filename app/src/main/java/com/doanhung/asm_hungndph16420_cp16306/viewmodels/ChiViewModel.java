package com.doanhung.asm_hungndph16420_cp16306.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;

import java.util.List;

public class ChiViewModel extends ViewModel {
    private final MutableLiveData<List<KhoanChi>> khoanChis = new MutableLiveData<>();

    public void setKhoanChis(List<KhoanChi> list) {
        khoanChis.setValue(list);
    }

    public LiveData<List<KhoanChi>> getKhoanChis() {
        return this.khoanChis;
    }
}
