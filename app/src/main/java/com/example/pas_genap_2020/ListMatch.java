package com.example.pas_genap_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMatch extends AppCompatActivity {

    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    AdapterMatch adapter;
    ArrayList<ModelMatch> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Match List");
        setContentView(R.layout.activity_list_team);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(ListMatch.this);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        addDataOnline();
    }

    void addDataOnline(){
        //Loading Screen
        dialog.setMessage("Processing Data");
        dialog.show();
        //Background Process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id=133613")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Do Anything With Response
                        Log.d("hasiljson","onResponse: " + response.toString());
                        DataArrayList = new ArrayList<>();
                        ModelMatch modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new ModelMatch();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setHome_name(jsonObject.getString("strHomeTeam"));
                                modelku.setAway_name(jsonObject.getString("strAwayTeam"));
                                modelku.setLeague(jsonObject.getString("strLeague"));
                                modelku.setVenue(jsonObject.getString("strVenue"));
                                modelku.setHome_score(jsonObject.getInt("intHomeScore"));
                                modelku.setAway_score(jsonObject.getInt("intAwayScore"));
                                DataArrayList.add(modelku);
                            }
                            //Handle Click
                            adapter = new AdapterMatch(DataArrayList, new AdapterMatch.Callback() {
                                @Override
                                public void onClick(int position) {
                                    ModelMatch team = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailTeam.class);
                                    intent.putExtra("id",team.id);
                                    intent.putExtra("home",team.strHomeTeam);
                                    intent.putExtra("away",team.strAwayTeam);
                                    intent.putExtra("league",team.strLeague);
                                    intent.putExtra("venue",team.strVenue);
                                    intent.putExtra("homescore",team.intHomeScore);
                                    intent.putExtra("awayscore",team.intAwayScore);
                                    startActivity(intent);
                                    Toast.makeText(ListMatch.this, +position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMatch.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        //Handle Error
                        Log.d("errorku","onError errorCode : " + anError.getErrorCode());
                        Log.d("errorku","onError errorBody : " + anError.getErrorBody());
                        Log.d("errorku","onError errorDetail : " + anError.getErrorDetail());
                    }
                });
    }
}
