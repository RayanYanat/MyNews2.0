package models.mostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopulaArticles {
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
    @SerializedName("media")
    @Expose
    private List<Medium> urlImageMedia;

    public MostPopulaArticles (String section, String subsection, String title, String url, String publishedDate, List<Medium> urlImageMedia){
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

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<Medium> getUrlImageMedia() {
        return urlImageMedia;
    }

    public void setUrlImageMedia(List<Medium> urlImageMedia) {
        this.urlImageMedia = urlImageMedia;
    }
}
