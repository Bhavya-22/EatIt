package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eatit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText editPhone,name,editPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editPassword=(MaterialEditText)findViewById(R.id.editPassword);
        editPhone=(MaterialEditText)findViewById(R.id.editPhone);
        name=(MaterialEditText)findViewById(R.id.name);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);

        //Init Firebase
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mProgressDialog= new ProgressDialog(SignUp.this);
                mProgressDialog.setMessage("Please waiting...");
                mProgressDialog.show();

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Check if user already exists
                        if(snapshot.child(editPhone.getText().toString()).exists())
                        {
                            mProgressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Phone no already exist",Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                                mProgressDialog.dismiss();
                                User user =new User(name.getText().toString(),editPassword.getText().toString());
                                table_user.child(editPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this,"User added successfully",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
