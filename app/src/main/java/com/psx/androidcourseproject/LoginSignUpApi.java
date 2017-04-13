package com.psx.androidcourseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/*
public class LoginSignUpApi extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks  {

    private FirebaseAuth firebaseAuth;
    private GoogleApiClient apiClient;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up_api);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .addOnConnectionFailedListener(this)
                .build();
        firebaseAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button :
                // function  call to sign in/ sign up
                signIn();
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(intent,9001);
    }

    private void firebaseAuthWithGoogle (GoogleSignInAccount account){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(LoginSignUpApi.this,"Authentication Failed. Please Try Agagin.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // task is successful
                            Toast.makeText(LoginSignUpApi.this,"Authneticated Successfully",Toast.LENGTH_SHORT).show();
                            // redirect to main Activity
                            startActivity(new Intent(LoginSignUpApi.this,MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9001){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()){
                // signed in successfully
                GoogleSignInAccount account = googleSignInResult.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else {
                Toast.makeText(LoginSignUpApi.this,"Authentication error. Please try again",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
*/
public class LoginSignUpApi {
 // To be implemented
}