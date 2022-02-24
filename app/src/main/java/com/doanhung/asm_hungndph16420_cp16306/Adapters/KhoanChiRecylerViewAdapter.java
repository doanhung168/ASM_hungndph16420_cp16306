package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.entity.KhoanChi;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IChuyenDoiInToString;
import com.doanhung.asm_hungndph16420_cp16306.interfaces.IClickItem;

import java.util.List;

public class KhoanChiRecylerViewAdapter extends RecyclerView.Adapter<KhoanChiRecylerViewAdapter.KhoanChiViewHolder> {

    private List<KhoanChi> mKhoanChiList;
    private IClickItem iClickItem;
    private IChuyenDoiInToString iChuyenDoiInToString;

    public KhoanChiRecylerViewAdapter(IClickItem iClickItem, IChuyenDoiInToString iChuyenDoiInToString) {
        this.iClickItem = iClickItem;
        this.iChuyenDoiInToString = iChuyenDoiInToString;
    }

    public void setData(List<KhoanChi> list) {
        mKhoanChiList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_chi, parent, false);
        return new KhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiViewHolder holder, int position) {
        KhoanChi khoanChi = mKhoanChiList.get(position);
        if(khoanChi == null) {
            return;
        }

        holder.tvThoiGianKC.setText(khoanChi.getThoiGianChi());
        holder.tvSoTienChi.setText(String.format("%.0f", khoanChi.getSoTienChi()));
        holder.tvTenKhoanChi.setText(khoanChi.getTenKhoanChi());
        holder.tvLoaiKC.setText(iChuyenDoiInToString.onChuyenDoiIntoString(khoanChi.getIdLoaiChi()));
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.delete(khoanChi);
            }
        });

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.edit(khoanChi);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mKhoanChiList != null) {
            return mKhoanChiList.size();
        }
        return 0;
    }

    public class KhoanChiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenKhoanChi, tvThoiGianKC, tvLoaiKC, tvSoTienChi;
        private ImageView imvDelete , imvEdit;
        public KhoanChiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLoaiKC = itemView.findViewById(R.id.tvLoaiChi);
            tvTenKhoanChi = itemView.findViewById(R.id.tvTenKhoanChi);
            tvSoTienChi = itemView.findViewById(R.id.tvSoTienChi);
            tvThoiGianKC = itemView.findViewById(R.id.tvTimeChi);

            imvEdit = itemView.findViewById(R.id.item_imvEdit);
            imvDelete = itemView.findViewById(R.id.item_imvDelete);

        }
    }
}
