package utils;

import java.lang.ref.WeakReference;
import java.util.List;


import models.NyTimesApiResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCall {

    public interface Callbacks {

        void onResponse(NyTimesApiResults topstories);

        void onFailure();
    }

    public static void getSearchResults(SearchCall.Callbacks callbacks, String toSearch, List<String> filterQuery,String beginDate,String endDate) {

        final WeakReference<SearchCall.Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

        NytimesService nytimesService = NytimesService.retrofit.create(NytimesService.class);

        Call<NyTimesApiResults> call =  nytimesService.getSearchResult( toSearch,  filterQuery,beginDate,endDate);

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
