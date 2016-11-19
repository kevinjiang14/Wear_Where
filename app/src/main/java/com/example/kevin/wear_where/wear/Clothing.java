package com.example.kevin.wear_where.wear;

import android.content.res.AssetManager;

import com.example.kevin.wear_where.MainActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 9/29/2016.
 */

public class Clothing {

    //Holds gender -> Clothes -> body part -> temperature range of clothing
    HashMap<String,ArrayList<HashMap<Integer,ArrayList<String>>>> Factory = new HashMap<>();

    //Holds Clothes -> body part -> temperature range of clothing
    ArrayList<HashMap<Integer,ArrayList<String>>> Clothes;

    //Holds body part -> temperature range of clothing
    private HashMap<Integer,ArrayList<String>> upperBody;
    private HashMap<Integer,ArrayList<String>> lowerBody;
    private HashMap<Integer,ArrayList<String>> overall;
    private HashMap<Integer,ArrayList<String>> shoeWear;
    private HashMap<Integer,ArrayList<String>> miscellaneous;

    //Holds temperature range of clothing
    private ArrayList<String> freezing;
    private ArrayList<String> chilly;
    private ArrayList<String> warm;
    private ArrayList<String> hot;

    public String gender;

    //users preferences of temperature, default set as infinity
    //float thetaFreezing = Float.POSITIVE_INFINITY;
    public float lowerChilly = Float.POSITIVE_INFINITY;
    public float upperChilly = Float.POSITIVE_INFINITY;
    public float lowerWarm = Float.POSITIVE_INFINITY;
    public float upperWarm = Float.POSITIVE_INFINITY;
    //float thetaHot = Float.POSITIVE_INFINITY;

    public Clothing(InputStream firstFile, InputStream secondFile){

        ArrayList<InputStream> files = new ArrayList<>();
        files.add(firstFile);
        files.add(secondFile);
        for (int i = 0; i < files.size(); i++){
            Clothes = new ArrayList<>();
            try {
                InputStreamReader file = new InputStreamReader(files.get(i));
                BufferedReader br = new BufferedReader(file);
                String myLine = br.readLine();
                if (myLine.contains("Female") || myLine.contains("female")){
                    gender = "female";
                }
                else if (myLine.contains("Male") || myLine.contains("male")){
                    gender = "male";
                }
                else{
                    gender = "male";
                }


                while ((myLine = br.readLine()) != null){
                    if (myLine.contains("Upper Body Wear")){
                        upperBody = new HashMap<>();
                        freezing = new ArrayList<>();
                        chilly = new ArrayList<>();
                        warm = new ArrayList<>();
                        hot = new ArrayList<>();

                        while (!(myLine = br.readLine()).contains("chilly")){
                            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("freezing")){
                                freezing.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("warm")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                chilly.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("hot")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                warm.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("--")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                hot.add(myLine);
                            }
                        }

                        upperBody.put(1, freezing);
                        upperBody.put(2, chilly);
                        upperBody.put(3, warm);
                        upperBody.put(4, hot);
                        Clothes.add(upperBody);
                    }


                    if (myLine.contains("Lower Body Wear")){
                        lowerBody = new HashMap<>();
                        freezing = new ArrayList<>();
                        chilly = new ArrayList<>();
                        warm = new ArrayList<>();
                        hot = new ArrayList<>();

                        while (!(myLine = br.readLine()).contains("chilly")){
                            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("freezing")){
                                freezing.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("warm")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                chilly.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("hot")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                warm.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("--")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                hot.add(myLine);
                            }
                        }
                        lowerBody.put(1, freezing);
                        lowerBody.put(2, chilly);
                        lowerBody.put(3, warm);
                        lowerBody.put(4, hot);
                        Clothes.add(lowerBody);
                    }

                    if (myLine.contains("Overall Wear")){
                        overall = new HashMap<>();
                        freezing = new ArrayList<>();
                        chilly = new ArrayList<>();
                        warm = new ArrayList<>();
                        hot = new ArrayList<>();
                        while (!(myLine = br.readLine()).contains("chilly")){
                            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("freezing")){
                                freezing.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("warm")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                chilly.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("hot")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                warm.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("--")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                hot.add(myLine);
                            }
                        }
                        overall.put(1, freezing);
                        overall.put(2, chilly);
                        overall.put(3, warm);
                        overall.put(4, hot);
                        Clothes.add(overall);
                    }


                    if (myLine.contains("Footwear")){
                        shoeWear = new HashMap<>();
                        freezing = new ArrayList<>();
                        chilly = new ArrayList<>();
                        warm = new ArrayList<>();
                        hot = new ArrayList<>();
                        while (!(myLine = br.readLine()).contains("chilly")){
                            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("freezing")){
                                freezing.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("warm")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                chilly.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("hot")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                warm.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("--")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                hot.add(myLine);
                            }
                        }
                        shoeWear.put(1, freezing);
                        shoeWear.put(2, chilly);
                        shoeWear.put(3, warm);
                        shoeWear.put(4, hot);
                        Clothes.add(shoeWear);
                    }

                    if (myLine.contains("Miscellaneous")){
                        miscellaneous = new HashMap<>();
                        freezing = new ArrayList<>();
                        chilly = new ArrayList<>();
                        warm = new ArrayList<>();
                        hot = new ArrayList<>();
                        while (!(myLine = br.readLine()).contains("chilly")){
                            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("freezing")){
                                freezing.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("warm")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                chilly.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("hot")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                warm.add(myLine);
                            }
                        }

                        while (!(myLine = br.readLine()).contains("--")){
                            if (!myLine.contains("{") && !myLine.contains("}")){
                                hot.add(myLine);
                            }
                        }
                        miscellaneous.put(1, freezing);
                        miscellaneous.put(2, chilly);
                        miscellaneous.put(3, warm);
                        miscellaneous.put(4, hot);
                        Clothes.add(miscellaneous);
                    }

                    Factory.put(gender, Clothes);
                }
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


    }


    /**
     * 	First get gender of clothes from Factory, then get body part, finally
     * 	the temperature range.
     *
     * 	For getting body part.
     * 	0- upper body
     * 	1- lower body
     * 	2- overall
     * 	3- shoes
     * 	4- miscellaneous
     *
     * 	For getting clothing of certain temperature range
     *  1- Freezing
     *  2- Chilly
     *  3- Warm
     *  4- Hot
     */

    public ArrayList<String> getUpperBody(String temperature){
        float temp = Float.parseFloat(temperature);
        if (lowerChilly != Float.POSITIVE_INFINITY &&
                upperChilly != Float.POSITIVE_INFINITY &&
                lowerWarm != Float.POSITIVE_INFINITY &&
                upperWarm != Float.POSITIVE_INFINITY){

            if (temp < lowerChilly){
                return Factory.get(gender).get(0).get(1);
            }
            else if (temp >= lowerChilly && temp < upperChilly){
                return Factory.get(gender).get(0).get(2);
            }
            else if (temp >= lowerWarm && temp < upperWarm){
                return Factory.get(gender).get(0).get(3);
            }
            else if (temp > upperWarm){
                return Factory.get(gender).get(0).get(4);
            }
        }
        else {
            if (temp < 40) {
                return Factory.get(gender).get(0).get(1);
            } else if (temp >= 40 && temp < 60) {
                return Factory.get(gender).get(0).get(2);
            } else if (temp >= 60 && temp < 80) {
                return Factory.get(gender).get(0).get(3);
            } else if (temp >= 80) {
                return Factory.get(gender).get(0).get(4);
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getLowerBody(String temperature){
        float temp = Float.parseFloat(temperature);
        if (lowerChilly != Float.POSITIVE_INFINITY &&
                upperChilly != Float.POSITIVE_INFINITY &&
                lowerWarm != Float.POSITIVE_INFINITY &&
                upperWarm != Float.POSITIVE_INFINITY){

            if (temp < lowerChilly){
                return Factory.get(gender).get(1).get(1);
            }
            else if (temp >= lowerChilly && temp < upperChilly){
                return Factory.get(gender).get(1).get(2);
            }
            else if (temp >= lowerWarm && temp < upperWarm){
                return Factory.get(gender).get(1).get(3);
            }
            else if (temp > upperWarm){
                return Factory.get(gender).get(1).get(4);
            }
        }
        else {
            if (temp < 40) {
                return Factory.get(gender).get(1).get(1);
            } else if (temp >= 40 && temp < 60) {
                return Factory.get(gender).get(1).get(2);
            } else if (temp >= 60 && temp < 80) {
                return Factory.get(gender).get(1).get(3);
            } else if (temp >= 80) {
                return Factory.get(gender).get(1).get(4);
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getOveralls(String temperature){
        float temp = Float.parseFloat(temperature);
        if (lowerChilly != Float.POSITIVE_INFINITY &&
                upperChilly != Float.POSITIVE_INFINITY &&
                lowerWarm != Float.POSITIVE_INFINITY &&
                upperWarm != Float.POSITIVE_INFINITY){

            if (temp < lowerChilly){
                return Factory.get(gender).get(2).get(1);
            }
            else if (temp >= lowerChilly && temp < upperChilly){
                return Factory.get(gender).get(2).get(2);
            }
            else if (temp >= lowerWarm && temp < upperWarm){
                return Factory.get(gender).get(2).get(3);
            }
            else if (temp > upperWarm){
                return Factory.get(gender).get(2).get(4);
            }
        }
        else {
            if (temp < 40) {
                return Factory.get(gender).get(2).get(1);
            } else if (temp >= 40 && temp < 60) {
                return Factory.get(gender).get(2).get(2);
            } else if (temp >= 60 && temp < 80) {
                return Factory.get(gender).get(2).get(3);
            } else if (temp >= 80) {
                return Factory.get(gender).get(2).get(4);
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getShoes(String temperature){
        float temp = Float.parseFloat(temperature);
        if (lowerChilly != Float.POSITIVE_INFINITY &&
                upperChilly != Float.POSITIVE_INFINITY &&
                lowerWarm != Float.POSITIVE_INFINITY &&
                upperWarm != Float.POSITIVE_INFINITY){

            if (temp < lowerChilly){
                return Factory.get(gender).get(3).get(1);
            }
            else if (temp >= lowerChilly && temp < upperChilly){
                return Factory.get(gender).get(3).get(2);
            }
            else if (temp >= lowerWarm && temp < upperWarm){
                return Factory.get(gender).get(3).get(3);
            }
            else if (temp > upperWarm){
                return Factory.get(gender).get(3).get(4);
            }
        }
        else {
            if (temp < 40) {
                return Factory.get(gender).get(3).get(1);
            } else if (temp >= 40 && temp < 60) {
                return Factory.get(gender).get(3).get(2);
            } else if (temp >= 60 && temp < 80) {
                return Factory.get(gender).get(3).get(3);
            } else if (temp >= 80) {
                return Factory.get(gender).get(3).get(4);
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getMisc(String temperature){
        float temp = Float.parseFloat(temperature);
        if (lowerChilly != Float.POSITIVE_INFINITY &&
                upperChilly != Float.POSITIVE_INFINITY &&
                lowerWarm != Float.POSITIVE_INFINITY &&
                upperWarm != Float.POSITIVE_INFINITY){

            if (temp < lowerChilly){
                return Factory.get(gender).get(4).get(1);
            }
            else if (temp >= lowerChilly && temp < upperChilly){
                return Factory.get(gender).get(4).get(2);
            }
            else if (temp >= lowerWarm && temp < upperWarm){
                return Factory.get(gender).get(4).get(3);
            }
            else if (temp > upperWarm){
                return Factory.get(gender).get(4).get(4);
            }
        }
        else {
            if (temp < 40) {
                return Factory.get(gender).get(4).get(1);
            } else if (temp >= 40 && temp < 60) {
                return Factory.get(gender).get(4).get(2);
            } else if (temp >= 60 && temp < 80) {
                return Factory.get(gender).get(4).get(3);
            } else if (temp >= 80) {
                return Factory.get(gender).get(4).get(4);
            }
        }
        return new ArrayList<>();
    }


}
