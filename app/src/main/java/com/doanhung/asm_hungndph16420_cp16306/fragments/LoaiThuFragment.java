package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.LoaiThuRecyclerViewAdapter;
import com.doanhung.asm_hungndph16420_cp16306.Databases.ThuChiDatabase;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;
import com.doanhung.asm_hungndph16420_cp16306.viewmodels.ThuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiThuFragment extends Fragment implements IClickItem {

    // declared views
    private FloatingActionButton f_btnAddLoaiThu;
    private RecyclerView recyclerView;
    private ThuViewModel thuViewModel;

    // declared objects
    private LoaiThuRecyclerViewAdapter loaiThuAdapter;
    private List<LoaiThu> list;

    public LoaiThuFragment() {

    }
    public static LoaiThuFragment newInstance() {
        LoaiThuFragment fragment = new LoaiThuFragment();
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
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        loaiThuAdapter = new LoaiThuRecyclerViewAdapter(this);
        list = new ArrayList<>();
        list = ThuChiDatabase.getInstance(this.getContext()).loaiThuDAO().getLoais();
        loaiThuAdapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(loaiThuAdapter);


        f_btnAddLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_dialogAddLoaiThu();
            }
        });

        thuViewModel = new ViewModelProvider(requireActivity()).get(ThuViewModel.class);

    }


    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        f_btnAddLoaiThu = view.findViewById(R.id.floatBtn);
    }

    private void action_dialogAddLoaiThu() {
        EditText edtTenLoai;
        Button btnHuy, btnThem;

        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_add_loai_thu);

        edtTenLoai = dialog.findViewById(R.id.edtTenLoai);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnThem = dialog.findViewById(R.id.btnThem);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoaiThu(edtTenLoai);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void addLoaiThu(EditText edtTenLoai) {
        String tenLoai = edtTenLoai.getText().toString().trim();
        if(TextUtils.isEmpty(tenLoai)){
            return;
        }
        LoaiThu loaiThu = new LoaiThu(tenLoai);
        ThuChiDatabase.getInstance(this.getContext()).loaiThuDAO().insertLoai(loaiThu);
        loadData();
    }

    public void loadData() {
        list = ThuChiDatabase.getInstance(this.getContext()).loaiThuDAO().getLoais();
        loaiThuAdapter.setData(list);
    }


    @Override
    public void edit(Object object) {
        EditText edtTenCu, edtTenMoi;
        Button btnHuy, btnSua;

        LoaiThu loaiThu = (LoaiThu) object;

        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_edit_loai_thu);

        edtTenCu = dialog.findViewById(R.id.edtTenCu);
        edtTenMoi = dialog.findViewById(R.id.edtTenMoi);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnSua = dialog.findViewById(R.id.btnSua);

        edtTenCu.setText(loaiThu.getTenLoai());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiThu.setTenLoai(edtTenMoi.getText().toString().trim());
                ThuChiDatabase.getInstance(getContext()).loaiThuDAO().updateLoaiThu(loaiThu);
                loadData();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void delete(Object object) {
        LoaiThu loaiThu = (LoaiThu) object;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Xóa Loại Thu");
        builder.setMessage("Bạn có chắn muốn xóa: " + loaiThu.getTenLoai() + "?"
                + "\nNếu xóa loại thu này thì tất cả khoản thu thuộc loại này đều bị xóa!" );
        builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xoaKhoanThuByLoaiThu(loaiThu);
                ThuChiDatabase.getInstance(getContext()).loaiThuDAO().deleteLoaiThu(loaiThu);
                loadData();
                List<KhoanThu> list = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getAllKhoanThu();
                thuViewModel.setKhoanThus(list);
            }
        });
        builder.show();
    }

    private void xoaKhoanThuByLoaiThu(LoaiThu loaiThu) {
        List<KhoanThu> list = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getAllKhoanThuByLoaiThu(loaiThu.getId());
        for(KhoanThu khoanThu : list) {
            ThuChiDatabase.getInstance(getContext()).khoanThuDAO().deleteKhoanThuByLoaiThu(khoanThu.getIdKhoanThu());
        }
    }
}