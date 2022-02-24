package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.doanhung.asm_hungndph16420_cp16306.fragments.KhoanChiFragment;
import com.doanhung.asm_hungndph16420_cp16306.fragments.LoaiChiFragment;


public class ChiViewPagerAdapter extends FragmentStateAdapter {

    public ChiViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                return fragment = KhoanChiFragment.newInstance();
            case 1:
                return fragment = LoaiChiFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
