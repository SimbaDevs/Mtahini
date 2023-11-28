package com.symon.mtahini;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {
    ArrayList<Unit> units;

    public UnitAdapter(ArrayList<Unit> units) {
        this.units = units;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_post_item, parent, false);

        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        Unit unit = units.get(position);

        holder.unitName.setText(unit.getName());
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder {

        TextView unitName;
        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            unitName = itemView.findViewById(R.id.unit_name_text);

        }
    }
}
