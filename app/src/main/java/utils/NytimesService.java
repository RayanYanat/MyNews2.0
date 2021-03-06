package utils;

        import androidx.annotation.Nullable;

        import java.util.List;

        import models.topStories.NyTimesApiResults;
        import models.mostPopular.NyTimesMostPopularResults;
        import models.search.NyTimesSearchResults;
        import retrofit2.Call;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;
        import retrofit2.http.GET;
        import retrofit2.http.Path;
        import retrofit2.http.Query;

public interface NytimesService {

    String API_KEY = "api-key=JBjJiEna1ALjDhOOSlBMTj7ej1D55z1O";

    @GET("topstories/v2/{section}.json?" + API_KEY)
    Call<NyTimesApiResults> getTopStoriesNews(@Path("section") String section);

    @GET("mostpopular/v2/mostviewed/{section}/{time-period}.json?" + API_KEY)
    Call<NyTimesMostPopularResults> getMostPopularNews(@Path("section") String section, @Path("time-period") String timePeriod);

    @GET("search/v2/articlesearch.json?sort=newest&" + API_KEY)
    Call<NyTimesSearchResults> getSearchResult(@Query("q") String toSearch, @Nullable @Query("fq") List<String> filterQuery, @Nullable @Query("begin_date") String beginDate, @Nullable @Query("end_date") String endDate);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build() ;

}
