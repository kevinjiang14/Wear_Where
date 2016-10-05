package com.example.kevin.wear_where.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kevin Jiang on 10/4/16.
 */

public class ImageLoaderAST extends AsyncTask<Void, Void, Bitmap>{

    private String imagelink;
    private ImageView imageView;

    public ImageLoaderAST(String link, ImageView view){
        imagelink = link;
        imageView = view;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL request = new URL(imagelink);

            HttpURLConnection urlConnection = (HttpURLConnection)request.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result){
        super.onPostExecute(result);
        imageView.setImageBitmap(result);
    }
}
