package hkcc.ccn2279.gp.canemovie;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hkcc.ccn2279.gp.canemovie.xml.Discussion;
import hkcc.ccn2279.gp.canemovie.xml.DomParseXML;

import static hkcc.ccn2279.gp.canemovie.MainActivity.fragmentManager;

public class ForumFragment extends Fragment {
    // https://givemepass.blogspot.com/2015/10/tablayout.html
    private TabLayout mTabs;
    private ViewPager mViewPager;
    // https://givemepass.blogspot.com/2015/12/recyclerview.html
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public ForumFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forum_index, container, false);
        return rootView;
    }

    // https://givemepass.blogspot.com/2015/10/tablayout.html
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mTabs = getView().findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText(getString(R.string.general_forum)));
        mTabs.addTab(mTabs.newTab().setText(getString(R.string.pro_forum)));

        mViewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        super.onActivityCreated(savedInstanceState);
    }

    // https://givemepass.blogspot.com/2015/12/recyclerview.html
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);
            }
        }

        public MyAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forum_index_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mTextView.setText(mData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new ForumCommentFragment()).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class SamplePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.forum_index_recyclerview,
                    container, false);
            container.addView(view);

            ArrayList<String> myDataset = new ArrayList<>();
            // https://blog.csdn.net/withiter/article/details/19908679
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            List<Discussion> discussions = new ArrayList<Discussion>();
            discussions = DomParseXML.getPost("https://raw.githubusercontent.com/johnnkp/CanemovieXMLDB_CCN2279Project/master/app/forum.xml");
            System.out.println(discussions.size());
            for (int count = 0; count < discussions.size(); count++) {
                myDataset.add(discussions.get(count).getTitle());
                System.out.println(discussions.get(count).getTitle());
            }

            mAdapter = new MyAdapter(myDataset);
            mRecyclerView = (RecyclerView) getView().findViewById(R.id.forum_index_recyclerview);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
