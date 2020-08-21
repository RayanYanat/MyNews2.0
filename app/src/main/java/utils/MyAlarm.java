package utils;


import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.mynews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;


import controllers.MainActivity;
import models.NotificationPreferences;
import models.search.NyTimesSearchResults;


import static android.content.Context.MODE_PRIVATE;
import static views.NotificationActivity.NOTIFICATIONS_STATE;
import static views.NotificationActivity.PREFS;



public class MyAlarm extends BroadcastReceiver implements SearchCall.Callbacks {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("alarm", "triggered" );

        this.mContext = context;
        this.retrieveSharedPreferences();


    }

    private void retrieveSharedPreferences(){
        SharedPreferences preferences = mContext.getSharedPreferences(PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<NotificationPreferences>(){}.getType();
        String jsonState = preferences.getString(NOTIFICATIONS_STATE,"");
        NotificationPreferences notificationsPreferences = gson.fromJson(jsonState, type);
        if (notificationsPreferences != null){
            String queryTerm = notificationsPreferences.getQueryTerm();
            List<String> categoryList = notificationsPreferences.getCategoryList();
            this.executeHttpRequestWithRetrofit(queryTerm, categoryList);
        }

    }

    private void executeHttpRequestWithRetrofit(String queryTerm, List<String> categoryList){
       SearchCall.getSearchResults(this,queryTerm, categoryList, DateOfTheDay(),null);
    }


    private String DateOfTheDay(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(Calendar.getInstance().getTime());
    }

    @Override
    public void onResponse(NyTimesSearchResults topstories) {
        int intResults = topstories.getResponse().getDocs().size();
        NotificationManager notificationManager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID",
                    "NOTIFICATION_CHANNEL",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }


        Intent notificationIntent = new Intent(mContext, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                mContext,"CHANNEL_ID")
                .setContentTitle("MyNews")
                .setContentText("Articles that may interest you!" + intResults + " articles find" )
                .setSmallIcon(R.drawable.baseline_menu_black_24)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (notificationManager != null) {
            notificationManager.notify(1, mNotifyBuilder.build());
        }

        Log.e("notifiedArticles",topstories.getResponse().getDocs().get(0).getWebUrl());

    }

    @Override
    public void onFailure() {

    }
}
