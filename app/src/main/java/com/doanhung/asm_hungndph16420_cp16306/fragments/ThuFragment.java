package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.ThuViewPagerAdapter;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.google.android.material.tabs.TabLayout;

public class ThuFragment extends Fragment{
    // declared views
    private TabLayout tabLayout;
    private ViewPager viewPager;

    // declared object
    private ThuViewPagerAdapter thuViewPagerAdapter;
    private FragmentManager manager;


    public ThuFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init views
        initViews(view);

        //init objects
        initObjects();


        //
        viewPager.setAdapter(thuViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initObjects() {
        thuViewPagerAdapter = new ThuViewPagerAdapter(getParentFragmentManager());
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
    }

}
