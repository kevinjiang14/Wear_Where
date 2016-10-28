package com.example.kevin.wear_where.wear;



import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Calvin on 10/4/2016.
 */

public class Suggestion {

    Clothing _clothes = new Clothing();
    public Suggestion(Clothing clothes){
        _clothes = clothes;
    }

    /**
     * Enter temperature and condition to get clothing suggestions
     */
    public String getSuggestion(String temperature, String condition){
        float temp = Float.parseFloat(temperature);
        ArrayList<String> headwear;
        ArrayList<String> upperbody;
        ArrayList<String> lowerbody;
        if (temp < 32){
            headwear = _clothes.getHeadWear(temperature);
            upperbody = _clothes.getUpperBody(temperature);
            lowerbody = _clothes.getLowerBody(temperature);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's freezing today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "You should bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's freezing today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 32 && temp < 55){
            headwear = _clothes.getHeadWear(temperature);
            upperbody = _clothes.getUpperBody(temperature);
            lowerbody = _clothes.getLowerBody(temperature);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's chilly today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "You should bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's chilly today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 55 && temp < 75){
            headwear = _clothes.getHeadWear(temperature);
            upperbody = _clothes.getUpperBody(temperature);
            lowerbody = _clothes.getLowerBody(temperature);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's warm today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "You should bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's warm today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }
        else if (temp >= 75){
            headwear = _clothes.getHeadWear(temperature);
            upperbody = _clothes.getUpperBody(temperature);
            lowerbody = _clothes.getLowerBody(temperature);
            Collections.shuffle(headwear);
            Collections.shuffle(upperbody);
            Collections.shuffle(lowerbody);

            if (this.evaluateCondition(condition) != ""){
                return "It's hot today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+". "+ "You should bring "+this.evaluateCondition(condition);
            }
            else{
                return "It's hot today, wear a "+headwear.get(0)+", "+upperbody.get(0)+", and "+lowerbody.get(0)+".";
            }
        }

        return "";
    }

    private String evaluateCondition(String condition){
        if (condition.contains("Shower") || condition.contains("Rain") || condition.contains("Storm")){
            return "an umbrella";
        }
        else if (condition.contains("Sunny")){
            return "sunglasses";
        }
        return "";
    }
}