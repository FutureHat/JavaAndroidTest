package com.stratpoint.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stratpoint.android.retro.ApiObject;

import java.util.Collections;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    private final Context context;
    private NewViewHolder holder;
    private List<ApiObject> apiObjectList;
    private String playerholder = "";
    private String scoreholder ="";
    public Button delete;
    private boolean holders = true;
    
    public NewAdapter(Context context, List<ApiObject> apiObjects){
        this.context = context;
        this.apiObjectList =  apiObjects;
    }
    @NonNull
    @Override
    public NewAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofnames, parent, false);
        return new NewAdapter.NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.NewViewHolder holder, final int position) {

        Collections.sort(apiObjectList, ApiObject.ScoreOrder);
        final ApiObject apiObject = apiObjectList.get(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("Players", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        playerholder+=  apiObject.getPlayerName()+",";
        scoreholder+= apiObject.getScore()+",";
        editor.putString("players", playerholder);
        editor.putString("scores", scoreholder);
        editor.commit();
        Log.d("please", sharedPreferences.getString("players", ""));
        holder.names.setText(apiObject.getPlayerName()+"\n"+apiObject.getScore());

    }


    @Override
    public int getItemCount() {
        return apiObjectList.size();
    }
    public class NewViewHolder extends RecyclerView.ViewHolder {

        public TextView names;

        public NewViewHolder(View itemView) {
            super(itemView);
            names = (TextView) itemView.findViewById(R.id.name);
        }

    }
}
