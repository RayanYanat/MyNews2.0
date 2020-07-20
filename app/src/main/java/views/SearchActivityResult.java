package views;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;

import java.util.ArrayList;
import java.util.List;


import controllers.NewsAdapter;
import controllers.WebViewActivity;
import models.NyTimesApiResults;
import models.TopStoriesArticles;
import utils.ItemClickSupport;
import utils.SearchCall;

public class SearchActivityResult extends AppCompatActivity implements SearchCall.Callbacks {

    private RecyclerView recyclerView;
    private List<TopStoriesArticles> results;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        recyclerView = findViewById(R.id.MyRecyclerView4);
        executeHttpRequestWithRetrofit();
        setUpRecyclerView();
        onClickRecyclerView();
    }

    @Override
    public void onResponse(NyTimesApiResults topstories) {
        adapter.setResults(topstories.getResults());
    }

    @Override
    public void onFailure() {
        Log.d("TAG", "Response = searchFailed ");
    }

    private void executeHttpRequestWithRetrofit() {
        Log.d("TAG", "Response = responseSearch");
        String query = getIntent().getStringExtra(SearchActivity.QUERY);
        String beginDate = getIntent().getStringExtra(SearchActivity.BEGIN_DATE);
        String endDate = getIntent().getStringExtra(SearchActivity.END_DATE);
        List<String> filterQuery = getIntent().getStringArrayListExtra(SearchActivity.FILTER_QUERY);
        SearchCall.getSearchResults(this,query,filterQuery,beginDate,endDate);
    }
    private void setUpRecyclerView() {
        Log.d("TAG", "Response = recyclerSearch ");
        results = new ArrayList<>();
        adapter = new NewsAdapter(results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void onClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.news_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                        TopStoriesArticles result  = adapter.getUrl(position);
                        openWebViewActivity(result.getUrl());

                    }
                });
    }

    private void openWebViewActivity(String url){
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
}
