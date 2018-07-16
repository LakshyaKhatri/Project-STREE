package com.example.aman.fusion;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.Locale;

public class MainBaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Bundle savedState;
    NavigationView navigationView;
    int currentFragmentId;
    ActionBarDrawerToggle toggle;
    TextToSpeech ttsObject;
    final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_base);

        savedState = savedInstanceState;
        navigationView = findViewById(R.id.nav_view);

//        Sets Up the toggle button in the ActionBar
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Sets View to Home Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new HomeFragment()).commit();
        currentFragmentId = R.id.Home;

        //highlights the "Home" in the navigation view
        navigationView.setCheckedItem(R.id.Home);

        setNavigationItemListeners();
        setNavigationHeaderItemListeners();
    }

    public void speak(String text){

    }
    @Override
    protected void onPause() {
        if(ttsObject != null) {
            ttsObject.stop();
            ttsObject.shutdown();
        }
        super.onPause();
    }

    public void inflateHomeFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new HomeFragment()).commit();
        currentFragmentId = R.id.Home;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflateHealthFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new HealthFragment()).commit();
        currentFragmentId = R.id.Health;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflateEducationFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new EducationFragment()).commit();
        currentFragmentId = R.id.Education;
        navigationView.setCheckedItem(currentFragmentId);

    }

    public void inflateJobsFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new JobsFragment()).commit();
        currentFragmentId = R.id.jobs;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflateInterviewFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new InterviewFragment()).commit();
        currentFragmentId = R.id.Interview;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflatePoliciesFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new PoliciesFragment()).commit();
        currentFragmentId = R.id.poliices;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflateLegalSupportFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new LegalSupportFragment()).commit();
        currentFragmentId = R.id.legal_support;
        navigationView.setCheckedItem(currentFragmentId);
    }


    public void inflateComplaintBoxFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new ComplaintBoxFragment()).commit();
        currentFragmentId = R.id.Complaint_Box;
        navigationView.setCheckedItem(currentFragmentId);
    }

    public void inflateMoreLinksFragment(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new MoreLinksFragment()).commit();
        currentFragmentId = R.id.More_Links;
        navigationView.setCheckedItem(currentFragmentId);
    }

    void setNavigationItemListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                if (currentFragmentId == item.getItemId())
                    return false;
                switch (item.getItemId()) {
                    case R.id.Home:
                        inflateHomeFragment(null);
                        return true;
                    case R.id.Health:
                        inflateHealthFragment(null);
                        return true;
                    case R.id.Education:
                        inflateEducationFragment(null);
                        return true;
                    case R.id.jobs:
                        inflateJobsFragment(null);
                        return true;
                    case R.id.Interview:
                        inflateInterviewFragment(null);
                        return true;
                    case R.id.poliices:
                        inflatePoliciesFragment(null);
                        return true;
                    case R.id.legal_support:
                        inflateLegalSupportFragment(null);
                        return true;
                    case R.id.More_Links:
                        inflateMoreLinksFragment(null);
                        return true;
                    case R.id.Complaint_Box:
                        inflateComplaintBoxFragment(null);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public  void openEHaatUrl(View view){
        Intent eHaatUrl = new Intent(Intent.ACTION_VIEW);
        eHaatUrl.setData(Uri.parse(getString(R.string.e_haat_url)));
        startActivity(eHaatUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    void setNavigationHeaderItemListeners() {
        View headerView = navigationView.getHeaderView(0);
        Button navigationHeaderLoginButton = headerView.findViewById(R.id.nav_header_login_button);

//        TODO: setup click listeners on these buttons
        Button navigationHeaderLogoutButton = headerView.findViewById(R.id.nav_header_logout_button);

//        Register Button Setup
        Button navigationHeaderRegisterButton = headerView.findViewById(R.id.nav_header_register_button);
        navigationHeaderRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainBaseActivity.this, RegisterActivity.class));
            }
        });

//        Login Button Setup
//        TODO: implement code for closing popup on pressing back button
        LayoutInflater inflater = (LayoutInflater) MainBaseActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, android.app.ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT, true);
        if (Build.VERSION.SDK_INT >= 21) {
            popupWindow.setElevation(5.0f);
        }

        ImageView dismissButton = popupView.findViewById(R.id.popup_dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        LinearLayout loginAsUserField = popupView.findViewById(R.id.login_as_user);
        loginAsUserField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                startActivity(new Intent(getBaseContext(), UserLoginActivity.class));
            }
        });

        LinearLayout loginAsAdminField = popupView.findViewById(R.id.login_as_admin);
        loginAsAdminField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                startActivity(new Intent(MainBaseActivity.this, AdminLoginActivity.class));
            }
        });

//        Shows popup on clicking on login button
        navigationHeaderLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                popupWindow.showAtLocation(findViewById(R.id.layout_container), Gravity.CENTER, 0, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawers();
            return;
        }

        if(currentFragmentId != R.id.Home){
            inflateHomeFragment(null);
            return;
        }
        super.onBackPressed();
    }
}
