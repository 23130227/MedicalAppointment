package com.example.medicalappointment.ui.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicalappointment.R;
import com.example.medicalappointment.data.model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<Appointment> appointments = new ArrayList<>();
    private final OnAppointmentClickListener listener;

    public interface OnAppointmentClickListener {
        void onItemClick(Appointment appointment);
        void onDeleteClick(String id);
    }

    public AppointmentAdapter(OnAppointmentClickListener listener) {
        this.listener = listener;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appt = appointments.get(position);
        holder.tvPatientId.setText("Patient: " + appt.getPatientId());
        holder.tvSymptoms.setText(appt.getSymptoms());
        holder.tvStatus.setText("Status: " + appt.getStatus());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(appt));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(appt.getId()));
    }

    @Override
    public int getItemCount() { return appointments.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPatientId, tvSymptoms, tvStatus;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPatientId = itemView.findViewById(R.id.itemPatientId);
            tvSymptoms = itemView.findViewById(R.id.itemSymptoms);
            tvStatus = itemView.findViewById(R.id.itemStatus);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}