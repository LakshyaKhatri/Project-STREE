package com.example.aman.fusion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InterviewFragment extends Fragment {

    public InterviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tabs_fragment, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.categories_view_pager);
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());

        adapter.addFragments(new InterviewFirstTab(), getString(R.string.i_tab1));

        viewPager.setAdapter(null);
        viewPager.setAdapter(adapter);
        TabLayout tabs = rootView.findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        return rootView;
    }


    public static class InterviewFirstTab extends android.support.v4.app.Fragment {

        public InterviewFirstTab() {
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.list_layout, container, false);


            ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.interview_tips1), getString(R.string.interview_tips1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips2), getString(R.string.interview_tips2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips4), getString(R.string.interview_tips4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips3), getString(R.string.interview_tips3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips5), getString(R.string.interview_tips5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips6), getString(R.string.interview_tips6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips7), getString(R.string.interview_tips7_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips8), getString(R.string.interview_tips8_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips9), getString(R.string.interview_tips9_desc)));
            dataItems.add(new ListDataItem(getString(R.string.interview_tips10), getString(R.string.interview_tips10_desc)));

            ListView listView = rootView.findViewById(R.id.list_view);
            ListViewAdapter interviewAdapter = new ListViewAdapter(getActivity(), dataItems);
            listView.setAdapter(interviewAdapter);
            return rootView;

        }
    }

}
