
package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.EntityClass.UserModel;

import java.util.ArrayList;
import java.util.List;

public class GetData extends AppCompatActivity {
    Button addEmployee;

    RecyclerView recyclerview;

    private List<UserModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        addEmployee=findViewById(R.id.addEmployee);
        getSupportActionBar( ).setTitle("Employee List Screen");
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar( ).setDisplayShowHomeEnabled(true);
        recyclerview = findViewById(R.id.recyclerview);
        addEmployee.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GetData.this,MainActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }

    private void getData() {
        list = new ArrayList<>();
        list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        recyclerview.setAdapter(new UserAdapter(getApplicationContext(), list, new UserAdapter.DeleteItemClicklistner() {
            @Override
            public void onItemDelete(int position, int id) {
                DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteData(id);
                getData();
            }
        }));
    }
}