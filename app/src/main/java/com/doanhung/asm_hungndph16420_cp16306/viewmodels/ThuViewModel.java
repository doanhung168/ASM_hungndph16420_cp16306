package com.doanhung.asm_hungndph16420_cp16306.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;

import java.util.List;

public class ThuViewModel extends ViewModel {
    private final MutableLiveData<List<KhoanThu>> khoanThus = new MutableLiveData<>();
    public void setKhoanThus(List<KhoanThu> khoanThus){
       this.khoanThus.setValue(khoanThus);
    }
    public LiveData<List<KhoanThu>> getKhoanThus() {
        return this.khoanThus;
    }
}
