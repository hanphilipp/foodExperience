package api;

import api.food.FoodResult;
import api.sportsCalc.Activity;
import api.sportsCalc.ActivityModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class NetClientGet {

    public static String getAPIData(String link) throws Exception{
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String resultString = "";
        String flag = br.readLine();


        while (flag != null) {
            resultString += flag;
            flag = br.readLine();
        }

        conn.disconnect();

        return resultString;
    }


    public static FoodResult requestRecipes(String link){

        try {
            String resultString = getAPIData(link);

            return new ObjectMapper().readValue(resultString, FoodResult.class);


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Activity> requestActivities(String link){

        try {
            String resultString = getAPIData(link);

            return  new ObjectMapper().readValue(resultString, ActivityModel.class).activities;


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
