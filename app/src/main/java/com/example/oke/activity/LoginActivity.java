package com.example.oke.activity;

<<<<<<< HEAD
//Firebase để xác thực người dùng bằng email & password.
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.EditText;
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
<<<<<<< HEAD


public class LoginActivity extends AppCompatActivity {
=======
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    private TextInputEditText edmail, edpassword;
    private Button btnLogin;
    private TextView txtSignup;
    private FirebaseAuth mAuth;

    private static final String ADMIN_EMAIL = "admin@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
=======
        EditText edEmail = findViewById(R.id.edemailLg);
        EditText edPassword = findViewById(R.id.edpasswordLg);

        edEmail.setText("Le08@gmail.com");
        edPassword.setText("Le1111");


>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        edmail = findViewById(R.id.edemailLg);
        edpassword = findViewById(R.id.edpasswordLg);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);
<<<<<<< HEAD
        mAuth = FirebaseAuth.getInstance(); //khoi tao firebase

        // SignUp xong và chuyển qua LoginActivity để tự động điền lại thông tin
=======
        mAuth = FirebaseAuth.getInstance();

        // Nhận dữ liệu từ intent nếu có
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            edmail.setText(intent.getStringExtra("email"));
            edpassword.setText(intent.getStringExtra("password"));
        }

<<<<<<< HEAD
        //bat su kien login
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edmail.getText().toString().trim();
                String password = edpassword.getText().toString().trim();

<<<<<<< HEAD
                // Kiểm tra trống
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
<<<<<<< HEAD
                // Kiểm tra định dạng email
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                if (!isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }
<<<<<<< HEAD
                // Nếu là admin thì gán mật khẩu mặc định
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
                    password = "admin123";
                }

<<<<<<< HEAD
                // Tiến hành đăng nhập Firebase
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
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
<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Sai Tài Khoản Hoặc Mật khẩu!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

<<<<<<< HEAD

        //Chuyển sang màn hình Đăng ký
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        txtSignup.setOnClickListener(view -> {
            Intent in = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(in);
        });
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
