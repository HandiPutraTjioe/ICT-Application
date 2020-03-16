package com.english.englishproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgMyPhoto;
    Button btnPhoto;
    TextView txtFullname, txtUsername, txtMyPhone;

    ImageView imgBack, imgLogout;

    private String fullname, email, mynumberphone;

    static int preq_code = 1;
    static int request_code = 1;
    Uri pickedImgUri;

    private FirebaseAuth firebaseAuth;
    private RelativeLayout relativeLayout;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        imgMyPhoto = findViewById(R.id.img_Photo);
        txtFullname = findViewById(R.id.txt_fullname);
        txtUsername = findViewById(R.id.txt_username);
        txtMyPhone = findViewById(R.id.txt_myphone);
        btnPhoto = findViewById(R.id.btn_changePhoto);
        firebaseAuth = FirebaseAuth.getInstance();

        imgBack = findViewById(R.id.back);
//        imgLogout = findViewById(R.id.logout);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        imgLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.signOut();
//                Intent intent = new Intent(ProfileActivity.this, WelcomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        imgMyPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21){
                    checkAndRequest();
                } else {
                    openGallery();
                }
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname = txtFullname.getText().toString();
                updateuserinfo(fullname, pickedImgUri, firebaseAuth.getCurrentUser());
            }
        });

        updateheader();
    }

    public void updateheader() {
        ImageView navUserphoto = findViewById(R.id.img_Photo);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserphoto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){
            DocumentReference ref = db.collection("users").document(firebaseAuth.getUid());
            txtFullname = findViewById(R.id.txt_fullname);
            txtUsername = findViewById(R.id.txt_username);
            txtMyPhone = findViewById(R.id.txt_myphone);

            ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Log.d("LOGGER", "fullname " + documentSnapshot.get("name"));
                    Log.d("LOGGER", "email " + documentSnapshot.get("email"));
                    Log.d("LOGGER", "mynumberphone " + documentSnapshot.get("phone"));

                    fullname = documentSnapshot.getString("name");
                    email = documentSnapshot.getString("email");
                    mynumberphone = documentSnapshot.getString("phone");

                    txtFullname.setText(fullname);
                    txtUsername.setText(email);
                    txtMyPhone.setText(mynumberphone);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileActivity.this, "Error getting data, please try again", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @NonNull
    private void updateuserinfo(final String fullname, Uri pickedImgUri, final  FirebaseUser currentUser){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference img = storageReference.child(pickedImgUri.getLastPathSegment());
        img.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                img.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fullname)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    showMessage("Successfully Updated Image....");
                                    updateUI();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent Menu = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(Menu);
        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void openGallery() {
        Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, request_code);
    }

    private void checkAndRequest() {
        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        preq_code);
            }
        } else {
            openGallery();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == request_code && data != null){
            pickedImgUri = data.getData();
            imgMyPhoto.setImageURI(pickedImgUri);
        }
    }
}
