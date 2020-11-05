package com.learning.employeemanagement;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

class DisplayAdapter extends BaseAdapter {
    Context context;
    List<Employee> employeeList;
    private int PHONE_PERMISSION_CODE = 03;

    public DisplayAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.employee_list_layout,null);

        TextView userNameTV = customView.findViewById(R.id.userNameTV);
        final ImageView callIcon = customView.findViewById(R.id.callImgView);
        final ImageView emailIcon = customView.findViewById(R.id.emailImgView);


        userNameTV.setText(employeeList.get(position).getUserName());

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = employeeList.get(position).getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("phone : "+phoneNumber));
                context.startActivity(Intent.createChooser(intent,"Because emulator has no play store!"));

            }
        });

        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("Text/Plain");
                intent.putExtra(Intent.EXTRA_EMAIL,"ferdousshawon36@gmail.com");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                context.startActivity(intent);




            }
        });

        return customView;
    }
}
