package views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import utils.TopStoriesCall;

public class TabOne extends Fragment implements TopStoriesCall.Callbacks {

    private RecyclerView recyclerView;
    private List<TopStoriesArticles> results;
    private NewsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = view.findViewById(R.id.MyRecyclerView);
        executeHttpRequestWithRetrofit();
        setUpRecyclerView();
        onClickRecyclerView();
        return view;
    }

    private void executeHttpRequestWithRetrofit() {
        Log.d("TAG", "Response = response22");
        TopStoriesCall.fetchTopStoriesArticle(this, "home");
    }

    @Override
    public void onResponse(NyTimesApiResults topstories) {
        Log.d("TAG", "Response = response");
        adapter.setResults(topstories.getResults());

    }

    @Override
    public void onFailure() {
        Log.d("TAG", "Response = failed");

    }

    private void setUpRecyclerView() {
        Log.d("TAG", "Response = recycler ");
        results = new ArrayList<>();
        adapter = new NewsAdapter(results);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
}

