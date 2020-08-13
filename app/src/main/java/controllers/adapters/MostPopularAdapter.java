package controllers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynews.R;

import java.util.List;

import models.mostPopular.MostPopulaArticles;


public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.ImageViewHolder> {
    private List<MostPopulaArticles> mData;



    public MostPopularAdapter(List<MostPopulaArticles> data) {

        mData = data;

    }

    @NonNull
    @Override
    public MostPopularAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MostPopularAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        MostPopulaArticles topSortiesItem =mData.get(position);
        holder.newsTittle.setText(topSortiesItem.getTitle());
        holder.newsDate.setText(topSortiesItem.getPublishedDate());
        holder.newsSection.setText(topSortiesItem.getSubsection());
        if (topSortiesItem.getUrlImageMedia()!= null && topSortiesItem.getUrlImageMedia().size() > 0)
            Glide.with(holder.itemView.getContext()).load(topSortiesItem.getUrlImageMedia().get(0).getMediaMetadata().get(0).getUrl()).into(holder.newsImage);

    }


    @Override
    public int getItemCount() {
        int itemCount=0;
        if (mData != null) itemCount = mData.size();
        return itemCount;
    }

    public void setResults(List<MostPopulaArticles> topStoriesResults) {
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

    public MostPopulaArticles getUrl(int position){
        List<MostPopulaArticles> result = mData;
        return result.get(position);
    }
}
