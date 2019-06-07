package hkcc.ccn2279.gp.canemovie.expandablerecyclerview;

import android.view.View;
import android.widget.TextView;

import hkcc.ccn2279.gp.canemovie.R;

public class RecyclerviewDetailViewHolder extends ChildViewHolder {

    private TextView mMoviesTextView;

    public RecyclerviewDetailViewHolder(View itemView) {
        super(itemView);
        mMoviesTextView = (TextView) itemView.findViewById(R.id.tv_movie_info);
    }

    public void bind(RecyclerviewDetail movies) {
        mMoviesTextView.setText(movies.getName());
    }
}
