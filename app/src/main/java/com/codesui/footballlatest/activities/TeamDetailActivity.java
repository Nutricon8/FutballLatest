package com.codesui.footballlatest.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.codesui.footballlatest.Adapter.SquadAdapter;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.codesui.footballlatest.data.SquadPlayer;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamDetailActivity extends AppCompatActivity {
    private InterstitialManager interstitialManager;
    private AppOpenManager appOpenManager;
    private ProgressBar progressBar;
    private SquadAdapter squadAdapter;
    private List<SquadPlayer> squadPlayers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(this);

        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        squadAdapter = new SquadAdapter(squadPlayers);
        recyclerView.setAdapter(squadAdapter);

        ImageView teamCrest = findViewById(R.id.teamCrest);
        TextView teamName = findViewById(R.id.teamName);
        TextView teamFounded = findViewById(R.id.teamFounded);
        TextView teamColors = findViewById(R.id.teamColors);
        TextView teamVenue = findViewById(R.id.teamVenue);

        // Setup position filter chips
        Chip chipAll = findViewById(R.id.chipAll);
        Chip chipGoalkeepers = findViewById(R.id.chipGoalkeepers);
        Chip chipDefenders = findViewById(R.id.chipDefenders);
        Chip chipMidfielders = findViewById(R.id.chipMidfielders);
        Chip chipAttackers = findViewById(R.id.chipAttackers);

        chipAll.setOnClickListener(v -> squadAdapter.filterByPosition("All"));
        chipGoalkeepers.setOnClickListener(v -> squadAdapter.filterByPosition("GK"));
        chipDefenders.setOnClickListener(v -> squadAdapter.filterByPosition("DEF"));
        chipMidfielders.setOnClickListener(v -> squadAdapter.filterByPosition("MID"));
        chipAttackers.setOnClickListener(v -> squadAdapter.filterByPosition("ATT"));

        int teamId = getIntent().getIntExtra("teamId", -1);
        String teamNameStr = getIntent().getStringExtra("teamName");
        String teamCrestUrl = getIntent().getStringExtra("teamCrest");

        if (teamNameStr != null) {
            toolbar.setTitle(teamNameStr);
            teamName.setText(teamNameStr);
        }

        if (teamCrestUrl != null) {
            Picasso.get().load(teamCrestUrl).placeholder(R.drawable.image5).error(R.drawable.image5).into(teamCrest);
        }

        if (teamId != -1) {
            loadTeamDetails(teamId, teamFounded, teamColors, teamVenue);
        }

        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, this, adViewContainer);
        bannerManager.loadBanner();
    }

    private void loadTeamDetails(int teamId, TextView teamFounded, TextView teamColors, TextView teamVenue) {
        progressBar.setVisibility(android.view.View.VISIBLE);
        String url = "https://api.football-data.org/v4/teams/" + teamId;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        // Team details
                        String founded = response.optString("founded", "");
                        if (!founded.isEmpty()) {
                            teamFounded.setText("Founded: " + founded);
                        }

                        String colors = response.optString("clubColors", "");
                        if (!colors.isEmpty()) {
                            teamColors.setText("Colors: " + colors);
                        }

                        String venue = response.optString("venue", "");
                        if (!venue.isEmpty()) {
                            teamVenue.setText("Venue: " + venue);
                        }

                        String website = response.optString("website", "");
                        String address = response.optString("address", "");

                        // Squad
                        JSONArray squadArray = response.optJSONArray("squad");
                        if (squadArray != null) {
                            squadPlayers.clear();
                            for (int i = 0; i < squadArray.length(); i++) {
                                JSONObject playerObj = squadArray.getJSONObject(i);
                                SquadPlayer player = new SquadPlayer(
                                        playerObj.getInt("id"),
                                        playerObj.getString("name"),
                                        playerObj.optString("position", ""),
                                        playerObj.optInt("shirtNumber", 0),
                                        playerObj.optString("nationality", ""),
                                        playerObj.optString("dateOfBirth", "")
                                );
                                squadPlayers.add(player);
                            }
                            squadAdapter = new SquadAdapter(squadPlayers);
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setAdapter(squadAdapter);
                            squadAdapter.notifyDataSetChanged();
                        }

                        progressBar.setVisibility(android.view.View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        progressBar.setVisibility(android.view.View.GONE);
                    }
                }, error -> {
                    progressBar.setVisibility(android.view.View.GONE);
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            interstitialManager.showInterstitial(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appOpenManager.showAdIfAvailable(this);
    }
}
