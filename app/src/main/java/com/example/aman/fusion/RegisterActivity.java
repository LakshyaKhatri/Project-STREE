package com.example.aman.fusion;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        TextView alreadyRegisteredTextView = findViewById(R.id.already_registered_login_textView);
        alreadyRegisteredTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

//        TODO: implement logic for receiving info from use in right way and show toast if the information is not correct
    }

    void showPopupWindow(){
        LayoutInflater inflater = (LayoutInflater) RegisterActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
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
                finish();
                startActivity(new Intent(RegisterActivity.this, UserLoginActivity.class));
            }
        });

        LinearLayout loginAsAdminField = popupView.findViewById(R.id.login_as_admin);
        loginAsAdminField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
                startActivity(new Intent(RegisterActivity.this, AdminLoginActivity.class));
            }
        });

        popupWindow.showAtLocation(findViewById(R.id.register_root_view), Gravity.CENTER, 0, 0);
    }
}
