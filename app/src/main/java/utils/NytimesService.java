package utils;

        import androidx.annotation.Nullable;

        import java.util.List;

        import io.reactivex.Observable;
        import models.NyTimesApiResults;
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
    Call<NyTimesApiResults> getMostPopularNews(@Path("section") String section, @Path("time-period") String timePeriod);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build() ;

}
