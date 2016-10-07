package com.example.kevin.wear_where.wear;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Calvin on 10/4/2016.
 */

public class Suggestion {

    Clothing _clothes;
    public Suggestion(Clothing clothes){
        _clothes = clothes;
    }

    public String suggest(String temperature, String condition){
        int temp = Integer.parseInt(temperature);
        ArrayList<String> headwear;
        ArrayList<String> upperbody;
        ArrayList<String> lowerbody;
        if (temp < 32){
            headwear = _clothes.getHeadWear().get(1);
            upperbody = _clothes.getUpperBody().get(1);
            lowerbody = _clothes.getLowerBody().get(1);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's freezing today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "Bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's freezing today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 32 && temp < 55){
            headwear = _clothes.getHeadWear().get(2);
            upperbody = _clothes.getUpperBody().get(2);
            lowerbody = _clothes.getLowerBody().get(2);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's chilly today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "Bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's chilly today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 55 && temp < 75){
            headwear = _clothes.getHeadWear().get(3);
            upperbody = _clothes.getUpperBody().get(3);
            lowerbody = _clothes.getLowerBody().get(3);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's warm today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "Bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's warm today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 75){
            headwear = _clothes.getHeadWear().get(4);
            upperbody = _clothes.getUpperBody().get(4);
            lowerbody = _clothes.getLowerBody().get(4);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's hot today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "Bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's hot today, put on some "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }

        return "";
    }

    private String evaluateCondition(String condition){
        if (condition.contains("shower") || condition.contains("rain") || condition.contains("storm")){
            return "an umbrella";
        }
        else if (condition.contains("Sunny")){
            return "sunglasses";
        }
        return "";
    }
}