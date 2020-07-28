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

public class BLEAdapter extends ArrayAdapter<BLEModel> {
    Context context;
    List<BLEModel> BLEAgent ;
    public BLEAdapter(@NonNull Context context, List<BLEModel> BLEAgent) {
        super(context, R.layout.custom_list_item_ble,BLEAgent);
        this.context = context ;
        this.BLEAgent = BLEAgent;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_ble,null,true);
        TextView tvidBLE = view.findViewById(R.id.idbleV);
        TextView tvheure = view.findViewById(R.id.heureV);
        tvidBLE.setText(BLEAgent.get(position).getIdBLE()+"");
        tvheure.setText(BLEAgent.get(position).getHeurAjout());

        return view;
    }
}
