package models;

import java.util.List;

public class NotificationPreferences {


    private String queryTerm;
    private List<String> categoryList;
    private boolean isEnabled;

    public NotificationPreferences(String queryTerm, List<String> categoryList, boolean isEnabled) {
        this.queryTerm = queryTerm;
        this.categoryList = categoryList;
        this.isEnabled = isEnabled;
    }

    public String getQueryTerm() {
        return queryTerm;
    }

    public void setQueryTerm(String queryTerm) {
        this.queryTerm = queryTerm;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
