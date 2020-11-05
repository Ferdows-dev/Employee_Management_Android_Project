package com.learning.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button saveBtn,showBtn;
    EditText nameET,emailET,phoneET;
    String userName,email,phoneNumber;
    Employee employeeModel;

    List<Employee> employeeList;
    DatabaseAdapter dataBaseAdapter;
    DisplayAdapter displayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.phoneET);
        saveBtn = findViewById(R.id.save_button);
        showBtn = findViewById(R.id.show_button);

        dataBaseAdapter = new DatabaseAdapter(this);
        employeeList = dataBaseAdapter.getAllEmployee();
        displayAdapter = new DisplayAdapter(this,employeeList);

        employeeModel = (Employee) getIntent().getSerializableExtra("Employee");
        if (employeeModel !=null){
            saveBtn.setText("Update");
            nameET.setText(employeeModel.getUserName());
            emailET.setText(employeeModel.getEmail());
            phoneET.setText(employeeModel.getPhoneNumber()+"");
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (employeeModel !=null) {
                    String updatedName = nameET.getText().toString();
                    String updatedEmail = emailET.getText().toString();
                    String updatedPhone = phoneET.getText().toString();
                    int id = employeeModel.getId();

                    Employee updatedEmployee = new Employee(id, updatedName, updatedEmail, updatedPhone);
                   Boolean updatedStatus = dataBaseAdapter.updateFromDatabase(updatedEmployee);

//                   if (updatedStatus){
//                       Toast.makeText(MainActivity.this, "updated Succesfully", Toast.LENGTH_SHORT).show();
//                   }else Toast.makeText(MainActivity.this, "cant Update", Toast.LENGTH_SHORT).show();

                }
                    else
                    saveEmployee();
                emptyfield();
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

    }
    private void saveEmployee() {

        boolean error = false;

        userName = nameET.getText().toString().trim();
        if (userName.isEmpty()) {
            nameET.setError("Please enter your name here");
            error = true;
        } else if (userName.length() < 6) {
            nameET.setError("UserName is too short!");
            error = true;
        } else {
            error = false;


        }
        email = emailET.getText().toString().trim();
        if (email.isEmpty()) {
            emailET.setError("Email Cannot be empty");
            error = true;
        } else {
            error = false;
        }
        phoneNumber = phoneET.getText().toString().trim();
        if (phoneNumber.isEmpty()) {
            phoneET.setError("This field cannot be empty");
            error = true;
        } else if (phoneNumber.length() == 11) {
            if (phoneNumber.startsWith("013") || phoneNumber.startsWith("016") || phoneNumber.startsWith("017")
                    || phoneNumber.startsWith("018") || phoneNumber.startsWith("019")) {
                error = false;
            } else {
                phoneET.setError("Please Insert BD phone number");
                error = true;

            }
        } else {
            phoneET.setError("Phone number is not validated");
            error = true;
        }


        if (error) {
        Toast.makeText(MainActivity.this, "Data is not correcte!", Toast.LENGTH_SHORT).show();
    } else {
        Employee employee = new Employee(userName, email, phoneNumber);
        dataBaseAdapter.insertIntoDatabase(employee);
        Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

    }
}
    private void emptyfield() {
        nameET.setText("");
        phoneET.setText("");
        emailET.setText("");

    }

    public void callUser(){

    }

        }
