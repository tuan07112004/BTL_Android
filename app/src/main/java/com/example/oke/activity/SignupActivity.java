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
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private TextInputEditText edemail, edpassword, edrppassword;
    private Button btnsignup;
    private TextView txtLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edemail = findViewById(R.id.edemail);
        edpassword = findViewById(R.id.edpassword);
        edrppassword = findViewById(R.id.edrppassword);
        btnsignup = findViewById(R.id.btnsignup);
        txtLogin = findViewById(R.id.txtLogin);
<<<<<<< HEAD
        mAuth = FirebaseAuth.getInstance(); // Lấy đối tượng Firebase Auth


        //Sự kiện khi nhấn nút "Đăng ký"
=======
        mAuth = FirebaseAuth.getInstance();



>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edemail.getText().toString();
                String password = edpassword.getText().toString();
                String rppassword = edrppassword.getText().toString();
<<<<<<< HEAD
                // Kiểm tra đầu vào
                if(email.equals("")||password.equals("")||rppassword.isEmpty()){
                    Toast.makeText(SignupActivity.this,"vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(rppassword)){
                    Toast.makeText(SignupActivity.this,"mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(email)) {
                    Toast.makeText(SignupActivity.this,"Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
=======
                if(email.equals("")||password.equals("")||rppassword.isEmpty()){
                    Toast.makeText(SignupActivity.this, "vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(rppassword)){
                    Toast.makeText(SignupActivity.this,    "mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(email)) {
                    Toast.makeText(SignupActivity.this, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                    return;
                }
                if (password.length() < 6 || !Character.isUpperCase(password.charAt(0))) {
                    Toast.makeText(SignupActivity.this, "Mật khẩu phải có ít nhất 6 kí tự và viết hoa chữ cái đầu tiên!", Toast.LENGTH_SHORT).show();
                    return;
                }

<<<<<<< HEAD
                // Tạo tài khoản với Firebase
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent in = new Intent(SignupActivity.this, LoginActivity.class);
<<<<<<< HEAD
                                    in.putExtra("email",email); // Truyền email sang login
=======
                                    in.putExtra("email",email);
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                                    in.putExtra("password",password);
                                    startActivity(in);
                                    Toast.makeText(SignupActivity.this, "Đăng Kí Thành Công!",
                                            Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
<<<<<<< HEAD

        //Chuyển sang màn đăng nhập nếu đã có tài khoản
=======
>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });
<<<<<<< HEAD
=======

>>>>>>> 1d9901e39b48bf3acc1effefba9928e45ecd6962
    }
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
