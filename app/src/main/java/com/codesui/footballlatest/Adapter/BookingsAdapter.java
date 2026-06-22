package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.MatchBooking;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {
    private final List<MatchBooking> bookings;

    public BookingsAdapter(List<MatchBooking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchBooking booking = bookings.get(position);

        holder.minuteText.setText(booking.getMinute() + "'");
        holder.playerName.setText(booking.getPlayer() != null ? booking.getPlayer().getName() : "Player");

        String cardType;
        int cardColor;
        if ("RED_CARD".equals(booking.getCard())) {
            cardType = holder.itemView.getContext().getString(R.string.red_card);
            cardColor = 0xFFFF0000; // Red
        } else if ("YELLOW_RED_CARD".equals(booking.getCard())) {
            cardType = holder.itemView.getContext().getString(R.string.second_yellow);
            cardColor = 0xFFFF0000; // Red (second yellow = red)
        } else {
            cardType = holder.itemView.getContext().getString(R.string.yellow_card);
            cardColor = 0xFFFFD700; // Yellow
        }

        holder.cardType.setText(cardType);
        holder.cardView.setBackgroundColor(cardColor);
        holder.teamName.setText(booking.getTeam() != null ? booking.getTeam().getName() : "");
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView minuteText, playerName, cardType, teamName;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            minuteText = itemView.findViewById(R.id.minuteText);
            playerName = itemView.findViewById(R.id.playerName);
            cardType = itemView.findViewById(R.id.cardType);
            teamName = itemView.findViewById(R.id.teamName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
