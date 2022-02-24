package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.Adapters.KhoanThuRecyclerViewAdapter;
import com.doanhung.asm_hungndph16420_cp16306.Databases.ThuChiDatabase;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IChuyenDoiInToString;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;
import com.doanhung.asm_hungndph16420_cp16306.viewmodels.ThuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KhoanThuFragment extends Fragment implements IClickItem, View.OnClickListener, IChuyenDoiInToString {
    private RecyclerView rcvKhoanThu;
    private FloatingActionButton f_btnAddKhoanThu;
    private KhoanThuRecyclerViewAdapter khoanThuAdapter;
    private List<KhoanThu> khoanThuList;
    private ThuViewModel thuViewModel;

    public KhoanThuFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvKhoanThu = view.findViewById(R.id.rcvKhoanThu);
        f_btnAddKhoanThu = view.findViewById(R.id.f_btnAddKhoanThu);

        khoanThuAdapter = new KhoanThuRecyclerViewAdapter(this, this);
        khoanThuList = new ArrayList<>();
        khoanThuList = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getAllKhoanThu();
        khoanThuAdapter.setData(khoanThuList);

        rcvKhoanThu.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvKhoanThu.setAdapter(khoanThuAdapter);
        f_btnAddKhoanThu.setOnClickListener(this);

        thuViewModel = new ViewModelProvider(requireActivity()).get(ThuViewModel.class);
        thuViewModel.getKhoanThus().observe(getViewLifecycleOwner(), list -> {
            khoanThuList = list;
            khoanThuAdapter.setData(khoanThuList);
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_btnAddKhoanThu:
                addKhoanThu();
                break;
        }
    }

    private void addKhoanThu() {
        EditText edtTenKhoanThu, edtSoTienThu, edtNoteKhoanThu, edtTimeKhoanThu;
        Spinner spnLoaiKhoanThu;
        Button btnThemKT, btnHuy;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_khoan_thu);

        edtTenKhoanThu = dialog.findViewById(R.id.edtTenKhoanThu);
        edtSoTienThu = dialog.findViewById(R.id.edtSoTienThu);
        edtNoteKhoanThu = dialog.findViewById(R.id.edtNoteKhoanThu);
        edtTimeKhoanThu = dialog.findViewById(R.id.edtTimeKhoanThu);
        spnLoaiKhoanThu = dialog.findViewById(R.id.spnKhoanThu);
        btnThemKT = dialog.findViewById(R.id.btnAddKhoanThu);
        btnHuy = dialog.findViewById(R.id.btnHuyAddKhoanThu);


        List<LoaiThu> loaiThuList = ThuChiDatabase.getInstance(getContext()).loaiThuDAO().getLoais();
        List<String> tenLoaiThuList = new ArrayList<>();
        for(LoaiThu lt : loaiThuList) {
            tenLoaiThuList.add(lt.getTenLoai());
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tenLoaiThuList);
        spnLoaiKhoanThu.setAdapter(adapter);



        edtTimeKhoanThu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtTimeKhoanThu);
                }
            }
        });

        edtTimeKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtTimeKhoanThu);
            }
        });

        btnThemKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhoanThu = edtTenKhoanThu.getText().toString().trim();
                double soTienThu = Double.parseDouble(edtSoTienThu.getText().toString().trim());
                String thoiGianThu = edtTimeKhoanThu.getText().toString().trim();
                String noteKhoanThu = edtNoteKhoanThu.getText().toString().trim();
                int loaiThu = getLoaiThu(spnLoaiKhoanThu.getSelectedItem().toString());

                KhoanThu khoanThu = new KhoanThu(tenKhoanThu, soTienThu, loaiThu, thoiGianThu, noteKhoanThu);
                ThuChiDatabase.getInstance(getContext()).khoanThuDAO().insertKhoanThu(khoanThu);
                loadData();
                dialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private int getLoaiThu(String toString) {
        List<LoaiThu> loaiThuList = ThuChiDatabase.getInstance(getContext()).loaiThuDAO().getLoais();
        for(LoaiThu lt : loaiThuList) {
            if(toString.equals(lt.getTenLoai())){
                return lt.getId();
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

    private void loadData() {
        khoanThuList = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getAllKhoanThu();
        khoanThuAdapter.setData(khoanThuList);
    }

    @Override
    public String onChuyenDoiIntoString(int i) {
        List<LoaiThu> loaiThuList = ThuChiDatabase.getInstance(getContext()).loaiThuDAO().getLoais();
        for(LoaiThu lt : loaiThuList){
            if(i == lt.getId()){
                return lt.getTenLoai();
            }
        }
        return "";
    }

    @Override
    public void edit(Object object) {
        if(!(object instanceof KhoanThu)) {
            return;
        }
        KhoanThu khoanThu = (KhoanThu) object;
        update(khoanThu);
    }

    private void update(KhoanThu khoanThu) {
        EditText edtTenKhoanThu, edtSoTienThu, edtNoteKhoanThu, edtTimeKhoanThu;
        TextView tvTile;
        Spinner spnLoaiKhoanThu;
        Button btnThemKT, btnHuy;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_khoan_thu);

        edtTenKhoanThu = dialog.findViewById(R.id.edtTenKhoanThu);
        edtSoTienThu = dialog.findViewById(R.id.edtSoTienThu);
        edtNoteKhoanThu = dialog.findViewById(R.id.edtNoteKhoanThu);
        edtTimeKhoanThu = dialog.findViewById(R.id.edtTimeKhoanThu);
        spnLoaiKhoanThu = dialog.findViewById(R.id.spnKhoanThu);
        btnThemKT = dialog.findViewById(R.id.btnAddKhoanThu);
        btnHuy = dialog.findViewById(R.id.btnHuyAddKhoanThu);
        tvTile = dialog.findViewById(R.id.title);


        tvTile.setText("Cập nhật khoản Thu");
        btnThemKT.setText("Update");
        edtNoteKhoanThu.setText(khoanThu.getNoteKhoanThu());
        edtSoTienThu.setText(String.format("%.0f", khoanThu.getSoTienThu()));
        edtTimeKhoanThu.setText(khoanThu.getThoiGianThu());
        edtTenKhoanThu.setText(khoanThu.getTenKhoanThu());


        List<LoaiThu> loaiThuList = ThuChiDatabase.getInstance(getContext()).loaiThuDAO().getLoais();
        List<String> tenLoaiThuList = new ArrayList<>();
        for(LoaiThu lt : loaiThuList) {
            if(lt.getId() == khoanThu.getIdLoaiThu()) {
                tenLoaiThuList.add(0, lt.getTenLoai());
            } else {
                tenLoaiThuList.add(lt.getTenLoai());
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tenLoaiThuList);
        spnLoaiKhoanThu.setAdapter(adapter);
        edtTimeKhoanThu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtTimeKhoanThu);
                }
            }
        });
        edtTimeKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtTimeKhoanThu);
            }
        });
        btnThemKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhoanThu = edtTenKhoanThu.getText().toString().trim();
                double soTienThu = Double.parseDouble(edtSoTienThu.getText().toString().trim());
                String thoiGianThu = edtTimeKhoanThu.getText().toString().trim();
                String noteKhoanThu = edtNoteKhoanThu.getText().toString().trim();
                int loaiThu = getLoaiThu(spnLoaiKhoanThu.getSelectedItem().toString());

                khoanThu.setTenKhoanThu(tenKhoanThu);
                khoanThu.setSoTienThu(soTienThu);
                khoanThu.setThoiGianThu(thoiGianThu);
                khoanThu.setNoteKhoanThu(noteKhoanThu);
                khoanThu.setIdLoaiThu(loaiThu);
                ThuChiDatabase.getInstance(getContext()).khoanThuDAO().updateKhoanThu(khoanThu);
                loadData();
                dialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void delete(Object object) {
        if(! (object instanceof  KhoanThu)){
            return;
        }
        KhoanThu khoanThu = (KhoanThu) object;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Xóa Khoản Thu");
        builder.setMessage("Bạn có chắn muốn xóa khoản thu: " + khoanThu.getTenKhoanThu() + "?" );
        builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThuChiDatabase.getInstance(getContext()).khoanThuDAO().deleteKhoanThu(khoanThu);
                loadData();
            }
        });
        builder.show();
    }

}
