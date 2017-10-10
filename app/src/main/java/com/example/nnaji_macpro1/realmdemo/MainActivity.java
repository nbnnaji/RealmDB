package com.example.nnaji_macpro1.realmdemo;

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
    private Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etPersonName = (EditText) findViewById(R.id.etPersonName);
        etAge = (EditText) findViewById(R.id.etAge);
        etSocialAccountName = (EditText) findViewById(R.id.etSocialAccount);
        etStatus = (EditText) findViewById(R.id.etStatus);
        myRealm =Realm.getDefaultInstance();
    }

        //Add data to Realm using Main UI Thread. Be careful : As it may BLOCK UI
        public void addUserToRealm_Synchronously(View view) {

            //Make these final when using option 2 method
            final String id = UUID.randomUUID().toString();
           final String name = etPersonName.getText().toString();
            final  int age = Integer.valueOf(etAge.getText().toString());
            final  String socialAccountName = etSocialAccountName.getText().toString();
            final String status = etStatus.getText().toString();

/**
 * Perform insertion to the realm DB manually: begin, commit and error handling
 */
//            try {
                //Note: Whatever you write between beginTransaction and commitTransaction
                // will be saved to the Realm Database
//                myRealm.beginTransaction();

//                SocialAccount socialAccount = myRealm.createObject(SocialAccount.class);
//                socialAccount.setName(socialAccountName);
//                socialAccount.setStatus(status);
//
//                User user = myRealm.createObject(User.class, id);
//                user.setName(name);
//                user.setAge(age);
//                user.setSocialAccount(socialAccount);
//                myRealm.commitTransaction();
//
//            } catch(Exception e) {
//                myRealm.cancelTransaction();
//            }

            /**
             * In this case begin/commit/cancel transaction is not used in this option.
             * The method does all that automatically.
             */
            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    SocialAccount socialAccount = myRealm.createObject(SocialAccount.class);
                    socialAccount.setName(socialAccountName);
                    socialAccount.setStatus(status);

                    User user = myRealm.createObject(User.class, id);
                    user.setName(name);
                    user.setAge(age);
                    user.setSocialAccount(socialAccount);
                    Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            });

        }




        //Add Data to Realm in the Background Thread

        public void addUserToRealm_ASynchronously (View view){

        }




        public void displayAllUsers(View view){

    }

}
