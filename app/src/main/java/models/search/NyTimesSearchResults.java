package models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NyTimesSearchResults {
    @SerializedName("response")
    @Expose
    private Response response;

    public NyTimesSearchResults(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
