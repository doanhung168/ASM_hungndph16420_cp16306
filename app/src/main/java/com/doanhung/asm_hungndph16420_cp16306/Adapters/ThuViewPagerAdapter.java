package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.doanhung.asm_hungndph16420_cp16306.fragments.LoaiThuFragment;
import com.doanhung.asm_hungndph16420_cp16306.fragments.KhoanThuFragment;

public class ThuViewPagerAdapter extends FragmentStatePagerAdapter {

    public ThuViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KhoanThuFragment();
            case 1:
                return new LoaiThuFragment();
            default:
                return new KhoanThuFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Khoản Thu";
                break;
            case 1:
                title = "Loại Thu";
                break;
        }
        return title;
    }
}
