package com.example.nnaji_macpro1.realmdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nnaji_macpro1.realmdemo.model.SocialAccount;
import com.example.nnaji_macpro1.realmdemo.model.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etPersonName, etAge, etSocialAccountName, etStatus;

    //Initialize realm instanceprivate Realm myRealm;
    private Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPersonName 		= (EditText) findViewById(R.id.etPersonName);
        etAge 				= (EditText) findViewById(R.id.etAge);
        etSocialAccountName = (EditText) findViewById(R.id.etSocialAccount);
        etStatus 			= (EditText) findViewById(R.id.etStatus);

        myRealm = Realm.getDefaultInstance();

    }

    // Add data to Realm using Main UI Thread. Be Careful: As it may BLOCK the UI.
    public void addUserToRealm_Synchronously(View view) {

        final String id = UUID.randomUUID().toString();
        final String name 				= etPersonName.getText().toString();
        final int age 					= Integer.valueOf(etAge.getText().toString());
        final String socialAccountName 	= etSocialAccountName.getText().toString();
        final String status 				= etStatus.getText().toString();

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //Always pass the primary key as the second parameter of the createObject function
                SocialAccount socialAccount = realm.createObject(SocialAccount.class);
                socialAccount.setName(socialAccountName);
                socialAccount.setStatus(status);

                User user = realm.createObject(User.class, id);
                user.setName(name);
                user.setAge(age);
                user.setSocialAccount(socialAccount);
                Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }



        //Add Data to Realm in the Background Thread

        public void addUserToRealm_ASynchronously (View view){

        }




        public void displayAllUsers(View view){

    }


}
