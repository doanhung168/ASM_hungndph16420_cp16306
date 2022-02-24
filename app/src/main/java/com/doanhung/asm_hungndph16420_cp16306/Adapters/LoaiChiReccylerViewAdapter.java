package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiChi;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.viewmodels.ThuViewModel;

import java.util.List;

public class LoaiChiReccylerViewAdapter extends RecyclerView.Adapter<LoaiChiReccylerViewAdapter.LoaiChiViewHolder> {

    private List<LoaiChi> loaiChiList;
    private IClickItem iClickItem;

    public LoaiChiReccylerViewAdapter(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public void setData(List<LoaiChi> list){
        loaiChiList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_chi, parent, false);
        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        LoaiChi loaiChi = loaiChiList.get(position);
        if(loaiChi == null) {
            return;
        }
        holder.tvTenLoaiChi.setText(loaiChi.getTenLoaiChi());
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.delete(loaiChi);
            }
        });

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.edit(loaiChi);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(loaiChiList != null) {
            return loaiChiList.size();
        }
        return 0;
    }

    public class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenLoaiChi;
        private ImageView imvDelete, imvEdit;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiChi = itemView.findViewById(R.id.item_tvTen);
            imvDelete = itemView.findViewById(R.id.item_imvDelete);
            imvEdit = itemView.findViewById(R.id.item_imvEdit);

        }
    }
}
