package utils;

import java.lang.ref.WeakReference;
import java.util.List;


import models.search.NyTimesSearchResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCall {

    public interface Callbacks {

        void onResponse(NyTimesSearchResults topstories);

        void onFailure();
    }

    public static void getSearchResults(SearchCall.Callbacks callbacks, String search, List<String> filterQuery,String beginDate,String endDate) {

        final WeakReference<SearchCall.Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

        NytimesService nytimesService = NytimesService.retrofit.create(NytimesService.class);

        Call<NyTimesSearchResults> call =  nytimesService.getSearchResult( search,filterQuery,beginDate,endDate);

        call.enqueue(new Callback<NyTimesSearchResults>() {

            @Override
            public void onResponse(Call<NyTimesSearchResults> call, Response<NyTimesSearchResults> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }



            @Override
            public void onFailure(Call<NyTimesSearchResults> call, Throwable t) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
