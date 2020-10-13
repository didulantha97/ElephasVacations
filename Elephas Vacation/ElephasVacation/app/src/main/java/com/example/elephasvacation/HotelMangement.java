package com.example.elephasvacation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HotelMangement extends AppCompatActivity {

    Button addHotel, viewHotel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_mangement);

        addHotel = (Button)findViewById(R.id.button3);
        viewHotel = (Button)findViewById(R.id.button4);

        addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent = new Intent(HotelMangement.this, HotelManagementForm.class);
                startActivity(hotelintent);
            }
        });

        viewHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelintent2 = new Intent(HotelMangement.this, ViewHotelDetails.class);
                startActivity(hotelintent2);
            }
        });

    }


}
