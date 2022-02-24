package com.doanhung.asm_hungndph16420_cp16306.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhung.asm_hungndph16420_cp16306.R;
import com.doanhung.asm_hungndph16420_cp16306.object.GioiThieu;

import java.util.List;

public class GioiThieuRecyclerAdapter extends RecyclerView.Adapter<GioiThieuRecyclerAdapter.GioiThieuViewHolder> {

    private List<GioiThieu> list;

    public GioiThieuRecyclerAdapter(List<GioiThieu> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GioiThieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gioi_thieu, parent, false);
        return new GioiThieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioiThieuViewHolder holder, int position) {
        GioiThieu gioiThieu = list.get(position);
        if (gioiThieu == null) {
            return;
        }

        holder.tvTitle.setText(gioiThieu.getTitle());
        holder.tvContent.setText(gioiThieu.getContent());
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class GioiThieuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent;
        public GioiThieuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvContent = itemView.findViewById(R.id.tvContent);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
