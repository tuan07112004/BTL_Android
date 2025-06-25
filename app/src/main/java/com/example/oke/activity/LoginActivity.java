package com.example.oke.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edmail, edpassword;
    private Button btnLogin;
    private TextView txtSignup;
    private FirebaseAuth mAuth;

    private static final String ADMIN_EMAIL = "admin@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edEmail = findViewById(R.id.edemailLg);
        EditText edPassword = findViewById(R.id.edpasswordLg);

        edEmail.setText("Le08@gmail.com");
        edPassword.setText("Le1111");


        edmail = findViewById(R.id.edemailLg);
        edpassword = findViewById(R.id.edpasswordLg);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);
        mAuth = FirebaseAuth.getInstance();

        // Nhận dữ liệu từ intent nếu có
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            edmail.setText(intent.getStringExtra("email"));
            edpassword.setText(intent.getStringExtra("password"));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edmail.getText().toString().trim();
                String password = edpassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
                    password = "admin123";
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Đăng Nhập Thành công", Toast.LENGTH_SHORT).show();

                                    // ➤ Phân biệt admin
                                    Intent nextIntent;
                                    if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
                                        nextIntent = new Intent(LoginActivity.this, AdminHome.class);
                                    } else {
                                        nextIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    }

                                    startActivity(nextIntent);
                                    finish();

                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Sai Tài Khoản Hoặc Mật khẩu!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        txtSignup.setOnClickListener(view -> {
            Intent in = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(in);
        });
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
