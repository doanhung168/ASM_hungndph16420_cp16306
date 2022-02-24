package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.interfaces.IChuyenDoiInToString;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanThu;

import java.util.List;

public class KhoanThuRecyclerViewAdapter extends RecyclerView.Adapter<KhoanThuRecyclerViewAdapter.KhoanThuViewHolder> {

    private List<KhoanThu> khoanThuList;
    private IClickItem iClickItem;
    private IChuyenDoiInToString iChuyenDoiInToString;

    public KhoanThuRecyclerViewAdapter(IClickItem iClickItem, IChuyenDoiInToString iChuyenDoiInToString) {
        this.iClickItem = iClickItem;
        this.iChuyenDoiInToString = iChuyenDoiInToString;
    }

    public void setData(List<KhoanThu> khoanThuList) {
        this.khoanThuList = khoanThuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KhoanThuRecyclerViewAdapter.KhoanThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent,false);
        return new KhoanThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuRecyclerViewAdapter.KhoanThuViewHolder holder, int position) {
        KhoanThu khoanThu = khoanThuList.get(position);
        if(khoanThu == null) {
            return;
        }

        holder.tvLoaiThu.setText(iChuyenDoiInToString.onChuyenDoiIntoString(khoanThu.getIdLoaiThu()));
        holder.tvThoiGianThu.setText(khoanThu.getThoiGianThu());
        holder.tvTenKhoanThu.setText(khoanThu.getTenKhoanThu());
        holder.tvSoTienThu.setText(String.format("%.0f", khoanThu.getSoTienThu()));
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.delete(khoanThu);
            }
        });

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.edit(khoanThu);
            }
        });
    }




    @Override
    public int getItemCount() {
        if(khoanThuList != null) {
            return khoanThuList.size();
        }
        return 0;
    }

    public class KhoanThuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenKhoanThu, tvThoiGianThu, tvSoTienThu,tvLoaiThu;
        private ImageView imvEdit, imvDelete;
        public KhoanThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoTienThu = itemView.findViewById(R.id.tvSoTienThu);
            tvTenKhoanThu = itemView.findViewById(R.id.tvTenKhoanThu);
            tvThoiGianThu = itemView.findViewById(R.id.tvTimeThu);
            imvEdit = itemView.findViewById(R.id.item_imvEdit);
            imvDelete = itemView.findViewById(R.id.item_imvDelete);
            tvLoaiThu = itemView.findViewById(R.id.tvLoaiThu);
        }
    }
}
