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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EducationFragment extends Fragment {


    public EducationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tabs_fragment, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.categories_view_pager);
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());

        adapter.addFragments(new Education_FirstTab(), getString(R.string.education_tab1));
        adapter.addFragments(new Education_SecondTab(), getString(R.string.education_tab2));

        viewPager.setAdapter(null);
        viewPager.setAdapter(adapter);

        TabLayout tabs = rootView.findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        return rootView;

    }

    public static class Education_FirstTab extends android.support.v4.app.Fragment {


        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Child_Education").removeEventListener(listener);
        }

        public Education_FirstTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Education");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Education_Images/Child_Education");
            final ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.education_head1_education_problem), getString(R.string.education_head1_desc_education_problem), null, null));
            dataItems.add(new ListDataItem(getString(R.string.education_head2_education_problem), getString(R.string.education_head2_desc_education_problem)));
            dataItems.add(new ListDataItem(getString(R.string.education_head3_education_problem), getString(R.string.education_head3_desc_education_problem)));
            dataItems.add(new ListDataItem(getString(R.string.education_head4_education_problem), getString(R.string.education_head4_desc_education_problem)));
            dataItems.add(new ListDataItem(getString(R.string.education_head5_education_problem), getString(R.string.education_head5_desc_education_problem)));
            dataItems.add(new ListDataItem(getString(R.string.education_head6_education_problem), getString(R.string.education_head6_desc_education_problem)));
            ListViewAdapter adapter = new ListViewAdapter(getContext(), dataItems);
            listView.setAdapter(adapter);


            final ArrayList<ListDataItem> list = new ArrayList<>();


            listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String title = ds.child("titleText").getValue(String.class);
                        String desc = ds.child("contentText").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        String link = ds.child("extraLink").getValue(String.class);

                        list.add(new ListDataItem(title, desc, imageUrl, link));
                    }


                    list.addAll(dataItems);


                    ListViewAdapter adapter = new ListViewAdapter(getContext(), list);
                    listView.setAdapter(adapter);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            databaseReference.child("Child_Education").addValueEventListener(listener);


            return rootView;
        }
    }


//    SSECONG TAB FRO EDUCATION

    public static class Education_SecondTab extends Fragment {



        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Education_Policy").removeEventListener(listener);
        }

        public Education_SecondTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Education");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Education_Images/Education_policy");
            final ArrayList<ListDataItem> dataItems = new ArrayList<>();


            dataItems.add(new ListDataItem(getString(R.string.women_education_head1), getString(R.string.women_education_head1_desc), null, null));
            dataItems.add(new ListDataItem(getString(R.string.women_education_head2), getString(R.string.women_education_head2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_education_head3), getString(R.string.women_education_head3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_education_head4), getString(R.string.women_education_head4_desc)));

            ListViewAdapter adapter = new ListViewAdapter(getContext(), dataItems);
            listView.setAdapter(adapter);


            final ArrayList<ListDataItem> list = new ArrayList<>();


            listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String title = ds.child("titleText").getValue(String.class);
                        String desc = ds.child("contentText").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        String link = ds.child("extraLink").getValue(String.class);

                        list.add(new ListDataItem(title, desc, imageUrl, link));
                    }


                    list.addAll(dataItems);


                    ListViewAdapter adapter = new ListViewAdapter(getContext(), list);
                    listView.setAdapter(adapter);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            databaseReference.child("Education_Policy").addValueEventListener(listener);
            return rootView;
        }

    }
}
