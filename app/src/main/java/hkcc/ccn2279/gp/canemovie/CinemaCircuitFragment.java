package hkcc.ccn2279.gp.canemovie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static hkcc.ccn2279.gp.canemovie.MainActivity.fragmentManager;

public class CinemaCircuitFragment extends Fragment {
    public CinemaCircuitFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_cinema, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setOnClickListener(R.id.broadway, "https://www.cinema.com.hk/tc/movie/details/11397");
        setOnClickListener(R.id.cgv, "https://cgv.com.hk/zh/movie/index/118");
        setOnClickListener(R.id.chinachem, "http://www.cel-cinemas.com/movieDetail.jsp?movieid=2651");
        setOnClickListener(R.id.cinema_city, "https://www.cinemacity.com.hk/tc/movie/details/v271");
        setOnClickListener(R.id.cityline, "https://www.cityline.com/movies/124538/Details.do");
        setOnClickListener(R.id.emperor, "https://www.emperorcinemas.com/zh/ticketing/movie_detail/showing/v58");
        setOnClickListener(R.id.golden_harvest, "https://www.goldenharvest.com/film/detail?film_id=869");
        setOnClickListener(R.id.mcl, "http://www.mclcinema.com/MovieSet.aspx?id=11294");
        setOnClickListener(R.id.metroplex, "https://www.metroplex.com.hk/zh_tw/movie/nowshowing/3146");
        setOnClickListener(R.id.newport, "https://www.theatre.com.hk/tc/movie/1601");
        setOnClickListener(R.id.ua, "https://www.uacinemas.com.hk/chi/movie/HO00000573");
        super.onActivityCreated(savedInstanceState);
    }

    private void setOnClickListener(int cinema, String website) {
        ImageView mCinema = getView().findViewById(cinema);
        mCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the fragment as the main content.
                fragmentManager.beginTransaction()
                        .add(R.id.container, new CinemaCircuitInfoFragment()).commit();
            }
        });
    }
}
