package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;
import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.LoaiThu;

import java.util.List;

public class LoaiThuRecyclerViewAdapter extends RecyclerView.Adapter<LoaiThuRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<LoaiThu> list;
    private IClickItem iClickItem;


    public LoaiThuRecyclerViewAdapter(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public void setData(List<LoaiThu> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiThuRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_thu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuRecyclerViewAdapter.ViewHolder holder, int position) {
        LoaiThu loaiThu = list.get(position);
        if(loaiThu == null){
            return;
        }
        holder.tvTen.setText(loaiThu.getTenLoai());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.edit(loaiThu);
            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.delete(loaiThu);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTen;
        private ImageView imvEdit;
        private ImageView imvDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTen = itemView.findViewById(R.id.item_tvTen);
            imvEdit = itemView.findViewById(R.id.item_imvEdit);
            imvDelete = itemView.findViewById(R.id.item_imvDelete);

        }
    }
}
