package controllers;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynews.R;

import java.util.List;


import models.TopStoriesArticles;
import views.TabOne;


public class  NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ImageViewHolder> {

    private List<TopStoriesArticles> mData;



    public NewsAdapter(List<TopStoriesArticles> data) {

        mData = data;

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {


        TopStoriesArticles topSortiesItem =mData.get(position);
        holder.newsTittle.setText(topSortiesItem.getTitle());
        holder.newsDate.setText(topSortiesItem.getPublishedDate());
        holder.newsSection.setText(topSortiesItem.getSubsection());
        Glide.with(holder.itemView.getContext()).load(topSortiesItem.getMultimedia()).into(holder.newsImage);



    }

    @Override
    public int getItemCount() {
        int itemCount=0;
        if (mData != null) itemCount = mData.size();
        return itemCount;
    }

    public void setResults(List<TopStoriesArticles> topStoriesResults) {
        this.mData=topStoriesResults;
        notifyDataSetChanged();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTittle;
        TextView newsDate;
        TextView newsSection;

        ImageViewHolder(View itemView) {
            super(itemView);

            newsImage = itemView.findViewById(R.id.news_item_image);
            newsTittle = itemView.findViewById(R.id.news_item_title);
            newsDate = itemView.findViewById(R.id.news_item_date);
            newsSection = itemView.findViewById(R.id.news_item_section);

        }

    }

    public TopStoriesArticles getUrl(int position){
        List<TopStoriesArticles> result = mData;
        return result.get(position);
    }

}
