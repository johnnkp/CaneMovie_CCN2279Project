package hkcc.ccn2279.gp.canemovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.ExpandableRecyclerAdapter;
import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewCategory;
import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewCategoryAdapter;
import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewDetail;

public class MovieInfoFragment extends Fragment {
    private RecyclerviewCategoryAdapter mAdapter;
    private RecyclerView recyclerView;

    public MovieInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_info, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        RecyclerviewDetail movie_one = new RecyclerviewDetail("《復仇者聯盟4》肯定是2019年全球最多人期待、最多人談論的電影盛事！這部Marvel電影宇宙巔峰之作，一直有傳或會是一些復仇者核心成員的告別作，追隨MCU多年的影迷又豈能錯過這部重中之重的重頭戲？！" +
                "\n\n魁隆彈指之間令半數復仇者英雄和宇宙一半人口灰飛煙滅，餘下的復仇者成員終極集結，團結一致視死如歸，力圖逆轉魁隆造成的局面，重整宇宙秩序。");
        RecyclerviewDetail movie_two = new RecyclerviewDetail("The Godfather");
        RecyclerviewDetail movie_three = new RecyclerviewDetail("The Dark Knight");
        RecyclerviewDetail movie_four = new RecyclerviewDetail("Schindler's List ");
        RecyclerviewDetail movie_five = new RecyclerviewDetail("12 Angry Men ");
        RecyclerviewDetail movie_six = new RecyclerviewDetail("Pulp Fiction");
        RecyclerviewDetail movie_seven = new RecyclerviewDetail("The Lord of the Rings: The Return of the King");
        RecyclerviewDetail movie_eight = new RecyclerviewDetail("The Good, the Bad and the Ugly");
        RecyclerviewDetail movie_nine = new RecyclerviewDetail("Fight Club");
        RecyclerviewDetail movie_ten = new RecyclerviewDetail("Star Wars: Episode V - The Empire Strikes");
        RecyclerviewDetail movie_eleven = new RecyclerviewDetail("Forrest Gump");
        RecyclerviewDetail movie_tweleve = new RecyclerviewDetail("Inception");

        RecyclerviewCategory mBasicInformation = new RecyclerviewCategory
                (getResources().getString(R.string.basic_information), Arrays.asList(movie_two));
        RecyclerviewCategory mPremise = new RecyclerviewCategory
                (getResources().getString(R.string.premise), Arrays.asList(movie_one));
        RecyclerviewCategory mTrailer = new RecyclerviewCategory
                (getResources().getString(R.string.trailer), Arrays.asList(movie_three));
        RecyclerviewCategory mRelatedMovie = new RecyclerviewCategory
                (getResources().getString(R.string.related_movie), Arrays.asList(movie_four));

        final List<RecyclerviewCategory> movieCategories = Arrays.asList(mBasicInformation, mPremise, mTrailer, mRelatedMovie);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
        mAdapter = new RecyclerviewCategoryAdapter(getContext(), movieCategories);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onActivityCreated(savedInstanceState);
    }

    // Save and Restore the state of Expand / Collapsed
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }
}
