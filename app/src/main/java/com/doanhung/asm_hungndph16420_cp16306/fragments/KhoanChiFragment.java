package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.KhoanChiRecylerViewAdapter;
import com.doanhung.asm_hungndph16420_cp16306.Databases.ThuChiDatabase;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IChuyenDoiInToString;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.viewmodels.ChiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanChiFragment extends Fragment implements IClickItem, IChuyenDoiInToString {
    private RecyclerView recyclerView;
    private FloatingActionButton f_btnAddKhoanChi;
    private KhoanChiRecylerViewAdapter adapter;
    private List<KhoanChi> khoanChiList;
    private ChiViewModel chiViewModel;

    public KhoanChiFragment() {
        // Required empty public constructor
    }


    public static KhoanChiFragment newInstance() {
        KhoanChiFragment fragment = new KhoanChiFragment();
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
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        f_btnAddKhoanChi = view.findViewById(R.id.f_btnAddKhoanChi);
        chiViewModel = new ViewModelProvider(requireActivity()).get(ChiViewModel.class);
        chiViewModel.getKhoanChis().observe(getViewLifecycleOwner(), list -> {
            khoanChiList = list;
            adapter.setData(khoanChiList);
        });


        adapter = new KhoanChiRecylerViewAdapter(this, this);
        khoanChiList = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getAllKhoanChi();
        adapter.setData(khoanChiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        f_btnAddKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKhoanChi();
            }
        });
    }

    private void addKhoanChi() {
        EditText edtTenKhoanChi, edtSoTienChi, edtNoteKhoanChi, edtTimeKhoanChi;
        Spinner spnLoaiKhoanChi;
        Button btnThemKC, btnHuyThemKC;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_khoan_chi);

        edtTenKhoanChi = dialog.findViewById(R.id.edtTenKhoanChi);
        edtSoTienChi = dialog.findViewById(R.id.edtSoTienChi);
        edtNoteKhoanChi = dialog.findViewById(R.id.edtNoteKhoanChi);
        edtTimeKhoanChi = dialog.findViewById(R.id.edtTimeKhoanChi);
        spnLoaiKhoanChi = dialog.findViewById(R.id.spnKhoanChi);
        btnThemKC = dialog.findViewById(R.id.btnAddKhoanChi);
        btnHuyThemKC = dialog.findViewById(R.id.btnHuyAddKhoanChi);


        List<LoaiChi> loaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        List<String> tenLoaiChiList = new ArrayList<>();
        for(LoaiChi lc : loaiChiList) {
            tenLoaiChiList.add(lc.getTenLoaiChi());
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tenLoaiChiList);
        spnLoaiKhoanChi.setAdapter(adapter);



        edtTimeKhoanChi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtTimeKhoanChi);
                }
            }
        });

        edtTimeKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtTimeKhoanChi);
            }
        });

        btnThemKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhoanThu = edtTenKhoanChi.getText().toString().trim();
                double soTienThu = Double.parseDouble(edtSoTienChi.getText().toString().trim());
                String thoiGianThu = edtTimeKhoanChi.getText().toString().trim();
                String noteKhoanThu = edtNoteKhoanChi.getText().toString().trim();
                int loaiThu = getLoaiChi(spnLoaiKhoanChi.getSelectedItem().toString());

                KhoanChi khoanChi = new KhoanChi(tenKhoanThu, soTienThu, loaiThu, thoiGianThu, noteKhoanThu);
                ThuChiDatabase.getInstance(getContext()).khoanChiDao().insertKhoanChi(khoanChi);
                loadData();
                dialog.dismiss();
            }
        });

        btnHuyThemKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void loadData() {
        khoanChiList = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getAllKhoanChi();
        adapter.setData(khoanChiList);
    }

    private int getLoaiChi(String toString) {
        List<LoaiChi> loaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        for(LoaiChi lc : loaiChiList) {
            if(toString.equals(lc.getTenLoaiChi())){
                return lc.getIdLoaiChi();
            }
        }
        return -1;
    }

    private void chonNgay(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                editText.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTime().getTime());
        datePickerDialog.show();
    }

    @Override
    public String onChuyenDoiIntoString(int i) {
        List<LoaiChi> loaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        for(LoaiChi lc : loaiChiList) {
            if(lc.getIdLoaiChi() == i) {
                return lc.getTenLoaiChi();
            }
        }
        return "";
    }

    @Override
    public void edit(Object object) {
        if(!(object instanceof  KhoanChi)) {
            return;
        }
        KhoanChi khoanChi = (KhoanChi) object;
        updateKhoanChi(khoanChi);
    }

    @Override
    public void delete(Object object) {
        if(! (object instanceof  KhoanChi)){
            return;
        }
        KhoanChi khoanChi = (KhoanChi) object;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Xóa Khoản Chi");
        builder.setMessage("Bạn có chắn muốn xóa khoản chi: " + khoanChi.getTenKhoanChi() + "?" );
        builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThuChiDatabase.getInstance(getContext()).khoanChiDao().deleteKhoanChi(khoanChi);
                loadData();
            }
        });
        builder.show();
    }

    private void updateKhoanChi(KhoanChi khoanChi) {
        EditText edtTenKhoanChi, edtSoTienChi, edtNoteKhoanChi, edtTimeKhoanChi;
        Spinner spnLoaiKhoanChi;
        Button btnThemKC, btnHuyThemKC;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_khoan_chi);

        edtTenKhoanChi = dialog.findViewById(R.id.edtTenKhoanChi);
        edtSoTienChi = dialog.findViewById(R.id.edtSoTienChi);
        edtNoteKhoanChi = dialog.findViewById(R.id.edtNoteKhoanChi);
        edtTimeKhoanChi = dialog.findViewById(R.id.edtTimeKhoanChi);
        spnLoaiKhoanChi = dialog.findViewById(R.id.spnKhoanChi);
        btnThemKC = dialog.findViewById(R.id.btnAddKhoanChi);
        btnHuyThemKC = dialog.findViewById(R.id.btnHuyAddKhoanChi);

        btnThemKC.setText("Sửa");

        edtNoteKhoanChi.setText(khoanChi.getNoteKhoanChi());
        edtSoTienChi.setText(String.format("%.0f", khoanChi.getSoTienChi()));
        edtTenKhoanChi.setText(khoanChi.getTenKhoanChi());
        edtTimeKhoanChi.setText(khoanChi.getThoiGianChi());


        List<LoaiChi> loaiChiList = ThuChiDatabase.getInstance(getContext()).loaiChiDao().getAllLoaiChi();
        List<String> tenLoaiChiList = new ArrayList<>();
        for(LoaiChi lc : loaiChiList) {
            if(khoanChi.getIdLoaiChi() == lc.getIdLoaiChi()) {
                tenLoaiChiList.add(0, lc.getTenLoaiChi());
            } else {
                tenLoaiChiList.add(lc.getTenLoaiChi());
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tenLoaiChiList);
        spnLoaiKhoanChi.setAdapter(adapter);



        edtTimeKhoanChi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtTimeKhoanChi);
                }
            }
        });

        edtTimeKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtTimeKhoanChi);
            }
        });

        btnThemKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhoanThu = edtTenKhoanChi.getText().toString().trim();
                double soTienThu = Double.parseDouble(edtSoTienChi.getText().toString().trim());
                String thoiGianThu = edtTimeKhoanChi.getText().toString().trim();
                String noteKhoanThu = edtNoteKhoanChi.getText().toString().trim();
                int loaiThu = getLoaiChi(spnLoaiKhoanChi.getSelectedItem().toString());

                khoanChi.setNoteKhoanChi(noteKhoanThu);
                khoanChi.setThoiGianChi(thoiGianThu);
                khoanChi.setTenKhoanChi(tenKhoanThu);
                khoanChi.setSoTienChi(soTienThu);
                khoanChi.setIdLoaiChi(loaiThu);

                ThuChiDatabase.getInstance(getContext()).khoanChiDao().updateKhoanChi(khoanChi);
                loadData();
                dialog.dismiss();
            }
        });

        btnHuyThemKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}