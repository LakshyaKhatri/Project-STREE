package com.example.aman.fusion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreLinksFragment extends Fragment {


    public MoreLinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabs_fragment, container, false);
        ViewPager viewPager = view.findViewById(R.id.categories_view_pager);

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());
        adapter.addFragments(new MoreLinksFragment_Tab(),null);

        viewPager.setAdapter(adapter);

        TabLayout tabs = view.findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    public static class MoreLinksFragment_Tab extends Fragment{
        public MoreLinksFragment_Tab(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.list_layout, container, false);
            ListView listView = view.findViewById(R.id.list_view);
            ArrayList<ListDataItem> dataItems = new ArrayList<>();
            dataItems.add(new ListDataItem("hello", "It's just a tab"));

            ListViewAdapter adapter = new ListViewAdapter(getContext(), dataItems);
            listView.setAdapter(adapter);
            return view;
        }
    }
}
