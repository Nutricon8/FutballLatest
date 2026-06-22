package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.MatchEvent;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {
    private final List<MatchEvent> goals;
    private final int homeTeamId;

    public GoalsAdapter(List<MatchEvent> goals, int homeTeamId) {
        this.goals = goals;
        this.homeTeamId = homeTeamId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goal_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchEvent goal = goals.get(position);

        String minuteText = goal.getExtraTime() != null
                ? goal.getMinute() + "+" + goal.getExtraTime() + "'"
                : goal.getMinute() + "'";
        holder.minuteText.setText(minuteText);
        holder.scorerName.setText(goal.getScorer() != null ? goal.getScorer().getName() : "Goal");

        if (goal.getAssist() != null && goal.getAssist().getName() != null) {
            holder.assistName.setVisibility(View.VISIBLE);
            holder.assistName.setText(holder.itemView.getContext().getString(R.string.assist) + ": " + goal.getAssist().getName());
        } else {
            holder.assistName.setVisibility(View.GONE);
        }

        holder.teamName.setText(goal.getTeam() != null ? goal.getTeam().getName() : "");
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView minuteText, scorerName, assistName, teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            minuteText = itemView.findViewById(R.id.minuteText);
            scorerName = itemView.findViewById(R.id.scorerName);
            assistName = itemView.findViewById(R.id.assistName);
            teamName = itemView.findViewById(R.id.teamName);
        }
    }
}
