package hkcc.ccn2279.gp.canemovie.expandablerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import hkcc.ccn2279.gp.canemovie.R;

public class RecyclerviewCategoryAdapter extends ExpandableRecyclerAdapter<RecyclerviewCategoryViewHolder, RecyclerviewDetailViewHolder> {

    private LayoutInflater mInflator;

    public RecyclerviewCategoryAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public RecyclerviewCategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View movieCategoryView = mInflator.inflate(R.layout.movie_info_category_view, parentViewGroup, false);
        return new RecyclerviewCategoryViewHolder(movieCategoryView);
    }

    @Override
    public RecyclerviewDetailViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View moviesView = mInflator.inflate(R.layout.movie_info_view, childViewGroup, false);
        return new RecyclerviewDetailViewHolder(moviesView);
    }

    @Override
    public void onBindParentViewHolder(RecyclerviewCategoryViewHolder movieCategoryViewHolder, int position, ParentListItem parentListItem) {
        RecyclerviewCategory movieCategory = (RecyclerviewCategory) parentListItem;
        movieCategoryViewHolder.bind(movieCategory);
    }

    @Override
    public void onBindChildViewHolder(RecyclerviewDetailViewHolder moviesViewHolder, int position, Object childListItem) {
        RecyclerviewDetail movies = (RecyclerviewDetail) childListItem;
        moviesViewHolder.bind(movies);
    }
}
