package com.example.learningmaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private ImageView male,female;
    private Button done;
    private EditText name,userid,email,phoneno,password;
    private String BloodGroup,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EpQmd1UQ4LCPsqG65sqHKSJ04Yk5V1e4VK631IKc")
                .clientKey("Vm0egZoggfRMZFVfXCaOkm3XfErzApy6BXqZjHUk")
                .server("https://parseapi.back4app.com")
                .build()
        );
//SPINNER CODE

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("O+");
        arrayList.add("O-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BloodGroup = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Blood Group: " + BloodGroup,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
//IMAGE CODE
        name=findViewById(R.id.signup_name);
        userid=findViewById(R.id.signup_userid);
        email=findViewById(R.id.signup_email);
        phoneno=findViewById(R.id.signup_phoneno);
        password=findViewById(R.id.signup_password);
        male=findViewById(R.id.maleimage);
        female=findViewById(R.id.femaleimage);
        done=findViewById(R.id.signup_done);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setImageResource(R.drawable.malepink);
                gender="male";
                female.setImageResource(R.drawable.femalegrey);
                //Toast.makeText(getApplicationContext(),"MALE",Toast.LENGTH_LONG).show();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setImageResource(R.drawable.femalepink);
                gender="female";
                male.setImageResource(R.drawable.malegrey);
                //Toast.makeText(getApplicationContext(),"FEMALE",Toast.LENGTH_LONG).show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText()==null || userid.getText()==null||email.getText()==null || phoneno.getText()==null|| password.getText()==null || gender==null)
                {
                    Toast.makeText(SignupActivity.this,"Fields Empty",Toast.LENGTH_LONG).show();
                }
                else {

                    ParseUser user = new ParseUser();
                    user.setUsername(userid.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.put("name", name.getText().toString());
                    user.put("phoneno", phoneno.getText().toString());
                    user.put("type", BloodGroup);
                    user.put("gender", gender);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent i = new Intent(SignupActivity.this, SliderActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(SignupActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

    }
}