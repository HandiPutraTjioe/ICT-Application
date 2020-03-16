package com.english.englishproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser, edtPass;
    Button btnLogin;
    TextView txtRegister1;
    TextView txtregister, mtoggleview;

    private FirebaseAuth firebaseAuth;
    private RelativeLayout relativeLayout;
    private Intent OthersMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btnLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        relativeLayout = findViewById(R.id.rl_login);
        relativeLayout.setZ(999);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtUser.getText().toString();
                String password = edtPass.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "All Forms Must be Filled!!!", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Email Invalid!!", Toast.LENGTH_SHORT).show();
                    edtUser.setText("");
                    edtPass.setText("");
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                relativeLayout.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                relativeLayout.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this, "Username or Password is Invalid!!", Toast.LENGTH_SHORT).show();
                                edtUser.setText("");
                                edtPass.setText("");
                            }
                        }
                    });
                }
            }
        });

        TextView txtRegister1 = findViewById(R.id.txtRegister);
        txtRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent= new Intent(arg0.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        mtoggleview = findViewById(R.id.ToggleTextView);
        mtoggleview.setVisibility(View.GONE);
        edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtPass.getText().length() > 0){
                    mtoggleview.setVisibility(View.VISIBLE);
                } else {
                    mtoggleview.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mtoggleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtoggleview.getText() == "Show"){
                    mtoggleview.setText("Hide");
                    edtPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edtPass.setSelection(edtPass.length());
                } else {
                    mtoggleview.setText("Show");
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtPass.setSelection(edtPass.length());
                }
            }
        });

    }

    boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit??")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
