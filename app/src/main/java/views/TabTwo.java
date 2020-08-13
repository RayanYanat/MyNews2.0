package views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;

import java.util.ArrayList;
import java.util.List;

import controllers.adapters.MostPopularAdapter;
import controllers.WebViewActivity;
import models.mostPopular.MostPopulaArticles;
import models.mostPopular.NyTimesMostPopularResults;
import utils.ItemClickSupport;
import utils.MostPopularCall;

public class TabTwo extends Fragment implements MostPopularCall.Callbacks {

    private RecyclerView recyclerView;
    private List<MostPopulaArticles> results;
    private MostPopularAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = view.findViewById(R.id.MyRecyclerView2);
        executeHttpRequestWithRetrofit();
        setUpRecyclerView();
        onClickRecyclerView();
        return view;
    }

    private void executeHttpRequestWithRetrofit() {
        Log.d("TAG", "Response = responseMostPopular");
        MostPopularCall.fetchMostPopularArticle(this, "all-sections","7");
    }

    @Override
    public void onResponse(NyTimesMostPopularResults topstories) {
        Log.d("TAG", "Response = responseMostPopular");
        adapter.setResults(topstories.getResults());

    }

    @Override
    public void onFailure() {

    }

    private void setUpRecyclerView() {
        Log.d("TAG", "Response = recyclerMostPopular ");
        results = new ArrayList<>();
        adapter = new MostPopularAdapter(results);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void onClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.news_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                        MostPopulaArticles result  = adapter.getUrl(position);
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

