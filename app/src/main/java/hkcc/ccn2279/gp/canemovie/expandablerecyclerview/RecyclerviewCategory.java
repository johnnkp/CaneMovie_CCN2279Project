package hkcc.ccn2279.gp.canemovie.expandablerecyclerview;

import java.util.List;

public class RecyclerviewCategory implements ParentListItem {
    private String mName;
    private List<RecyclerviewDetail> mMovieInfo;

    public RecyclerviewCategory(String name, List<RecyclerviewDetail> movies) {
        mName = name;
        mMovieInfo = movies;
    }

    public String getName() {
        return mName;
    }
    @Override
    public List<?> getChildItemList() {
        return mMovieInfo;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
