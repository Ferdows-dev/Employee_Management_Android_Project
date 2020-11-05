package com.learning.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    DatabaseAdapter dataBaseAdapter;
    DisplayAdapter displayAdapter;
    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.LV);
        final ImageView emailIcon = findViewById(R.id.emailImgView);

        dataBaseAdapter = new DatabaseAdapter(this);
        employeeList = dataBaseAdapter.getAllEmployee();
        displayAdapter = new DisplayAdapter(this,employeeList);
        listView.setAdapter(displayAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = employeeList.get(position);
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.putExtra("Employee", employee);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        menu.setHeaderTitle("Delete Employee");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }
}