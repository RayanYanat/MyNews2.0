package utils;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import models.mostPopular.NyTimesMostPopularResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularCall {

    public interface Callbacks {
        void onResponse(NyTimesMostPopularResults topstories);

        void onFailure();
    }

    public static void fetchMostPopularArticle(MostPopularCall.Callbacks callbacks, String section,String timePeriod) {

        final WeakReference<MostPopularCall.Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

        NytimesService nytimesService = NytimesService.retrofit.create(NytimesService.class);

        Call<NyTimesMostPopularResults> call = nytimesService.getMostPopularNews(section,timePeriod);

        call.enqueue(new Callback<NyTimesMostPopularResults>() {

            @Override
            public void onResponse(@NonNull Call<NyTimesMostPopularResults> call, @NonNull Response<NyTimesMostPopularResults> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }



            @Override
            public void onFailure(@NonNull Call<NyTimesMostPopularResults> call, @NonNull Throwable t) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
