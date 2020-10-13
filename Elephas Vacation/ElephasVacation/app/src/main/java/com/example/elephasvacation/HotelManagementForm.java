package com.example.elephasvacation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HotelManagementForm extends AppCompatActivity {

    EditText name,
            address,
            email,
            phone,
            starclass,
            single,
            Double,
            triple,
            king,
            quard,
            queen,
            roomonly,
            bedandbreackfast,
            fullboard,
            halfboard;

    Button buttonSave;

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_management_form);

        name = (EditText) findViewById(R.id.editText);
        address = (EditText) findViewById(R.id.editText2);
        email= (EditText) findViewById(R.id.editText3);
        phone = (EditText) findViewById(R.id.editText4);
        starclass = (EditText) findViewById(R.id.editText5);
        single = (EditText) findViewById(R.id.editText6);
        Double = (EditText) findViewById(R.id.editText7);
        triple = (EditText) findViewById(R.id.editText8);
        king = (EditText) findViewById(R.id.editText10);
        quard = (EditText) findViewById(R.id.editText9);
        queen = (EditText) findViewById(R.id.editText11);
        roomonly = (EditText) findViewById(R.id.editText12);
        bedandbreackfast = (EditText) findViewById(R.id.editText16);
        fullboard = (EditText) findViewById(R.id.editText15);
        halfboard = (EditText) findViewById(R.id.editText14);

        db = new DBHelper(this);

        buttonSave = (Button) findViewById(R.id.button);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    Hotel hotel = new Hotel();

                    hotel.setName(name.getText().toString());
                    hotel.setAddress(address.getText().toString());
                    hotel.setEmail(email.getText().toString());
                    hotel.setPhone(phone.getText().toString());
                    hotel.setStarclass(starclass.getText().toString());
                    hotel.setSingle(single.getText().toString());
                    hotel.setDouble(Double.getText().toString());
                    hotel.setTriple(triple.getText().toString());
                    hotel.setKing(king.getText().toString());
                    hotel.setQuard(quard.getText().toString());
                    hotel.setQueen(queen.getText().toString());
                    hotel.setRoomonly(roomonly.getText().toString());
                    hotel.setBedandbreackfast(bedandbreackfast.getText().toString());
                    hotel.setFullboard(fullboard.getText().toString());
                    hotel.setHalfboard(halfboard.getText().toString());

                    boolean isInserted = db.addHotelDetails(hotel);

                    if (isInserted) {
                        Toast.makeText(HotelManagementForm.this, "Payment successful", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(HotelManagementForm.this, HotelMangement.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(HotelManagementForm.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(HotelManagementForm.this, "spnot working", Toast.LENGTH_SHORT).show();
                }

            }

        });}}



