package com.example.kevin.wear_where.wear;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 9/29/2016.
 */

public class Clothing {

    // Initializing data structures to hold clothing types
    HashMap<Integer,ArrayList<String>> headWear = new HashMap<>();
    HashMap<Integer,ArrayList<String>> upperBody = new HashMap<>();
    HashMap<Integer,ArrayList<String>> lowerBody = new HashMap<>();
    HashMap<Integer,ArrayList<String>> shoes = new HashMap<>();

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

        freezing.add("fur hat");
        chilly.add("beanie");
        warm.add("snapback");
        warm.add("cool hairstyle");
        hot.add("sun hat");

        headWear.put(1,freezing);
        headWear.put(2,chilly);
        headWear.put(3,warm);
        headWear.put(4,hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();

        freezing.add("coat");
        chilly.add("jacket");
        chilly.add("hoodie");
        chilly.add("sweater");
        chilly.add("long sleeve");
        warm.add("graphic tee");
        warm.add("flannel shirt");
        warm.add("polo");
        hot.add("tanktop");

        upperBody.put(1,freezing);
        upperBody.put(2,chilly);
        upperBody.put(3,warm);
        upperBody.put(4,hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();

        freezing.add("thermal leggings under your warmest pants");
        freezing.add("snow pants");
        chilly.add("sweat pants");
        chilly.add("jeans");
        warm.add("shorts");
        warm.add("jeans");
        hot.add("short shorts!");

        lowerBody.put(1, freezing);
        lowerBody.put(2, chilly);
        lowerBody.put(3, warm);
        lowerBody.put(4, hot);

        freezing = new ArrayList<>();
        chilly = new ArrayList<>();
        warm = new ArrayList<>();
        hot = new ArrayList<>();

        freezing.add("snow boots");
        chilly.add("boots");
        warm.add("sneakers");
        hot.add("flip flops");

        shoes.put(1, freezing);
        shoes.put(2, chilly);
        shoes.put(3, warm);
        shoes.put(4, hot);


    }


    public ArrayList<String> getHeadWear(String temperature){
        float temp = Float.parseFloat(temperature);
        if (temp < 32){
            return headWear.get(1);
        }
        else if (temp >= 32 && temp < 55){
            return headWear.get(2);
        }
        else if (temp >= 55 && temp < 80){
            return headWear.get(3);
        }
        else if (temp >= 80){
            return headWear.get(4);
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getUpperBody(String temperature){
        float temp = Float.parseFloat(temperature);
        if (temp < 32){
            return upperBody.get(1);
        }
        else if (temp >= 32 && temp < 55){
            return upperBody.get(2);
        }
        else if (temp >= 55 && temp < 80){
            return upperBody.get(3);
        }
        else if (temp >= 80){
            return upperBody.get(4);
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getLowerBody(String temperature){
        float temp = Float.parseFloat(temperature);
        if (temp < 32){
            return lowerBody.get(1);
        }
        else if (temp >= 32 && temp < 55){
            return lowerBody.get(2);
        }
        else if (temp >= 55 && temp < 80){
            return lowerBody.get(3);
        }
        else if (temp >= 80){
            return lowerBody.get(4);
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getShoes(String temperature){
        float temp = Float.parseFloat(temperature);
        if (temp < 32){
            return shoes.get(1);
        }
        else if (temp >= 32 && temp < 55){
            return shoes.get(2);
        }
        else if (temp >= 55 && temp < 80){
            return shoes.get(3);
        }
        else if (temp >= 80){
            return shoes.get(4);
        }
        return new ArrayList<>();
    }

    //Scrap this method for the moment. Not in use.
    private String getClothing(String temperature, String clothing){
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

