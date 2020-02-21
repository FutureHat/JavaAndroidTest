package com.stratpoint.android.retro;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class ApiObject {
    @SerializedName("Player Name")
    private String PlayerName;

    @SerializedName("Score")
    private int Score;

    public ApiObject(String playerName, int score) {
        PlayerName = playerName;
        Score = score;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public static Comparator<ApiObject> ScoreOrder= new Comparator<ApiObject>() {

        public int compare(ApiObject student, ApiObject t1) {
            int score1 = student.getScore();
            int score2 = t1.getScore();
            return score2-score1;
        }
    };
}
