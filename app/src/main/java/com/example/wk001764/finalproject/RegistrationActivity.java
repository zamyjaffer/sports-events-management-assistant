package com.example.wk001764.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mEmail, mPassword, mName;
    private RadioGroup mRadioGroup;
    private CheckBox mBadminton, mBasketball, mBoxing, mCricket, mFootball, mGolf, mHockey, mRugby, mSwimming, mTennis;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mRegister = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mName = (EditText) findViewById(R.id.name);
        mRadioGroup = (RadioGroup) findViewById(R.id.gender);
        mBadminton = (CheckBox) findViewById(R.id.badminton);
        mBasketball = (CheckBox) findViewById(R.id.basketball);
        mBoxing = (CheckBox) findViewById(R.id.boxing);
        mCricket = (CheckBox) findViewById(R.id.cricket);
        mFootball = (CheckBox) findViewById(R.id.football);
        mGolf = (CheckBox) findViewById(R.id.golf);
        mHockey = (CheckBox) findViewById(R.id.hockey);
        mRugby = (CheckBox) findViewById(R.id.rugby);
        mSwimming = (CheckBox) findViewById(R.id.swimming);
        mTennis = (CheckBox) findViewById(R.id.tennis);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectId = mRadioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = (RadioButton) findViewById(selectId);
                if(radioButton.getText() == null){
                    return;
                }

                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }else{
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                            Map userInfo = new HashMap<>();
                            userInfo.put("name", name);
                            userInfo.put("sex", radioButton.getText().toString());
                            userInfo.put("profileImageUrl", "default");
                            currentUserDb.updateChildren(userInfo);
                        }
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }else {
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Sport");
                            Map userInfo = new HashMap<>();

                            if(mBadminton.isChecked()) {
                                userInfo.put("badminton",true);
                            }else{
                                userInfo.put("badminton",false);
                            }
                            if(mBasketball.isChecked()) {
                                userInfo.put("basketball",true);
                            }else{
                                userInfo.put("basketball",false);
                            }
                            if(mBoxing.isChecked()) {
                                userInfo.put("boxing",true);
                            }else{
                                userInfo.put("boxing",false);
                            }
                            if(mCricket.isChecked()) {
                                userInfo.put("cricket",true);
                            }else{
                                userInfo.put("cricket",false);
                            }
                            if(mFootball.isChecked()) {
                                userInfo.put("football",true);
                            }else{
                                userInfo.put("football",false);
                            }
                            if(mGolf.isChecked()) {
                                userInfo.put("golf",true);
                            }else{
                                userInfo.put("golf",false);
                            }
                            if(mHockey.isChecked()) {
                                userInfo.put("hockey",true);
                            }else{
                                userInfo.put("hockey",false);
                            }
                            if(mRugby.isChecked()) {
                                userInfo.put("ruby",true);
                            }else{
                                userInfo.put("rugby",false);
                            }
                            if(mSwimming.isChecked()) {
                                userInfo.put("swimming",true);
                            }else{
                                userInfo.put("swimming",false);
                            }
                            if(mTennis.isChecked()) {
                                userInfo.put("tennis",true);
                            }else{
                                userInfo.put("tennis",false);
                            }
                            currentUserDb.updateChildren(userInfo);
                        }

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}