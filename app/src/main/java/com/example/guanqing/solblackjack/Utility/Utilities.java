package com.example.guanqing.solblackjack.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.guanqing.solblackjack.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Guanqing on 2015/10/5.
 */
public class Utilities {
    private static final String LOG_TAG = "HGQ_DEBUG";

    //create a hashmap to relate a card to its corresponding drawable
    public static HashMap<Card, Integer> cardResHashMap;
    public static String GAME_PREFS = "GAME_PREFS";
    //key strings for sharedPreferences
    public static String HIGH_SCORE_KEY = "HIGH_SCORE_KEY";
    public static String TOTAL_SCORE_KEY = "TOTAL_SCORE_KEY";
    public static String TOTAL_GAME_NUMBER_KEY = "TOTAL_GAME_NUMBER_KEY";

    public static String SETTING_THEME_KEY = "SETTING_THEME_KEY";
    public static int THEME_GREEN = 0;
    public static int THEME_BLUE = 1;
    public static int THEME_WOOD = 2;

    public Utilities(){}

    /**
     * get the corresponding drawable id of a card
     * @param card
     * @return drawableResId
     */
    public static int getCardDrawableResId(Card card){
        if (cardResHashMap==null) {
            createHashMap();
        }
        for (Card c: cardResHashMap.keySet()){
            if (c.equals(card)){
                return cardResHashMap.get(c);
            }
        }
        return -1;
    }

    //--------------------------------------------------------------------------
    //------------------Set High Scores in the Leader Board---------------------
    //--------------------------------------------------------------------------
    public static boolean checkAndSetHighScore(Context context, int newScore){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        String newScoreInfo = getNewScoreString(newScore);
        Log.e(LOG_TAG, "new score info: "+newScoreInfo);
        String highscores = pref.getString(HIGH_SCORE_KEY, "");
        Log.e(LOG_TAG, "high scores: "+highscores);
        if(highscores.equals("")){
            Log.e(LOG_TAG, "is empty");
            editor.putString(HIGH_SCORE_KEY, newScoreInfo).apply();
            return true;
        }

        Log.e(LOG_TAG, "not empty");
        String[] scores = highscores.split("/");
        Log.e(LOG_TAG, "scores last one: "+scores[scores.length-1]);
        if(scores.length<6 || getScore(scores[scores.length-1])<newScore){
            String newHighScores = getNewHighScores(scores, newScoreInfo);
            editor.putString(HIGH_SCORE_KEY, newHighScores).apply();
            return true;
        }else{
            return false;
        }
    }

    private static String getNewScoreString(int newScore){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String date = dateFormat.format(new Date());
        if (newScore==0){
            return date + " - 00";
        }else if (newScore<10){
            return date + " - 0" + newScore;
        }else{
            return date + " - " + newScore;
        }
    }

    private static int getScore(String s){
        Log.e(LOG_TAG, "get score string: "+s);
        Log.e(LOG_TAG, "s.length: " + s.length());
        return Integer.parseInt(s.substring(s.length() - 2));
    }

    private static String getNewHighScores(String[] scores, String newScoreInfo){
        Score score = new Score(getScore(newScoreInfo), newScoreInfo);
        ArrayList<Score> list = new ArrayList<>();
        list.add(score);
        int len = scores.length;
        if(len==6){
            //keep the max number of high scores at 6
            len=5;
        }
        for (int i=0;i<len;i++){
            list.add(new Score(getScore(scores[i]), scores[i]));
        }
        Collections.sort(list);

        String newHighScore = "";
        for(Score sc:list){
            newHighScore += sc.info + "/";
        }
        return newHighScore.substring(0, newHighScore.length() - 1);
    }

    public static String getHighScores(Context context){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        String highscores = pref.getString(HIGH_SCORE_KEY, "");
        if (highscores.isEmpty()){
            return "no record";
        }
        highscores = highscores.replaceAll("/", "\n");
        return highscores;
    }

    public static boolean isNewHighScore(Context context, int newScore){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        String highscores = pref.getString(HIGH_SCORE_KEY, "");
        if(highscores.equals("")){
            return true;
        }

        String[] scores = highscores.split("/");
        if (scores.length<6){
            return true;
        }else if(getScore(scores[scores.length-1])<newScore){
            return true;
        }
        return false;
    }

    //-------- Set the total number of games played -------------------------
    //-------- and the average score in the Leader Board --------------------
    public static void setGameNumAndAvgScore(Context context, int newScore){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();

        int totalScore = pref.getInt(TOTAL_SCORE_KEY, 0);
        totalScore+=newScore;
        editor.putInt(TOTAL_SCORE_KEY, totalScore).apply();

        int gameNum = pref.getInt(TOTAL_GAME_NUMBER_KEY, 0);
        gameNum++;
        editor.putInt(TOTAL_GAME_NUMBER_KEY, gameNum).apply();
    }

    public static String getAvgScore(Context context){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);

        int totalScore = pref.getInt(TOTAL_SCORE_KEY, 0);
        int gameNum = pref.getInt(TOTAL_GAME_NUMBER_KEY, 0);
        if (gameNum==0){return "0.00";}
        double avg = totalScore / (double) gameNum;
        return String.format("%.2f", avg);
    }

    public static int getTotalGameNum(Context context){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        return pref.getInt(TOTAL_GAME_NUMBER_KEY, 0);
    }

    public static void resetStatistics(Context context){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        pref.edit().clear().apply();
    }




    //---------------------------------------------------------------------
    //---------------- set theme in setting -------------------------------
    //---------------------------------------------------------------------
    public static void setTheme(Context context, int theme){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        pref.edit().putInt(SETTING_THEME_KEY, theme).apply();
    }

    public static int getTheme(Context context){
        SharedPreferences pref = context.getSharedPreferences(GAME_PREFS, 0);
        return pref.getInt(SETTING_THEME_KEY, 0);
    }





    private static void createHashMap(){
        cardResHashMap = new HashMap<>();
        cardResHashMap.put(new Card(1,"C"), R.drawable.c1c);
        cardResHashMap.put(new Card(1,"D"), R.drawable.c1d);
        cardResHashMap.put(new Card(1,"H"), R.drawable.c1h);
        cardResHashMap.put(new Card(1,"S"), R.drawable.c1s);

        cardResHashMap.put(new Card(2,"C"), R.drawable.c2c);
        cardResHashMap.put(new Card(2,"D"), R.drawable.c2d);
        cardResHashMap.put(new Card(2,"H"), R.drawable.c2h);
        cardResHashMap.put(new Card(2,"S"), R.drawable.c2s);

        cardResHashMap.put(new Card(3,"C"), R.drawable.c3c);
        cardResHashMap.put(new Card(3,"D"), R.drawable.c3d);
        cardResHashMap.put(new Card(3,"H"), R.drawable.c3h);
        cardResHashMap.put(new Card(3,"S"), R.drawable.c3s);

        cardResHashMap.put(new Card(4,"C"), R.drawable.c4c);
        cardResHashMap.put(new Card(4,"D"), R.drawable.c4d);
        cardResHashMap.put(new Card(4,"H"), R.drawable.c4h);
        cardResHashMap.put(new Card(4,"S"), R.drawable.c4s);

        cardResHashMap.put(new Card(5,"C"), R.drawable.c5c);
        cardResHashMap.put(new Card(5,"D"), R.drawable.c5d);
        cardResHashMap.put(new Card(5,"H"), R.drawable.c5h);
        cardResHashMap.put(new Card(5,"S"), R.drawable.c5s);

        cardResHashMap.put(new Card(6,"C"), R.drawable.c6c);
        cardResHashMap.put(new Card(6,"D"), R.drawable.c6d);
        cardResHashMap.put(new Card(6,"H"), R.drawable.c6h);
        cardResHashMap.put(new Card(6,"S"), R.drawable.c6s);

        cardResHashMap.put(new Card(7,"C"), R.drawable.c7c);
        cardResHashMap.put(new Card(7,"D"), R.drawable.c7d);
        cardResHashMap.put(new Card(7,"H"), R.drawable.c7h);
        cardResHashMap.put(new Card(7,"S"), R.drawable.c7s);

        cardResHashMap.put(new Card(8,"C"), R.drawable.c8c);
        cardResHashMap.put(new Card(8,"D"), R.drawable.c8d);
        cardResHashMap.put(new Card(8,"H"), R.drawable.c8h);
        cardResHashMap.put(new Card(8,"S"), R.drawable.c8s);

        cardResHashMap.put(new Card(9,"C"), R.drawable.c9c);
        cardResHashMap.put(new Card(9,"D"), R.drawable.c9d);
        cardResHashMap.put(new Card(9,"H"), R.drawable.c9h);
        cardResHashMap.put(new Card(9,"S"), R.drawable.c9s);

        cardResHashMap.put(new Card(10,"C"), R.drawable.c10c);
        cardResHashMap.put(new Card(10,"D"), R.drawable.c10d);
        cardResHashMap.put(new Card(10,"H"), R.drawable.c10h);
        cardResHashMap.put(new Card(10,"S"), R.drawable.c10s);

        cardResHashMap.put(new Card(11,"C"), R.drawable.c11c);
        cardResHashMap.put(new Card(11,"D"), R.drawable.c11d);
        cardResHashMap.put(new Card(11,"H"), R.drawable.c11h);
        cardResHashMap.put(new Card(11,"S"), R.drawable.c11s);

        cardResHashMap.put(new Card(12,"C"), R.drawable.c12c);
        cardResHashMap.put(new Card(12,"D"), R.drawable.c12d);
        cardResHashMap.put(new Card(12,"H"), R.drawable.c12h);
        cardResHashMap.put(new Card(12,"S"), R.drawable.c12s);

        cardResHashMap.put(new Card(13,"C"), R.drawable.c13c);
        cardResHashMap.put(new Card(13,"D"), R.drawable.c13d);
        cardResHashMap.put(new Card(13,"H"), R.drawable.c13h);
        cardResHashMap.put(new Card(13,"S"), R.drawable.c13s);
    }
}
