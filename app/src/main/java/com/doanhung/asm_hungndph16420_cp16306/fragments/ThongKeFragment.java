package com.doanhung.asm_hungndph16420_cp16306.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.doanhung.asm_hungndph16420_cp16306.Databases.ThuChiDatabase;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private List<String> luaChonList;
    private ArrayAdapter arrayAdapter;

    private Spinner spinner;
    private TextView tvKhoanChi, tvKhoanThu;

    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
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
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        tvKhoanChi = view.findViewById(R.id.tvKhoanChi);
        tvKhoanThu = view.findViewById(R.id.tvKhoanThu);

        luaChonList = new ArrayList<>();
        getList();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, luaChonList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        getKhoanChi();
        getKhoanThu();



    }


    private void getList() {
        luaChonList.add("All");
        luaChonList.add("Hôm nay");
        luaChonList.add("Tuần này");
        luaChonList.add("Tháng này");
        luaChonList.add("Ba tháng gần đây");
        luaChonList.add("Sáu tháng gần đây");
        luaChonList.add("Năm nay");
        luaChonList.add("Lựa chọn tùy ý");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (luaChonList.get(position)) {
            case "All":
                getKhoanChi();
                getKhoanThu();
                break;
            case "Hôm nay":
                List<KhoanChi> khoanChiList = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiToday();
                tinhTienKhoanChi(khoanChiList);
                List<KhoanThu> khoanThuList = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuToday();
                tinhTienKhoanThu(khoanThuList);
                break;
            case "Tuần này":
                Calendar calendar = Calendar.getInstance();
                int date_of_week = calendar.get(Calendar.DAY_OF_WEEK);
                switch (date_of_week) {
                    case 1:
                        List<KhoanThu> khoanThuList1 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-6 day");
                        tinhTienKhoanThu(khoanThuList1);
                        List<KhoanChi> khoanChiList1 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-6 day");
                        tinhTienKhoanChi(khoanChiList1);
                        break;
                    case 2:
                        List<KhoanThu> khoanThuList2 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-0 day");
                        tinhTienKhoanThu(khoanThuList2);
                        List<KhoanChi> khoanChiList2 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-0 day");
                        tinhTienKhoanChi(khoanChiList2);
                        break;
                    case 3:
                        List<KhoanThu> khoanThuList3 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-1 day");
                        tinhTienKhoanThu(khoanThuList3);
                        List<KhoanChi> khoanChiList3 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-1 day");
                        tinhTienKhoanChi(khoanChiList3);
                        break;
                    case 4:
                        List<KhoanThu> khoanThuList4 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-2 day");
                        tinhTienKhoanThu(khoanThuList4);
                        List<KhoanChi> khoanChiList4 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-2 day");
                        tinhTienKhoanChi(khoanChiList4);
                        break;
                    case 5:
                        List<KhoanThu> khoanThuList5 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-3 day");
                        tinhTienKhoanThu(khoanThuList5);
                        List<KhoanChi> khoanChiList5 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-3 day");
                        tinhTienKhoanChi(khoanChiList5);
                        break;
                    case 6:
                        List<KhoanThu> khoanThuList6 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-4 day");
                        tinhTienKhoanThu(khoanThuList6);
                        List<KhoanChi> khoanChiList6 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-4 day");
                        tinhTienKhoanChi(khoanChiList6);
                        break;
                    case 7:
                        List<KhoanThu> khoanThuList7 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-5 day");
                        tinhTienKhoanThu(khoanThuList7);
                        List<KhoanChi> khoanChiList7 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-5 day");
                        tinhTienKhoanChi(khoanChiList7);
                        break;
                    default:
                        break;

                }
                break;
            case "Tháng này":
                Calendar calendar2 = Calendar.getInstance();
                int dateOfMonth = calendar2.get(Calendar.DAY_OF_MONTH);
                List<KhoanThu> khoanThuList1 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-" + (dateOfMonth - 1) + " day" );
                tinhTienKhoanThu(khoanThuList1);
                List<KhoanChi> khoanChiList1 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-" + (dateOfMonth - 1) + " day");
                tinhTienKhoanChi(khoanChiList1);
                break;

            case "Ba tháng gần đây":
                List<KhoanThu> khoanThuList2 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-3 month" );
                tinhTienKhoanThu(khoanThuList2);
                List<KhoanChi> khoanChiList2 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-3 month");
                tinhTienKhoanChi(khoanChiList2);
                break;
            case "Sáu tháng gần đây":
                List<KhoanThu> khoanThuList3 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-6 month" );
                tinhTienKhoanThu(khoanThuList3);
                List<KhoanChi> khoanChiList3 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-6 month");
                tinhTienKhoanChi(khoanChiList3);
                break;
            case "Năm nay":
                Calendar calendar1 = Calendar.getInstance();
                int dayOfYear = calendar1.get(Calendar.DAY_OF_YEAR);
                List<KhoanThu> khoanThuList4 = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuByTuanNay("-" + (dayOfYear - 1) + " day" );
                tinhTienKhoanThu(khoanThuList4);
                List<KhoanChi> khoanChiList4 = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiByTuanNay("-" + (dayOfYear - 1) + " day");
                tinhTienKhoanChi(khoanChiList4);
                break;
            case "Lựa chọn tùy ý":
                Log.i("ds","fsd");
                thongKeTheoNgay();
                break;
            default:
                thongKeTheoNgay();
                break;

        }
    }

    private void thongKeTheoNgay() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.item_lua_chon_thong_ke);

        EditText edtNgayBD = dialog.findViewById(R.id.tvNgayBD);
        EditText edtNgayKT = dialog.findViewById(R.id.tvNgayKT);
        Button btnTimKiem = dialog.findViewById(R.id.btnTimKiem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        edtNgayBD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtNgayBD);
                }
            }
        });

        edtNgayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtNgayBD);
            }
        });

        edtNgayKT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    chonNgay(edtNgayKT);
                }
            }
        });

        edtNgayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay(edtNgayKT);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngay1 = edtNgayBD.getText().toString().trim();
                String ngay2 = edtNgayKT.getText().toString().trim();
                List<KhoanThu> khoanThuList = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getKhoanThuTheoNgay(ngay1, ngay2);
                tinhTienKhoanThu(khoanThuList);
                List<KhoanChi> khoanChis = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getKhoanChiTheoNgay(ngay1, ngay2);
                tinhTienKhoanChi(khoanChis);
                dialog.cancel();
            }
        });

        dialog.show();
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
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void getKhoanThu() {
        List<KhoanThu> khoanThuList = ThuChiDatabase.getInstance(getContext()).khoanThuDAO().getAllKhoanThu();
        tinhTienKhoanThu(khoanThuList);
    }

    private void getKhoanChi() {
        List<KhoanChi> khoanChiList = ThuChiDatabase.getInstance(getContext()).khoanChiDao().getAllKhoanChi();
        tinhTienKhoanChi(khoanChiList);
    }

    public void tinhTienKhoanThu(List<KhoanThu> list) {
        double st = 0;
        for (KhoanThu kt : list) {
            st = st + kt.getSoTienThu();
        }
        String result = String.format("%.0f", st);
        Log.i("ds", result);
        tvKhoanThu.setText(result);
    }

    public void tinhTienKhoanChi(List<KhoanChi> list) {
        double st = 0;
        for (KhoanChi kc : list) {
            st = st + kc.getSoTienChi();
        }
        String result = String.format("%.0f", st);
        Log.i("ds", result);
        tvKhoanChi.setText(result);
    }
}