package com.example.elephasvacation;

import android.provider.BaseColumns;

public class Database_hotel {

        public Database_hotel() { }

        public static class hotelEntry implements BaseColumns {
            public static final String TABLE_HOTEL = "hotel";
            public static final String Hotel_COLUMN_ID = "id";
            public static final String Hotel_COLUMN_NAME = "name";
            public static final String Hotel_COLUMN_Address = "address";
            public static final String Hotel_COLUMN_EMAIL = "email";
            public static final String Hotel_COLUMN_PHONE = "phone";
            public static final String Hotel_COLUMN_STARCLASS = "starclass";
            public static final String Hotel_COLUMN_SINGLE = "single";
            public static final String Hotel_COLUMN_DOUBLE= "double";
            public static final String Hotel_COLUMN_TRIPLE = "triple";
            public static final String Hotel_COLUMN_KING= "king";
            public static final String Hotel_COLUMN_QUARD = "quard";
            public static final String Hotel_COLUMN_QUEEN = "queen";
            public static final String Hotel_COLUMN_ROOMONLY= "roomonly";
            public static final String Hotel_COLUMN_BEDANDBREACKFAST = "bedandbreackfast";
            public static final String Hotel_COLUMN_FULLBOARD= "fullboard";
            public static final String Hotel_COLUMN_HALFBOARD= "halfboard";
        }

    }

