package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NyTimesApiResults {
    @SerializedName("data")
    @Expose
    private List<TopStoriesArticles>results;

    public List<TopStoriesArticles>getResults(){
        return results;
    }
}

