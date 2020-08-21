package models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {
    @SerializedName("main")
    @Expose
    private String main;

    public Headline(String main) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }
}
