package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.ChiViewPagerAdapter;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiFragment extends Fragment {
    private TabLayout chiTabLayout;
    private ViewPager2 chiViewPager;
    private ChiViewPagerAdapter chiViewPagerAdapter;


    public ChiFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chiTabLayout = view.findViewById(R.id.tabLayout);
        chiViewPager = view.findViewById(R.id.container);

        chiViewPagerAdapter = new ChiViewPagerAdapter(getActivity());
        chiViewPager.setAdapter(chiViewPagerAdapter);

        new TabLayoutMediator(chiTabLayout, chiViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0) {
                    tab.setText("Khoản Chi");
                } else {
                    tab.setText("Loại Chi");
                }
            }
        }).attach();
    }
}