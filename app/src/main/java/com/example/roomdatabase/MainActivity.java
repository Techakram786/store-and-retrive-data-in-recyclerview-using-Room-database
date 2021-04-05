package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.EntityClass.UserModel;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name, phoneno;
    TextView textView;
    Spinner spinner1,spinner2;
    Button save, getData;
    private int mYear;
    private int mMonth;
    private int mDay;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar( ).setTitle("Add Employee Screen");
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar( ).setDisplayShowHomeEnabled(true);
        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.phone);
        spinner1 = findViewById(R.id.spDepartment);
        spinner2 = findViewById(R.id.spDesignation);
        textView=findViewById(R.id.tvSetDate);
        save = findViewById(R.id.btn_save);
        //......

        ///....
       // getData = findViewById(R.id.btn_getData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData( ) != 0)
                {
                    Toast.makeText(MainActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show( );
                } else {
                    saveData( );
                    startActivity(new Intent(getApplicationContext( ), GetData.class));

                }
            }
        });

//        getData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), GetData.class));
//            }
//        });
        textView.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

    }

    private int validateData()
    {
        String name_txt = name.getText( ).toString( ).trim( );
        String phone_txt = phoneno.getText( ).toString( ).trim( );
        String depart = spinner1.getSelectedItem( ).toString( );
        String desig = spinner2.getSelectedItem( ).toString( );
        String tvdate = textView.getText( ).toString( );
        if(TextUtils.isEmpty(name_txt))
        {
            name.setError("name can not be empty");
            return 1;
        }
        if(TextUtils.isEmpty(phone_txt))
        {
            phoneno.setError("phone can not be empty");
            return 2;
        }
        if(phone_txt.length()>10)
        {
            phoneno.setError("phone can not be greater 10 digit");
            return 8;
        }
        if(phone_txt.length()<10)
        {
            phoneno.setError("phone can not be less 10 digit");
            return 7;
        }

        if(TextUtils.isEmpty(tvdate))
        {
            textView.setError("Field can not be empty");
            return 6;
        }
        else {
            return 0;
        }
    }


    private void saveData() {

        String name_txt = name.getText( ).toString( ).trim( );
        String phone_txt = phoneno.getText( ).toString( ).trim( );
        String depart = spinner1.getSelectedItem( ).toString( );
        String desig = spinner2.getSelectedItem( ).toString( );
        String tvdate = textView.getText( ).toString( );

            UserModel model = new UserModel( );
            model.setName(name_txt);
            model.setDesignation(desig);
            model.setDepartment(depart);
            model.setPhoneno(phone_txt);
            model.setTvdate(tvdate);
            DatabaseClass.getDatabase(getApplicationContext( )).getDao( ).insertAllData(model);

            name.setText("");
            phoneno.setText("");
            textView.setText("");
            //spinner1.setPrompt("");
            Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show( );


        }

    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofmonth) {
                monthofYear=monthofYear+1;
                textView.setText(dayofmonth+"/"+monthofYear+"/"+year);


            }
        },mYear,mMonth,mDay);
        datePickerDialog.show();
    }

}