package com.example.aman.fusion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

public class AdminBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_fragment);

        ViewPager viewPager = findViewById(R.id.categories_view_pager);
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragments(new Admin_UploadData(), getString(R.string.upload_data));

        viewPager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);


    }


    public static class Admin_UploadData extends Fragment {

        EditText titleAdmin, descAdmin, LinkAdmin;
        Button uploadImage, Upload;
        View v;
        Spinner tabSpinner, categorySpinner;
        String title, desc, image, link, ImageStoragePath;
        LinearLayout l;
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        String[] secondSpinnerData;
        ArrayAdapter arrayAdapter;
        int firstSpinnerItem;
        Intent i;
        Uri uri;
        FirebaseStorage storage;
        StorageReference storageReference, ImageReference;
        Random rand = new Random();
        int random;
        ProgressDialog d;

        public Admin_UploadData() {
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.admin_upload_data, container, false);

//INITIALIZING VARIABLE
            titleAdmin = v.findViewById(R.id.Admin_Title);
            descAdmin = v.findViewById(R.id.Admin_Desc);
            LinkAdmin = v.findViewById(R.id.Admin_Link);
            uploadImage = v.findViewById(R.id.UploadImage);
            Upload = v.findViewById(R.id.Upload);

            database = FirebaseDatabase.getInstance();
            storage = FirebaseStorage.getInstance();
            tabSpinner = v.findViewById(R.id.Tab_Spinner);
            categorySpinner = v.findViewById(R.id.Categories_Spinner);
            d = new ProgressDialog(getContext());
            storageReference = storage.getReference();
            //SETTING SECOND SPINNER DATA ACCORDING TO FIRST SPINNER
            tabSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            secondSpinnerData = getResources().getStringArray(R.array.health_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;
                        case 1:
                            secondSpinnerData = getResources().getStringArray(R.array.education_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;

                        case 2:
                            secondSpinnerData = getResources().getStringArray(R.array.jobs_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;

                        case 3:
                            secondSpinnerData = getResources().getStringArray(R.array.interview_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;
                        case 4:
                            secondSpinnerData = getResources().getStringArray(R.array.policy_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;
                        case 5:
                            secondSpinnerData = getResources().getStringArray(R.array.legislation_spinner);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;
                        case 6:
                            secondSpinnerData = getResources().getStringArray(R.array.links);
                            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, secondSpinnerData);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            categorySpinner.setAdapter(arrayAdapter);
                            break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //GETTING STORAGE AND DATABASE PATH FROM SECOND SPINNER
            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    firstSpinnerItem = tabSpinner.getSelectedItemPosition();
                    switch (firstSpinnerItem) {
                        case 0:  //1st spinner ==Health
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Health").child("Women_Health_Tips");
                                    ImageStoragePath = "Health/Images/Women_health_tips/";
                                    break;
                                case 2:
                                    databaseReference = database.getReference().child("Health").child("Women_Health_Issues");
                                    ImageStoragePath = "Health/Images/Women_Health_issues/";
                                    break;
                                case 1:
                                    databaseReference = database.getReference().child("Health").child("Child_Health_Tips");
                                    ImageStoragePath = "Health/Images/Child_health_tips/";
                                    break;
                                case 3:
                                    databaseReference = database.getReference().child("Health").child("Child_Health_Issues");
                                    ImageStoragePath = "Health/Images/Child_health_issues/";
                                    break;
                                case 4:
                                    databaseReference = database.getReference().child("Health").child("Nutrition_Guide");
                                    ImageStoragePath = "Health/Images/Nutrition_Guide/";
                                    break;
                                case 5:
                                    databaseReference = database.getReference().child("Health").child("Hygiene");
                                    ImageStoragePath = "Health/Images/Hygiene_Methods/";
                                    break;
                            }
                            break;

                        case 1:  //1st spinner ==EDUCATION
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Education").child("Child_Education");
                                    ImageStoragePath = "Education/Education_Images/Child_Education/";
                                    break;
                                case 1:
                                    databaseReference = database.getReference().child("Education").child("Education_Policy");
                                    ImageStoragePath = "Education/Education_Images/Education_policy/";
                                    break;
                            }
                            break;

                        case 2:     //1st spinner ==JOBS
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Jobs").child("Goverment_jobs");
                                    ImageStoragePath = "Jobs/jobs_images/Government_jobs";
                                    break;

                                case 1:
                                    databaseReference = database.getReference().child("Jobs").child("Private_jobs");
                                    ImageStoragePath = "Jobs/jobs_images/Private_jobs";
                                    break;
                                case 2:
                                    databaseReference = database.getReference().child("Jobs").child("Business_rules");
                                    ImageStoragePath = "Jobs/jobs_images/Bussiness_rules";
                                    break;

                                case 3:
                                    databaseReference = database.getReference().child("Jobs").child("Carrier_opportunity");
                                    ImageStoragePath = "Jobs/jobs_images/Carrier_opportunity";
                                    break;
                            }
                            break;

                        case 3:     //1st spinner ==INTERVIEW
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Interview").child("Interview_skills");
                                    ImageStoragePath = "Interview/Interview_Images/Interview_skills";
                                    break;
                                case 1:
                                    databaseReference = database.getReference().child("Interview").child("Interview_tips");
                                    ImageStoragePath = "Interview/Interview_Images/Interview_tips";
                                    break;
                            }
                            break;

                        case 4:     //1st spinner ==POLICIES
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Policies").child("_policies");
                                    ImageStoragePath = "Policies/Policies_Images/policies/";
                                    break;
                                case 1:
                                    databaseReference = database.getReference().child("Policies").child("schemes");
                                    ImageStoragePath = "Policies/Policies_Images/schemes/";
                                    break;

                            }
                            break;

                        case 5: //1st SPINNER == Legislation
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Legislation").child("Women_Legislation");
                                    ImageStoragePath = "Legislation/Legislation_Images/Women_Legislation/";
                                    break;
                                case 1:
                                    databaseReference = database.getReference().child("Legislation").child("Child_Legislation");
                                    ImageStoragePath = "Legislation/Legislation_Images/Child_legislation/";
                                    break;
                                case 2:
                                    databaseReference = database.getReference().child("Legislation").child("Other_legislation");
                                    ImageStoragePath = "Legislation/Legislation_Images/Others_legislation";
                                    break;
                            }
                            break;

                        case 6:
                            switch (i) {
                                case 0:
                                    databaseReference = database.getReference().child("Links").child("new_Links");
                                    break;
                            }

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            uploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 0);

                }
            });


            //UPLOAD DATA HERE
            Upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    title = titleAdmin.getText().toString().trim();
                    desc = descAdmin.getText().toString().trim();
                    link = LinkAdmin.getText().toString().trim();
                    if (title.isEmpty()) {
                        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    } else if (desc.isEmpty() && link.isEmpty()) {
                        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    } else {
                        titleAdmin.setText("");
                        descAdmin.setText("");
                        LinkAdmin.setText("");

                        ListDataItem data = new ListDataItem(title, desc, image, link);

                        databaseReference.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                image = null;

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }
                }
            });

            return v;

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, final Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                d.setTitle("UPLOADING");
                d.show();
                uri = data.getData();
                title = titleAdmin.getText().toString();
                random = rand.nextInt();
                ImageReference = storageReference.child(ImageStoragePath + title + random);
                ImageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        image = taskSnapshot.getDownloadUrl().toString();
                        Toast.makeText(getContext(), getString(R.string.uploaded) + image, Toast.LENGTH_SHORT).show();
                        d.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                });
            }
        }
    }
}


