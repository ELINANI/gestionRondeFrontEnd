package com.example.gestionrondefrontend;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class planningAdapter extends ArrayAdapter<PlanningModel> {
 Context context ;
 List<PlanningModel> listPlanning ;
    public planningAdapter(@NonNull Context context, List<PlanningModel> listPlanning) {
        super(context, R.layout.custom_list_item,listPlanning);
        this.context = context ;
        this.listPlanning = listPlanning;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tvCodePlanning = view.findViewById(R.id.codPlaningV);
        TextView tvDesc = view.findViewById(R.id.descV);
        String f = listPlanning.get(position).getCodePlanning()+"";
        tvCodePlanning.setText(f);
        tvDesc.setText(listPlanning.get(position).getDesc());

        return view;
    }
}
