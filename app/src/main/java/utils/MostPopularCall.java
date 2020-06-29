package utils;

import android.util.Log;

import java.lang.ref.WeakReference;

import models.NyTimesApiResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularCall {

    public interface Callbacks {
        void onResponse(NyTimesApiResults topstories);

        void onFailure();
    }

    public static void fetchMostPopularArticle(MostPopularCall.Callbacks callbacks, String section,String timePeriod) {

        final WeakReference<MostPopularCall.Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

        NytimesService nytimesService = NytimesService.retrofit.create(NytimesService.class);

        Call<NyTimesApiResults> call = nytimesService.getMostPopularNews(section,timePeriod);

        call.enqueue(new Callback<NyTimesApiResults>() {

            @Override
            public void onResponse(Call<NyTimesApiResults> call, Response<NyTimesApiResults> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }



            @Override
            public void onFailure(Call<NyTimesApiResults> call, Throwable t) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
