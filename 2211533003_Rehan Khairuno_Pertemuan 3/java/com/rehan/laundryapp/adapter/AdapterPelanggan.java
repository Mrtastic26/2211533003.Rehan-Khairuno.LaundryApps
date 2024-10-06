package com.rehan.laundryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rehan.laundryapp.R;
import com.rehan.laundryapp.model.ModelPelanggan;

import java.util.List;

public class AdapterPelanggan extends RecyclerView.Adapter<AdapterPelanggan.ViewHolder> {

    private static final String TAG = AdapterPelanggan.class.getSimpleName();
    private Context context;
    private List<ModelPelanggan> list;
    private View.OnClickListener onItemClicked;

    // Constructor
    public AdapterPelanggan(Context context, List<ModelPelanggan> list) {
        this.context = context;
        this.list = list;
    }

    // Set Item Click Listener
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.onItemClicked = itemClickListener;
    }

    // Create ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelanggan, parent, false);
        return new ViewHolder(view);
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPelanggan item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvHp.setText(item.getHp());
    }

    // Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }

    // Clear the list
    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHp;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvItemPelangganName); // Sesuaikan ID ini dengan XML item_pelanggan
            tvHp = itemView.findViewById(R.id.tvItemPelangganTelp); // Sesuaikan ID ini dengan XML item_pelanggan
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClicked);
        }
    }
}
