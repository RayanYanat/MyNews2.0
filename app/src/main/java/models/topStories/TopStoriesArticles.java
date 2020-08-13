package models.topStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopStoriesArticles {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> urlImageMedia;

    public List<Multimedia> getMultimedia() {
        return urlImageMedia;
    }

    public TopStoriesArticles(String section, String subsection, String title, String url, String publishedDate,List<Multimedia> urlImageMedia){
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this.url = url;
        this.publishedDate = publishedDate;
        this.urlImageMedia = urlImageMedia;
    }

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
         return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

}