package com.example.nnaji_macpro1.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.nnaji_macpro1.realmdemo.model.SocialAccount;
import com.example.nnaji_macpro1.realmdemo.model.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etPersonName, etAge, etSocialAccountName, etStatus;
    private Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String id = UUID.randomUUID().toString();
        etPersonName = (EditText) findViewById(R.id.etPersonName);
        etAge = (EditText) findViewById(R.id.etAge);
        etSocialAccountName = (EditText) findViewById(R.id.etSocialAccount);
        etStatus = (EditText) findViewById(R.id.etStatus);
        myRealm =Realm.getDefaultInstance();
    }

        //Add data to Realm using Main UI Thread. Be careful : As it may BLOCK UI
        public void addUserToRealm_Sychronously(View view) {

            String name = etPersonName.getText().toString();
            int age = Integer.valueOf(etAge.getText().toString());
            String socialAccountName = etSocialAccountName.getText().toString();
            String status = etStatus.getText().toString();


            try {
                myRealm.beginTransaction();

                SocialAccount socialAccount = myRealm.createObject(SocialAccount.class);
                socialAccount.setName(socialAccountName);
                socialAccount.setStatus(status);

                User user = myRealm.createObject(User.class, id);
                user.setName(name);
                user.setAge(age);
                user.setSocialAccount(socialAccount);
                myRealm.commitTransaction();

            }catch(Exception e) {
                myRealm.cancelTransaction();
            }

        }




        //Add Data to Realm in the Background Thread

        public void addUserToRealm_ASynchronously (View view){

        }




        public void displayAllUsers(View view){

    }

}
