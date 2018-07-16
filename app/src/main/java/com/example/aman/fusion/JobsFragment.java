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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobsFragment extends Fragment {


    public JobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.tabs_fragment, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.categories_view_pager);
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());

        adapter.addFragments(new JobsFirstTab(), getString(R.string.j_tab1));
        adapter.addFragments(new JobsSecondTab(), getString(R.string.j_tab2));
        adapter.addFragments(new JobsThirdTab(), getString(R.string.j_tab3));
        adapter.addFragments(new JobsFourthTab(), getString(R.string.j_tab4));

        viewPager.setAdapter(null);
        viewPager.setAdapter(adapter);
        TabLayout tabs = rootView.findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        return rootView;
    }

    public static class JobsFirstTab extends android.support.v4.app.Fragment{
        public JobsFirstTab(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.list_layout, container, false);


            ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.link1), null, null, getString(R.string.government_jobs_link1)));
            dataItems.add(new ListDataItem(getString(R.string.link2),null, null, getString(R.string.government_jobs_link2)));
            dataItems.add(new ListDataItem(getString(R.string.link3), null, null,getString(R.string.government_jobs_link3)));
            dataItems.add(new ListDataItem(getString(R.string.link4),null, null, getString(R.string.government_jobs_link4)));
            dataItems.add(new ListDataItem(getString(R.string.link5),null, null, getString(R.string.government_jobs_link5)));
            dataItems.add(new ListDataItem(getString(R.string.link6), null, null,getString(R.string.government_jobs_link6)));
            dataItems.add(new ListDataItem(getString(R.string.link7), null, null,getString(R.string.government_jobs_link7)));
            dataItems.add(new ListDataItem(getString(R.string.link8), null, null,getString(R.string.government_jobs_link8)));
            dataItems.add(new ListDataItem(getString(R.string.link9), null, null,getString(R.string.government_jobs_link9)));

            ListView listView = rootView.findViewById(R.id.list_view);
            ListViewAdapter listAdapter = new ListViewAdapter(getActivity(), dataItems);
            listView.setAdapter(listAdapter);
            return rootView;
        }
    }


    public static class JobsSecondTab extends android.support.v4.app.Fragment{
        public JobsSecondTab(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);


            ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.link1),null, null, getString(R.string.private_jobs_link1)));
            dataItems.add(new ListDataItem(getString(R.string.link2), null, null,getString(R.string.private_jobs_link2)));
            dataItems.add(new ListDataItem(getString(R.string.link3), null, null,getString(R.string.private_jobs_link3)));
            dataItems.add(new ListDataItem(getString(R.string.link4), null, null,getString(R.string.private_jobs_link4)));
            dataItems.add(new ListDataItem(getString(R.string.link5), null, null,getString(R.string.private_jobs_link5)));
            dataItems.add(new ListDataItem(getString(R.string.link6), null, null,getString(R.string.private_jobs_link6)));
            dataItems.add(new ListDataItem(getString(R.string.link7),null, null, getString(R.string.private_jobs_link7)));

            ListView listView = rootView.findViewById(R.id.list_view);
            listView.setAdapter(null);
            ListViewAdapter listAdapter = new ListViewAdapter(getActivity(), dataItems);
            listView.setAdapter(listAdapter);
            return rootView;
        }
    }

    public static class JobsThirdTab extends android.support.v4.app.Fragment{
        public JobsThirdTab(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);


            ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.business_loan1), getString(R.string.business_loan1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.business_loan2), getString(R.string.business_loan2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.business_loan3), getString(R.string.business_loan3_desc)));

            ListView listView = rootView.findViewById(R.id.list_view);
            listView.setAdapter(null);
            ListViewAdapter listAdapter = new ListViewAdapter(getActivity(), dataItems);
            listView.setAdapter(listAdapter);
            return rootView;
        }
    }

    public static class JobsFourthTab extends android.support.v4.app.Fragment{
        public JobsFourthTab(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.list_layout, container, false);


            ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.link1), null, null,getString(R.string.carrier_opportunities_link1)));
            dataItems.add(new ListDataItem(getString(R.string.link2), null, null,getString(R.string.carrier_opportunities_link2)));
            dataItems.add(new ListDataItem(getString(R.string.link3), null, null,"www.google.com"));

            ListView listView = rootView.findViewById(R.id.list_view);
            ListViewAdapter listAdapter = new ListViewAdapter(getActivity(), dataItems);
            listView.setAdapter(listAdapter);
            return rootView;
        }
    }
}
