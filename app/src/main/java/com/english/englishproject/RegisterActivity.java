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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextView edtName, edtUser, edt_phone, edt_Pass, edtretypepass;
    TextView txtlogin;
    Button btn_Register;
    TextView mtoggleview1, mtoggleview2;

    private FirebaseAuth firebaseAuth;
    private RelativeLayout relativeLayout;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtlogin = findViewById(R.id.txtlogin1);
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        edtName = findViewById(R.id.edt_myname);
        edtUser = findViewById(R.id.edt_user);
        edt_phone = findViewById(R.id.edt_myphonenumber);
        edt_Pass = findViewById(R.id.edt_pass1);

        mtoggleview1 = findViewById(R.id.ToggleTextView2);
        mtoggleview1.setVisibility(View.GONE);
        edt_Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mtoggleview2 = findViewById(R.id.ToggleTextView3);
        mtoggleview2.setVisibility(View.GONE);
        edt_Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        edt_Pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Pass.getText().length() > 0){
                    mtoggleview1.setVisibility(View.VISIBLE);
                } else {
                    mtoggleview1.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mtoggleview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtoggleview1.getText() == "Show"){
                    mtoggleview1.setText("Hide");
                    edt_Pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mtoggleview1.setText("Show");
                    edt_Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        edtretypepass = findViewById(R.id.edt_repass);

        edtretypepass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtretypepass.getText().length() > 0){
                    mtoggleview2.setVisibility(View.VISIBLE);
                } else {
                    mtoggleview2.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mtoggleview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtoggleview2.getText() == "Show"){
                    mtoggleview2.setText("Hide");
                    edtretypepass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mtoggleview2.setText("Show");
                    edtretypepass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        btn_Register = findViewById(R.id.btnRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        relativeLayout = findViewById(R.id.rl_register);
        relativeLayout.setZ(999);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = edtName.getText().toString();
                final String email = edtUser.getText().toString();
                final String phone = edt_phone.getText().toString();
                final String password = edt_Pass.getText().toString();
                final String retypepassword = edtretypepass.getText().toString();

                if (fullname.equals("") || email.equals("") || phone.equals("") || password.equals("") || retypepassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "All forms must be filled!!!", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtUser.setText("");
                    edt_phone.setText("");
                    edt_Pass.setText("");
                    edtretypepass.setText("");
                } else if (!isValidEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "Email Invalid!!!", Toast.LENGTH_SHORT).show();
                    edtUser.requestFocus();
                    return;
                } else if (phone.length() != 12){
                    Toast.makeText(RegisterActivity.this, "Number must be 12 digits!!", Toast.LENGTH_SHORT).show();
                    edtUser.requestFocus();
                } else if (password.length() < 6 || password.length() > 20) {
                    Toast.makeText(RegisterActivity.this, "Password too Short!! and must greater then 6 - 20 characters!!", Toast.LENGTH_SHORT).show();
                    edt_Pass.requestFocus();
                    return;
                } else if (retypepassword.trim().length() < 6 || password.length() > 20){
                    Toast.makeText(RegisterActivity.this, "Password too Short!! and must greater then 6 - 20 characters!!", Toast.LENGTH_SHORT).show();
                    edtretypepass.requestFocus();
                    return;
                } else if (!password.equals(retypepassword)) {
                    Toast.makeText(RegisterActivity.this, "Both password Must be the same!", Toast.LENGTH_SHORT).show();
                    edtretypepass.requestFocus();
                    return;
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                relativeLayout.setVisibility(View.INVISIBLE);
                                Map<String, Object> docdata = new HashMap<>();
                                docdata.put("email", email);
                                docdata.put("name", fullname);
                                docdata.put("phone", phone);
                                docdata.put("password", password);
                                docdata.put("retypepassword", retypepassword);

                                String uid = firebaseAuth.getUid();

                                DocumentReference ref = db.collection("users").document(uid);
                                ref.set(docdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, "Thank You for registering...", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            } else {
                                relativeLayout.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                edtName.setText("");
                                edtUser.setText("");
                                edt_phone.setText("");
                                edt_Pass.setText("");
                                edtretypepass.setText("");
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });


    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
