package com.example.aman.fusion;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.unique_id_edit_text).setVisibility(View.VISIBLE);
        TextView loginHeader = (TextView) findViewById(R.id.login_header);
        loginHeader.setText(R.string.admin_login);

        TextView notRegisteredTextView = findViewById(R.id.new_to_stree_register_textView);
        notRegisteredTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AdminLoginActivity.this, RegisterActivity.class));
            }
        });

        //        TODO: implement logic for receiving info from use in right way and show toast if the information is not correct
    }
}
