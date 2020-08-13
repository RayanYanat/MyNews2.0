package models.mostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NyTimesMostPopularResults {
    @SerializedName("results")
    @Expose
    private List<MostPopulaArticles> results;

    public List<MostPopulaArticles>getResults(){
        return results;
    }

}
