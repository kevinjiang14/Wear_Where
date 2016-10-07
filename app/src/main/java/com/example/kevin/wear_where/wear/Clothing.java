package com.example.kevin.wear_where.wear;

import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Calvin on 9/29/2016.
 */

public class Clothing {

    HashMap<Integer,ArrayList<String>> headWear = new HashMap<>();
    HashMap<Integer,ArrayList<String>> upperBody = new HashMap<>();
    HashMap<Integer,ArrayList<String>> lowerBody = new HashMap<>();

    public Clothing(){
        /**
         *  Integer key of hash map determines the level of the temperature outside and type of clothing
         *  1- Freezing
         *  2- Chilly
         *  3- Warm
         *  4- Hot
         */

        ArrayList<String> freezing = new ArrayList<>();
        ArrayList<String> chilly = new ArrayList<>();
        ArrayList<String> warm = new ArrayList<>();
        ArrayList<String> hot = new ArrayList<>();

        freezing.add("Fur Hat");
        chilly.add("Beanie");
        warm.add("Snapback");
        hot.add("Sun Hat");

        headWear.put(1,freezing);
        headWear.put(2,chilly);
        headWear.put(3,warm);
        headWear.put(4,hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();

        freezing.add("Coat");
        chilly.add("Jacket");
        chilly.add("Hoodie");
        warm.add("Shirt");
        hot.add("TankTop");

        upperBody.put(1,freezing);
        upperBody.put(2,chilly);
        upperBody.put(3,warm);
        upperBody.put(4,hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();

        freezing.add("Thermal Leggings");
        freezing.add("Snow Pants");
        chilly.add("Sweat Pants");
        chilly.add("Jeans");
        warm.add("Shorts");
        hot.add("Short Shorts");
    }


    public HashMap<Integer,ArrayList<String>> getHeadWear(){
        return headWear;
    }

    public HashMap<Integer,ArrayList<String>> getUpperBody(){
        return upperBody;
    }

    public HashMap<Integer,ArrayList<String>> getLowerBody(){
        return lowerBody;
    }

    public String getClothing(String temperature, String clothing){
        /**
         * clothing input can only be the following:
         * 'headwear'
         * 'upperbody'
         * 'lowerbody'
         */
        int temp = Integer.parseInt(temperature);
        if (temp < 32){
            switch(clothing){
                case "headwear":
                    return headWear.get(1).get(0);

                case "upperbody":
                    return upperBody.get(1).get(0);

                case "lowerbody":
                    return lowerBody.get(1).get(0);
            }
        }
        else if (temp >= 32 && temp < 55){
            switch(clothing){
                case "headwear":
                    return headWear.get(2).get(0);

                case "upperbody":
                    return upperBody.get(2).get(0);

                case "lowerbody":
                    return lowerBody.get(2).get(0);
            }
        }
        else if (temp >= 55 && temp < 80){
            switch(clothing){
                case "headwear":
                    return headWear.get(3).get(0);

                case "upperbody":
                    return upperBody.get(3).get(0);

                case "lowerbody":
                    return lowerBody.get(3).get(0);
            }
        }
        else if (temp > 80){
            switch(clothing){
                case "headwear":
                    return headWear.get(4).get(0);

                case "upperbody":
                    return upperBody.get(4).get(0);

                case "lowerbody":
                    return lowerBody.get(4).get(0);
            }
        }
        return "Temperature out of bounds";
    }
}
