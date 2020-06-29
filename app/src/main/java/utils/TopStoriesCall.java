package utils;

import android.util.Log;

import java.lang.ref.WeakReference;

import models.NyTimesApiResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesCall {

    public interface Callbacks {
        void onResponse(NyTimesApiResults topstories);

        void onFailure();
    }

    public static void fetchTopStoriesArticle(Callbacks callbacks, String section) {

        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

        NytimesService nytimesService = NytimesService.retrofit.create(NytimesService.class);

        Call<NyTimesApiResults> call = nytimesService.getTopStoriesNews(section);

        call.enqueue(new Callback<NyTimesApiResults>() {

            @Override
            public void onResponse(Call<NyTimesApiResults> call, Response<NyTimesApiResults> response) {
                Log.d("TAG","Response = ");
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }



            @Override
            public void onFailure(Call<NyTimesApiResults> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}

