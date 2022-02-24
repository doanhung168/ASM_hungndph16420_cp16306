package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.LoaiChiReccylerViewAdapter;
import com.doanhung.asm_hungndph16420_cp16306.Databases.ThuChiDatabase;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiChi;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IChuyenDoiInToString;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.viewmodels.ChiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiChiFragment extends Fragment implements IClickItem {
    private RecyclerView recyclerView;
    private FloatingActionButton f_btnAddLoaiChi;

    private LoaiChiReccylerViewAdapter adapter;
    private List<LoaiChi> mLoaiChiList;

    private ChiViewModel chiViewModel;


    public LoaiChiFragment() {
        // Required empty public constructor
    }

    public static LoaiChiFragment newInstance() {
        LoaiChiFragment fragment = new LoaiChiFragment();
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
        return inflater.inflate(R.layout.fragment_loai_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        f_btnAddLoaiChi = view.findViewById(R.id.f_btnAddLoaiChi);
        chiViewModel = new ViewModelProvider(requireActivity()).get(ChiViewModel.class);

        adapter = new LoaiChiReccylerViewAdapter(this);
        mLoaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        adapter.setData(mLoaiChiList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        f_btnAddLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoaiChi();
            }
        });

    }

    private void addLoaiChi() {
        EditText edtTenLoai;
        Button btnHuy, btnThem;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_loai_chi);

        edtTenLoai = dialog.findViewById(R.id.edtTenLoaiChi);
        btnHuy = dialog.findViewById(R.id.btnHuyThemLoaiChi);
        btnThem = dialog.findViewById(R.id.btnThemLoaiChi);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiChi = edtTenLoai.getText().toString().trim();
                LoaiChi loaiChi = new LoaiChi(tenLoaiChi);

                ThuChiDatabase.getInstance(getContext()).loaiChiDao().insertLoaiChi(loaiChi);
                loadData();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void loadData() {
        mLoaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        adapter.setData(mLoaiChiList);
    }

    @Override
    public void edit(Object object) {
        if(!(object instanceof LoaiChi)){
            return;
        }
        LoaiChi loaiChi = (LoaiChi) object;
        Log.e("hung", loaiChi.getTenLoaiChi());
        EditText edtTenCu, edtTenMoi;
        Button btnHuy, btnSua;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_edit_loai_chi);

        edtTenCu = dialog.findViewById(R.id.edtTenCu);
        edtTenMoi = dialog.findViewById(R.id.edtTenMoi);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnSua = dialog.findViewById(R.id.btnSua);

        edtTenCu.setText(loaiChi.getTenLoaiChi());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiChiMoi = edtTenMoi.getText().toString().trim();
                loaiChi.setTenLoaiChi(tenLoaiChiMoi);
                ThuChiDatabase.getInstance(getContext()).loaiChiDao().updateLoaiChi(loaiChi);
                loadData();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void delete(Object object) {
        if(!(object instanceof LoaiChi)){
            return;
        }

        LoaiChi loaiChi = (LoaiChi) object;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Loại Chi");
        builder.setMessage("Bạn có chắc chắn muốn xóa loại chi: " + loaiChi.getTenLoaiChi() + "?"
                +"\nNếu xóa loại chi này thì tất cả khoản chi thuộc loại này đều bị xóa!");

        builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<KhoanChi> khoanChis = ThuChiDatabase.getInstance(getContext()).khoanChiDao()
                        .getAllKhoanChiByLoaiChi(loaiChi.getIdLoaiChi());
                for(KhoanChi kc : khoanChis) {
                    ThuChiDatabase.getInstance(getContext()).khoanChiDao().deleteAllKhoanChiByLoaiChi(kc.getIdKhoanChi());
                }
                ThuChiDatabase.getInstance(getContext()).loaiChiDao().deleteLoaiChi(loaiChi);
                loadData();
                chiViewModel.setKhoanChis(ThuChiDatabase.getInstance(getContext()).khoanChiDao().getAllKhoanChi());
            }
        });

        builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}