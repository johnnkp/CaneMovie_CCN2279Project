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

import java.util.Arrays;
import java.util.List;

import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewCategory;
import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewCategoryAdapter;
import hkcc.ccn2279.gp.canemovie.expandablerecyclerview.RecyclerviewDetail;

public class CinemaCircuitInfoFragment extends Fragment {
    private RecyclerviewCategoryAdapter mAdapter;
    private RecyclerView recyclerView;

    public CinemaCircuitInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cinema_circuit_info, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        RecyclerviewDetail basicInfo = new RecyclerviewDetail
                ("百老匯戲院於1950年開業，並於1987年重建於旺角西洋菜街現址，隨後逐發展成百老匯院線，是現時香港最大的院線。" +
                        "百老匯院線由安樂影片有限公司管理，旗下有4個品牌，" +
                        "包括具時尚優質的「百老匯」和「My Cinema」, 高端格調的「PALACE」和 全新「PREMIERE」及文化生活概念戲院「MOViE MOViE」等，覆蓋香港、九龍及新界各區。" +
                        "於2006年起，安樂影片有限公司開始為美國AMC影院管理香港AMC戲院。" +
                        "現時百老匯院線於香港共有14間戲院，合共82個銀幕及超過12,000個寬敞舒適的座位。" +
                        "每間戲院均有不同的設計風格，及配有先進的數碼影院設備，為觀眾提供極盡完美的視聽享受及觀影環境。" +

                        "\n\n為進一步推動電影文化，開拓無盡及新穎的電影空間，本院線於1996年11月成立了百老匯電影中心 (簡稱電影中心)，" +
                        "讓喜愛電影的觀眾可以悠閒地流連忘返，締造親切及舒適的交流地。" +
                        "電影中心是現時香港獨一無二，集合放映多元化及高質素中外電影以及舉辦推動電影文化活動的電影文化地標。" +
                        "電影中心特設會員計劃 - bcinephile，希望在提供多項購票及消費優惠之餘，亦能通過籌辦多元化之活動，包括電影特備節目、導演回顧展、專題講座、電影課程等，" +
                        "讓貪新懷舊的影癡更進一步認識電影文化，更投入多姿多采的電影空間。");
        RecyclerviewDetail MK = new RecyclerviewDetail("地址：九龍旺角西洋菜街6-12號");
        RecyclerviewDetail YauMaTei = new RecyclerviewDetail("地址：九龍油麻地眾坊街3號駿發花園");
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
                (getResources().getString(R.string.basic_information), Arrays.asList(basicInfo));
        RecyclerviewCategory mMK = new RecyclerviewCategory
                ("旺角百老匯", Arrays.asList(MK));
        RecyclerviewCategory mYauMaTei = new RecyclerviewCategory
                ("百老匯電影中心", Arrays.asList(YauMaTei));
        RecyclerviewCategory mRelatedMovie = new RecyclerviewCategory
                (getResources().getString(R.string.related_movie), Arrays.asList(movie_four));

        final List<RecyclerviewCategory> movieCategories = Arrays.asList(mBasicInformation, mMK, mYauMaTei);

        recyclerView = (RecyclerView) getView().findViewById(R.id.address_recyclerview);
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
