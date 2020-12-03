package com.example.pas_genap_2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListTeamFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapterFavourite adapter;
    List<ModelFavourite> DataArrayList; //kit add kan ke adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Favourite Team List");
        setContentView(R.layout.activity_list_team);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        Realm.init(ListTeamFavourite.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMovie();
        if (DataArrayList.size() == 0){
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {

                    ModelFavourite team = DataArrayList.get(position);
                    Intent intent = new Intent(getApplicationContext(), DetailFavourite.class);
                    intent.putExtra("id",team.id);
                    intent.putExtra("team",team.strTeam);
                    intent.putExtra("alternate",team.strAlternate);
                    intent.putExtra("league",team.strLeague);
                    intent.putExtra("stadium",team.strStadium);
                    intent.putExtra("badge",team.strTeamBadge);
                    intent.putExtra("description",team.strDescriptionEN);
                    intent.putExtra("location",team.strStadiumLocation);
                    startActivity(intent);
                    Toast.makeText(ListTeamFavourite.this, ""+position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListTeamFavourite.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}