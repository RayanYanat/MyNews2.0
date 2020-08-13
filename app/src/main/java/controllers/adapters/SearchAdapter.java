package controllers.adapters;

import android.util.Log;
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

import models.search.Doc;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ImageViewHolder> {
    private List<Doc> mData;



    public SearchAdapter(List<Doc> data) {

        mData = data;

    }

    @NonNull
    @Override
    public SearchAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new SearchAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Doc topSortiesItem =mData.get(position);
        holder.newsTittle.setText(topSortiesItem.getHeadline().getMain());
        holder.newsDate.setText(topSortiesItem.getPubDate());
        holder.newsSection.setText(topSortiesItem.getSubsectionName());
        if (topSortiesItem.getMultimedia()!= null && topSortiesItem.getMultimedia().size() > 0)
            Glide.with(holder.itemView.getContext()).load("https://static01.nyt.com/" + topSortiesItem.getMultimedia().get(0).getUrl()).into(holder.newsImage);


    }


    @Override
    public int getItemCount() {
        int itemCount=0;
        if (mData != null) itemCount = mData.size();
        return itemCount;
    }

    public void setResults(List<Doc> topStoriesResults) {
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

    public Doc getUrl(int position){
        List<Doc> result = mData;
        return result.get(position);
    }
}
