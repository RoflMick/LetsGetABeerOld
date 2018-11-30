package com.example.mikulash.presencefirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

public class MainActivity extends Activity {

    Button butLogin;

    private final static int LOGIN_PERM = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butLogin = findViewById(R.id.butLogin);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAllowNewEmailAccounts(true).build(), LOGIN_PERM);
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), LOGIN_PERM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_PERM){
            startNewActivity(resultCode, data);
        }
    }

    private void startNewActivity(int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Intent intent = new Intent(MainActivity.this, ListOfOnlineActivity.class);
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
