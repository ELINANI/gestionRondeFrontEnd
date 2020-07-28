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

public class TraceAdapter extends ArrayAdapter<TraceModel> {
    Context context ;
    List<TraceModel> listTrace ;
    public TraceAdapter(@NonNull Context context, List<TraceModel> listTrace ) {
        super(context, R.layout.custom_list_item_trace,listTrace);
        this.context = context ;
        this.listTrace = listTrace;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_trace,null,true);
        TextView tvcodeTrace = view.findViewById(R.id.codeTraceV);
        TextView tvcodeAgent = view.findViewById(R.id.codeAgentVV);
        tvcodeTrace.setText(listTrace.get(position).getCodeTrace()+"");
        tvcodeAgent.setText(listTrace.get(position).getCodeAgent()+"");

        return view;
    }

}
