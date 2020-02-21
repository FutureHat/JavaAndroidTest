package com.stratpoint.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.stratpoint.android.retro.ApiObject;
import com.stratpoint.android.retro.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiIntegration extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final String TAG = ApiIntegration.class.getSimpleName();
    List<ApiObject> postList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiUtil.getServiceClass().getAllPost().enqueue(new Callback<List<ApiObject>>() {
            @Override
            public void onResponse(Call<List<ApiObject>> call, Response<List<ApiObject>> response) {
                if(response.isSuccessful()){
                    postList = response.body();
                    Log.d(TAG, "Returned count " + postList.size());
                    NewAdapter adapter = new NewAdapter(getApplicationContext(), postList);
                    new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ApiObject>> call, Throwable t) {
                //showErrorMessage();
               // Log.d(TAG, "error loading from API");
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Players", Context.MODE_PRIVATE);
                String playerNames = sharedPreferences.getString("players", "");
                String playerScores = sharedPreferences.getString("scores", "");

                String[] players = playerNames.split(",");
                String[] scores = playerScores.split(",");
                for(int x =0 ; x < players.length; x++){
                   postList.add(new ApiObject(players[x],Integer.parseInt(scores[x])));
                }
                NewAdapter adapter = new NewAdapter(getApplicationContext(), postList);
                new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
                recyclerView.setAdapter(adapter);
            }

        });


    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            postList.remove(viewHolder.getAdapterPosition());
            NewAdapter adapter = new NewAdapter(getApplicationContext(), postList);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
    };



}
