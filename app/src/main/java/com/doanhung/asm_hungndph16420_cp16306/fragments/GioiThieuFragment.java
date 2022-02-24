package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.GioiThieuRecyclerAdapter;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.object.GioiThieu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioiThieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioiThieuFragment extends Fragment {
    private RecyclerView recyclerView;
    private GioiThieuRecyclerAdapter adapter;
    private List<GioiThieu> list;

    public GioiThieuFragment() {
        // Required empty public constructor
    }


    public static GioiThieuFragment newInstance(String param1, String param2) {
        GioiThieuFragment fragment = new GioiThieuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        list = getList();
        adapter = new GioiThieuRecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    private List<GioiThieu> getList() {
        list = new ArrayList<>();
        list.add(new GioiThieu("Tên App: ", "Quản Lý Thu Chi"));
        list.add(new GioiThieu("Mục đích: ", "Quản Lý Thu Chi Cá Nhân"));
        list.add(new GioiThieu("Tên Người Tạo: ", "Nguyễn Doãn Hùng"));
        list.add(new GioiThieu("Ngày tạo: ", "01/08/2021"));
        return list;
    }
}