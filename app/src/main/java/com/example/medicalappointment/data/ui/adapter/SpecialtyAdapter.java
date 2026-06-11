package com.example.medicalappointment.data.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicalappointment.R;
import com.example.medicalappointment.data.model.Specialty;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder> {

    private final List<Specialty> specialties;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Specialty specialty);
    }

    public SpecialtyAdapter(List<Specialty> specialties) {
        this.specialties = new ArrayList<>(specialties);
    }

    public void updateData(List<Specialty> newSpecialties) {
        this.specialties.clear();
        if (newSpecialties != null) {
            this.specialties.addAll(newSpecialties);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpecialtyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specialty, parent, false);
        return new SpecialtyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtyViewHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.bind(specialty, listener);
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    static class SpecialtyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcon;
        private final TextView tvName;

        public SpecialtyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_specialty_icon);
            tvName = itemView.findViewById(R.id.tv_specialty_name);
        }

        public void bind(Specialty specialty, OnItemClickListener listener) {
            tvName.setText(specialty.getName());
            
            // Map icons based on specialty name
            String name = specialty.getName().toLowerCase();
            int iconResId = R.drawable.ic_cardiology; // Default fallback
            if (name.contains("tim") || name.contains("cardio")) {
                iconResId = R.drawable.ic_cardiology;
            } else if (name.contains("nhi") || name.contains("pediatric")) {
                iconResId = R.drawable.ic_pediatrics;
            } else if (name.contains("sản") || name.contains("phụ") || name.contains("gyne")) {
                iconResId = R.drawable.ic_gynecology;
            }
            
            ivIcon.setImageResource(iconResId);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(specialty);
                }
            });
        }
    }
}
