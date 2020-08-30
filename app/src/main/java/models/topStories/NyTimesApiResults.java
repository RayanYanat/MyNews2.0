package models.topStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;




public class NyTimesApiResults {
    @SerializedName("results")
    @Expose
    private List<TopStoriesArticles>results;

    public List<TopStoriesArticles>getResults(){
        return results;
    }
}

