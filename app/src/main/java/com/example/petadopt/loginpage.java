package com.example.petadopt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginpage extends AppCompatActivity {
    DatabaseReference root;
    FirebaseDatabase rootNode;
    TextView username, password;
    Button login_button;
    private Button button10;
    private DatabaseReference databaseReference;
    private FirebaseAuth m;

    //email checker if valid
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference();
        m = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_loginpage);
        getSupportActionBar().hide();//yes
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//yes
        username = findViewById(R.id.lgUser);
        password = findViewById(R.id.lgPass);
        button10 = (Button) findViewById(R.id.buttonnext);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_user();
                //opensurvey();
            }
        });
    }

    public void login_user() {
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        m.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        Toast.makeText(loginpage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        //redirect to user profile
                        startActivity(new Intent(loginpage.this, petselect.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(loginpage.this, "Please check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(loginpage.this, "Please input your correct login credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}