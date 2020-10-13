package com.example.elephasvacation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hasinthiDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database_hotel.hotelEntry.TABLE_HOTEL + " (" +
                    Database_hotel.hotelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_NAME + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_Address + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_PHONE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_KING + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_QUARD + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD + " TEXT," +
                    Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD + " TEXT)";


    private String DROP_HOTEL_TABLE = "DROP TABLE IF EXISTS " + Database_hotel.hotelEntry.TABLE_HOTEL;

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HOTEL_TABLE);
        onCreate(db);
    }


    public boolean addHotelDetails (Hotel hotel) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_NAME, hotel.getName());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_Address, hotel.getAddress());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL, hotel.getEmail());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_PHONE, hotel.getPhone());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS, hotel.getStarclass());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE, hotel.getSingle());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE, hotel.getDouble());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE, hotel.getTriple());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_KING, hotel.getKing());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUARD, hotel.getQuard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN, hotel.getQueen());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY, hotel.getRoomonly());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST, hotel.getBedandbreackfast());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD, hotel.getFullboard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD, hotel.getHalfboard());

        long newRowId = db.insert(Database_hotel.hotelEntry.TABLE_HOTEL, null, values);


        // Insert the new row, returning the primary key value of the new row
        return true;
    }

    //read Hotel ID to spinner
    public ArrayList<Hotel> readAllHotels () {

        ArrayList<Hotel> models = new ArrayList<>();

        SQLiteDatabase score = this.getReadableDatabase();

        Cursor results = score.rawQuery("select * from " + Database_hotel.hotelEntry.TABLE_HOTEL, null);

        results.moveToFirst();

        while (!results.isAfterLast()) {

            Hotel hotel = new Hotel();

            hotel.setID(results.getInt(results.getColumnIndex(Database_hotel.hotelEntry._ID)));

            //driver.setID(results.getInt(results.getColumnIndex(Database_driver.driverEntry._ID)));

            models.add(hotel);
            results.moveToNext();
        }

        return models;

    }

    //read  Hotel data where id equals in spinner
    public ArrayList<Hotel> selectedHotels (String id) {

        ArrayList<Hotel> models = new ArrayList<>();

        SQLiteDatabase scoredb = this.getReadableDatabase();
        Cursor results = scoredb.rawQuery("select * from " + Database_hotel.hotelEntry.TABLE_HOTEL + " where " + Database_hotel.hotelEntry._ID + " = '" + id + "'", null);
        results.moveToFirst();
        while (!results.isAfterLast()) {
            try {
                Hotel model = new Hotel();
                model.setName(results.getString(1));
                model.setAddress(results.getString(2));
                model.setEmail(results.getString(3));
                model.setPhone(results.getString(4));
                model.setStarclass(results.getString(5));
                model.setSingle(results.getString(6));
                model.setDouble(results.getString(7));
                model.setTriple(results.getString(8));
                model.setKing(results.getString(9));
                model.setQuard(results.getString(10));
                model.setQueen(results.getString(11));
                model.setRoomonly(results.getString(12));
                model.setBedandbreackfast(results.getString(13));
                model.setFullboard(results.getString(14));
                model.setHalfboard(results.getString(15));

                models.add(model);
                results.moveToNext();

            } catch (SQLException e) {
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                System.out.println(s);

            }

        }
        return models;
    }


    //Update Hotel
    public boolean updateHotel (String id, String name, String address, String email, String phone, String starclass, String single,
                                String aDouble, String triple, String king, String quard, String queen, String roomonly, String bedandbreackfast,
                                String fullboard, String halfboard) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_NAME, name);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_Address, address);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL, email);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_PHONE, phone);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS, starclass);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE, single);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE, aDouble);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE, triple);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_KING, king);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUARD, quard);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN, queen);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY, roomonly);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST, bedandbreackfast);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD, fullboard);
        contentValues.put(Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD, halfboard);


        long res = db.update(Database_hotel.hotelEntry.TABLE_HOTEL, contentValues, Database_hotel.hotelEntry._ID + " = '" + id + "'", null);

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    //delete Hotel
    public boolean deleteHotel (String id) {

        SQLiteDatabase db = getReadableDatabase();

        long res = db.delete(Database_hotel.hotelEntry.TABLE_HOTEL, Database_hotel.hotelEntry._ID + " = '" + id + "'", null);

        db.close();

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }
}

/*
    public boolean addHotelDetails(Hotel hotel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_NAME, hotel.getName());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_Address, hotel.getAddress());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_EMAIL, hotel.getEmail());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_PHONE, hotel.getPhone());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_STARCLASS, hotel.getStarclass());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_SINGLE, hotel.getSingle());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_DOUBLE, hotel.getDouble());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_TRIPLE, hotel.getTriple());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_KING, hotel.getKing());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUARD, hotel.getQuard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_QUEEN, hotel.getQueen());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_ROOMONLY, hotel.getRoomonly());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_BEDANDBREACKFAST, hotel.getBedandbreackfast());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_FULLBOARD, hotel.getFullboard());
        values.put(Database_hotel.hotelEntry.Hotel_COLUMN_HALFBOARD, hotel.getHalfboard());


        long newRowId = db.insert(Database_hotel.hotelEntry.TABLE_HOTEL, null, values);

        db.close();

        return true;

    }



*/