package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.SquadPlayer;

import java.util.ArrayList;
import java.util.List;

public class SquadAdapter extends RecyclerView.Adapter<SquadAdapter.ViewHolder> {
    private List<SquadPlayer> allPlayers;
    private List<SquadPlayer> filteredPlayers;
    private String currentFilter = "All";

    public SquadAdapter(List<SquadPlayer> players) {
        this.allPlayers = new ArrayList<>(players);
        this.filteredPlayers = new ArrayList<>(players);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player_squad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SquadPlayer player = filteredPlayers.get(position);

        holder.shirtNumber.setText(String.valueOf(player.getShirtNumber()));
        holder.playerName.setText(player.getName());
        holder.playerPosition.setText(player.getPosition());
        holder.playerNationality.setText(player.getNationality());
    }

    @Override
    public int getItemCount() {
        return filteredPlayers.size();
    }

    public void filterByPosition(String position) {
        currentFilter = position;
        filteredPlayers.clear();

        if (position.equals("All")) {
            filteredPlayers.addAll(allPlayers);
        } else {
            String filterPosition;
            switch (position) {
                case "GK":
                    filterPosition = "Goalkeeper";
                    break;
                case "DEF":
                    filterPosition = "Defender";
                    break;
                case "MID":
                    filterPosition = "Midfielder";
                    break;
                case "ATT":
                    filterPosition = "Attacker";
                    break;
                default:
                    filterPosition = position;
            }

            for (SquadPlayer player : allPlayers) {
                if (player.getPosition().equals(filterPosition)) {
                    filteredPlayers.add(player);
                }
            }
        }

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shirtNumber, playerName, playerPosition, playerNationality;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shirtNumber = itemView.findViewById(R.id.shirtNumber);
            playerName = itemView.findViewById(R.id.playerName);
            playerPosition = itemView.findViewById(R.id.playerPosition);
            playerNationality = itemView.findViewById(R.id.playerNationality);
        }
    }
}
