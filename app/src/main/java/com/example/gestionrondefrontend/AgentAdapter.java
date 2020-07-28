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

public class AgentAdapter extends ArrayAdapter<AgentModel> {
    Context context;
    List<AgentModel> listAgent ;
    public AgentAdapter(@NonNull Context context, List<AgentModel> listAgent) {
        super(context, R.layout.custom_list_item_agent,listAgent);
        this.context = context ;
        this.listAgent = listAgent;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_agent,null,true);
        TextView tvCodeAgent = view.findViewById(R.id.codeAgentV);
        TextView tvnomAgent = view.findViewById(R.id.nomV);
        tvCodeAgent.setText(listAgent.get(position).getCodeAgent()+"");
        tvnomAgent.setText(listAgent.get(position).getNomAgent());

        return view;
    }
}
