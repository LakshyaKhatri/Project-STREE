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
public class HealthFragment extends Fragment {


    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tabs_fragment, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.categories_view_pager);
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());

        adapter.addFragments(new Health_FirstTab(), getString(R.string.h_tab1));
        adapter.addFragments(new Health_SecondTab(), getString(R.string.h_tab2));
        adapter.addFragments(new Health_ThirdTab(), getString(R.string.h_tab3));
        adapter.addFragments(new Health_FourthTab(), getString(R.string.h_tab4));
        adapter.addFragments(new Health_FifthTab(), getString(R.string.h_tab5));
        adapter.addFragments(new Health_SixthTab(), getString(R.string.h_tab6));

        viewPager.setAdapter(null);
        viewPager.setAdapter(adapter);

        TabLayout tabs = rootView.findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        return rootView;
    }

    public static class Health_FirstTab extends android.support.v4.app.Fragment {

        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_FirstTab() {
        }


        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Women_Health_Tips").removeEventListener(listener);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Health/Images/Women_health_tips");
            final ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.healthtip1), getString(R.string.healthtip1_desc), null, null));
            dataItems.add(new ListDataItem(getString(R.string.healthtip2), getString(R.string.healthtip2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.healthtip3), getString(R.string.healthtip3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.healthtip4), getString(R.string.healthtip4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.healthtip5), getString(R.string.healthtip5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.healthtip6), getString(R.string.healthtip6_desc)));
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

            databaseReference.child("Women_Health_Tips").addValueEventListener(listener);


            return rootView;

        }
    }

    public static class Health_SecondTab extends android.support.v4.app.Fragment {

        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Child_Health_Tips").removeEventListener(listener);
        }

        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_SecondTab() {
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Images/Child_health_tips");
            final ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue1), getString(R.string.women_healthIssue1_desc), null, null));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue2), getString(R.string.women_healthIssue2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue3), getString(R.string.women_healthIssue3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue4), getString(R.string.women_healthIssue4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue5), getString(R.string.women_healthIssue5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue6), getString(R.string.women_healthIssue6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue7), getString(R.string.women_healthIssue7_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue8), getString(R.string.women_healthIssue8_desc)));

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


                    ListViewAdapter adapter = new ListViewAdapter(rootView.getContext(), list);
                    listView.setAdapter(adapter);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            databaseReference.child("Child_Health_Tips").addValueEventListener(listener);


            return rootView;

        }

    }


    public static class Health_ThirdTab extends android.support.v4.app.Fragment {

        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Women_Health_Issues").removeEventListener(listener);
        }
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_ThirdTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Images/Women_Health_issues");

            final ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue1), getString(R.string.women_healthIssue1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue2), getString(R.string.women_healthIssue2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue3), getString(R.string.women_healthIssue3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue4), getString(R.string.women_healthIssue4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue5), getString(R.string.women_healthIssue5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue6), getString(R.string.women_healthIssue6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue7), getString(R.string.women_healthIssue7_desc)));
            dataItems.add(new ListDataItem(getString(R.string.women_healthIssue8), getString(R.string.women_healthIssue8_desc)));

            ListViewAdapter adapter = new ListViewAdapter(getActivity(), dataItems);
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

            databaseReference.child("Women_Health_Issues").addValueEventListener(listener);


            return rootView;
        }
    }


    public static class Health_FourthTab extends android.support.v4.app.Fragment {

        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Child_Health_Issues").removeEventListener(listener);
        }
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_FourthTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Images/Child_health_issues");

            final ArrayList<ListDataItem> dataItems = new ArrayList<>();

            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues1), getString(R.string.child_healthIssues1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues2), getString(R.string.child_healthIssues2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues3), getString(R.string.child_healthIssues3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues4), getString(R.string.child_healthIssues4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues5), getString(R.string.child_healthIssues5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues6), getString(R.string.child_healthIssues6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.child_healthIssues7), getString(R.string.child_healthIssues7_desc)));


            ListViewAdapter adapter = new ListViewAdapter(getActivity(), dataItems);
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

            databaseReference.child("Child_Health_Issues").addValueEventListener(listener);

            return rootView;

        }
    }


    public static class Health_FifthTab extends android.support.v4.app.Fragment {


        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Nutrition_Guide").removeEventListener(listener);
        }
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_FifthTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Images/Nutrition_Guide");

            final ArrayList<ListDataItem> dataItems = new ArrayList<>();
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide1), getString(R.string.nutrition_guide1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide2), getString(R.string.nutrition_guide2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide3), getString(R.string.nutrition_guide3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide4), getString(R.string.nutrition_guide4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide5), getString(R.string.nutrition_guide5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide6), getString(R.string.nutrition_guide6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide7), getString(R.string.nutrition_guide7_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide8), getString(R.string.nutrition_guide8_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide9), getString(R.string.nutrition_guide9_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide10), getString(R.string.nutrition_guide10_desc)));
            dataItems.add(new ListDataItem(getString(R.string.nutrition_guide11), getString(R.string.nutrition_guide11_desc)));


            ListViewAdapter adapter = new ListViewAdapter(getActivity(), dataItems);
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

            databaseReference.child("Nutrition_Guide").addValueEventListener(listener);

            return rootView;

        }
    }




    public static class Health_SixthTab extends android.support.v4.app.Fragment {
        @Override
        public void onDetach() {
            super.onDetach();
            databaseReference.child("Hygiene").removeEventListener(listener);
        }
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        FirebaseStorage storage;
        StorageReference storageReference;
        ValueEventListener listener;

        public Health_SixthTab() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_layout, container, false);

            final ListView listView = rootView.findViewById(R.id.list_view);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("Health");

            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference("Education/Images/Hygiene_Methods");

            final ArrayList<ListDataItem> dataItems = new ArrayList<>();
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method1), getString(R.string.hygiene_method1_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method2), getString(R.string.hygiene_method2_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method3), getString(R.string.hygiene_method3_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method4), getString(R.string.hygiene_method4_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method5), getString(R.string.hygiene_method5_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method6), getString(R.string.hygiene_method6_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method7), getString(R.string.hygiene_method7_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method8), getString(R.string.hygiene_method8_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method9), getString(R.string.hygiene_method9_desc)));
            dataItems.add(new ListDataItem(getString(R.string.hygiene_method10), getString(R.string.hygiene_method10_desc)));



            ListViewAdapter adapter = new ListViewAdapter(getActivity(), dataItems);
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

            databaseReference.child("Hygiene").addValueEventListener(listener);
            return rootView;
        }
    }
}
