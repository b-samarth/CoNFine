package com.defiance.confine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button signinButton;
    private TextView signupText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView forgotText;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSigninClient;
    private GoogleSignInOptions gso;
    FirebaseFirestore db;

    private static final int RC_SIGN_IN = 7;
    private String TAG = "LoginActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signinButton = findViewById(R.id.sign_in);
        signupText = findViewById(R.id.signup);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        forgotText = findViewById(R.id.forgot);
        signupText.setText(Html.fromHtml("Not yet Registered? <u>Sign Up</u>"));
        forgotText.setText(Html.fromHtml("<u>Forgot Password?</u>"));

        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleSigninClient = GoogleSignIn.getClient(this, gso);
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.googlesignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googlesignin();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,
                        CreateAccountActivity.class));
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = emailEditText.getText().toString().trim();
                final String password = passwordEditText.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();
                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Check your credentials and try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter your login details first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void googlesignin() {

        Intent intent = mGoogleSigninClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                linktofirebase(account);
            } catch (ApiException e) {
                Log.d(TAG,"Google Sign in failed " + e.toString());
            }

        }
    }

    private void linktofirebase(GoogleSignInAccount account) {
        Log.d(TAG, "linktofirebase "+ account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linktofirebase success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            Log.d(TAG, "linktofirebase failed");
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Map<String, String> users = new HashMap<>();
            users.put("username", user.getDisplayName());
            users.put("email", user.getEmail());
            users.put("uid",user.getUid());

            db.collection("Users")
                    .document(user.getEmail())
                    .set(users)
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

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        else {
            Log.d(TAG, "UpdateUIfailed");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        else {
            Log.d(TAG, "User not Signed in");
        }

    }
}