package com.example.elephasvacation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ViewHotelDetails extends AppCompatActivity {

    EditText name, address,
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

    Spinner spinner;

    Button update, delete;

    DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel_details);

        spinner = (Spinner) findViewById(R.id.spinnerHotel);

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

        update = (Button) findViewById(R.id.button);
        delete = (Button) findViewById(R.id.button2);

        db = new DBHelper(this);

        spinnerClass();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try{
                    ArrayList<Hotel> hotelModel = db.selectedHotels(spinner.getSelectedItem().toString());

                    for(int x=0 ; hotelModel.size()>x ; x++) {

                        name.setText(hotelModel.get(x).getName());
                        address.setText(hotelModel.get(x).getAddress());
                        email.setText(hotelModel.get(x).getEmail());
                        phone.setText(hotelModel.get(x).getPhone());
                        starclass.setText(hotelModel.get(x).getStarclass());
                        single.setText(hotelModel.get(x).getSingle());
                        Double.setText(hotelModel.get(x).getDouble());
                        triple.setText(hotelModel.get(x).getTriple());
                        king.setText(hotelModel.get(x).getKing());
                        quard.setText(hotelModel.get(x).getQuard());
                        queen.setText(hotelModel.get(x).getQueen());
                        roomonly.setText(hotelModel.get(x).getRoomonly());
                        bedandbreackfast.setText(hotelModel.get(x).getBedandbreackfast());
                        fullboard.setText(hotelModel.get(x).getFullboard());
                        halfboard.setText(hotelModel.get(x).getHalfboard());

                    }
                }catch (Exception e){

                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    Toast.makeText(ViewHotelDetails.this, s, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!spinner.getSelectedItem().toString().equals("")){

                    Hotel hotel = new Hotel();

                    final String uname = address.getText().toString();
                    final String uaddress = address.getText().toString();
                    final String uemail = email.getText().toString();
                    final String uphone = phone.getText().toString();
                    final String ustarclass = starclass.getText().toString();
                    final String usingle = single.getText().toString();
                    final String uDouble = Double.getText().toString();
                    final String utriple = triple.getText().toString();
                    final String uking = king.getText().toString();
                    final String uquard = quard.getText().toString();
                    final String uqueen = queen.getText().toString();
                    final String uroomonly = roomonly.getText().toString();
                    final String ubedandbreackfast = bedandbreackfast.getText().toString();
                    final String ufullboard = fullboard.getText().toString();
                    final String uhalfboard = halfboard.getText().toString();

                    if(db.updateHotel(spinner.getSelectedItem().toString(), uname,uaddress,  uemail,  uphone,
                            ustarclass,  usingle,  uDouble,  utriple,  uking,  uquard,
                            uqueen,  uroomonly,  ubedandbreackfast,  ufullboard,  uhalfboard)){
                        Toast.makeText(getApplicationContext(),"Successfully updated !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.deleteHotel(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Successfully deleted !",Toast.LENGTH_SHORT).show();
                        spinnerClass();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void spinnerClass(){

        ArrayList<Hotel> hotels = db.readAllHotels();

        final List<String> listHotels = new ArrayList<String>();

        for(int i=0;hotels.size()>i;i++){

            listHotels.add(Integer.toString(hotels.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listHotels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

    }

}