  package com.defiance.confine;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

  public class CreateAccountActivity<TAG> extends AppCompatActivity {

      private EditText usernameEditText;
      private EditText emailEditText;
      private EditText passwordEditText;
      private EditText confirmPasswordEditText;
      private Button createAccount;

      private FirebaseUser mUser;
      private FirebaseAuth mAuth;
      private FirebaseAuth.AuthStateListener mAuthStateListener;
      private FirebaseFirestore db;
      public static final String TAG = "CreateAccountActivity";

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        usernameEditText = findViewById(R.id.username_account);
        emailEditText = findViewById(R.id.email_account);
        passwordEditText = findViewById(R.id.password_account);
        confirmPasswordEditText = findViewById(R.id.confirm_password_account);
        createAccount = findViewById(R.id.create_account_button);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = mAuth.getCurrentUser();

                if (mUser != null) {
                    // User Signed In
                }
                else {
                    //User Signed out
                }
            }
        };

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirm_password = confirmPasswordEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(username)
                    && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(password)
                    && !TextUtils.isEmpty(confirm_password)) {

                    if (password.equals(confirm_password)) {
                        createAccount(username, email, password);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match \n Please try Again", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "All Fields are compulsory \n Please try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
   }

      private void createAccount(final String username, final String email, String password) {

          mAuth.createUserWithEmailAndPassword(email, password)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          Toast.makeText(getApplicationContext(), "Account Created Succesfully", Toast.LENGTH_SHORT).show();
                          mAuth = FirebaseAuth.getInstance();
                          mUser = mAuth.getCurrentUser();
                              Map<String, String> user = new HashMap<>();
                              user.put("username", username);
                              user.put("email", email);
//                              user.put("uid", mUser.getUid());
                              db.collection("Users").document(email).set(user)
                                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                                          @Override
                                          public void onSuccess(Void aVoid) {
                                              Log.d(TAG, "Success ");

                                          }
                                      })
                                      .addOnFailureListener(new OnFailureListener() {
                                          @Override
                                          public void onFailure(@NonNull Exception e) {
                                              Log.d(TAG, "Error " + e.toString());
                                          }
                                      });
                              mAuth.signOut();
                              startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                              finish();
                      }
                  });
      }

      @Override
      protected void onStart() {
          super.onStart();
          mUser = mAuth.getCurrentUser();
          mAuth.addAuthStateListener(mAuthStateListener);

      }
  }