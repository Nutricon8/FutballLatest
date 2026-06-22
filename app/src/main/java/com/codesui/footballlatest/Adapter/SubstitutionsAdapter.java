package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.MatchSubstitution;

import java.util.List;

public class SubstitutionsAdapter extends RecyclerView.Adapter<SubstitutionsAdapter.ViewHolder> {
    private final List<MatchSubstitution> substitutions;

    public SubstitutionsAdapter(List<MatchSubstitution> substitutions) {
        this.substitutions = substitutions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_substitution_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchSubstitution sub = substitutions.get(position);

        holder.minuteText.setText(sub.getMinute() + "'");
        holder.playerIn.setText(sub.getPlayerIn() != null ? sub.getPlayerIn().getName() : "Player In");
        holder.playerOut.setText(sub.getPlayerOut() != null ? sub.getPlayerOut().getName() : "Player Out");
        holder.teamName.setText(sub.getTeam() != null ? sub.getTeam().getName() : "");
    }

    @Override
    public int getItemCount() {
        return substitutions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView minuteText, playerIn, playerOut, teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            minuteText = itemView.findViewById(R.id.minuteText);
            playerIn = itemView.findViewById(R.id.playerIn);
            playerOut = itemView.findViewById(R.id.playerOut);
            teamName = itemView.findViewById(R.id.teamName);
        }
    }
}
