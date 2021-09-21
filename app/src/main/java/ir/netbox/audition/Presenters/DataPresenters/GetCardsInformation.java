package ir.netbox.audition.Presenters.DataPresenters;

import ir.netbox.audition.Models.Movie;
import android.util.Log;
import android.widget.Toast;

import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import ir.netbox.audition.Models.CardRow;

public class GetCardsInformation extends BrowseSupportFragment {
    public String responseBody;
    ArrayObjectAdapter mAdapter;
    List<Movie> moviesList;
    List<Movie> topicsList;

    public GetCardsInformation(){

        moviesList=new ArrayList<>();
        topicsList=new ArrayList<>();
    }

    public GetCardsInformation(ArrayObjectAdapter mAdapter){
        this.mAdapter=mAdapter;
        moviesList=new ArrayList<>();
        topicsList=new ArrayList<>();
    }

    public void startThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                getInformation();
            }
        }.start();
    }

    public void getInformation(){
        HttpURLConnection connection;
        BufferedReader bufferedReader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {

            URL url=new URL("https://net-box.ir/apiv1/media/launcherupdateTEST/MainPage.json");
            connection=(HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status=connection.getResponseCode();

            if(status>299){
                bufferedReader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                while ((line = bufferedReader.readLine()) !=null){
                    responseContent.append(line);
                }

            }else {
                bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while ((line = bufferedReader.readLine()) !=null){
                    responseContent.append(line);
                }
                parseData(responseContent.toString());
                bufferedReader.close();
            }

            Log.d("result :",responseContent.toString());
            System.out.println(responseContent.toString());


        }catch (Exception e){
            Log.v("exception", String.valueOf(e.getCause()));
        }

    }
    public void parseData(String responseBody){


        try {
            JSONObject jsonObject=new JSONObject(responseBody);
            JSONArray contentJsonArray=jsonObject.getJSONArray("Content");
            JSONObject movieContentObject=contentJsonArray.getJSONObject(0);
            JSONArray moviesJsonArray=movieContentObject.getJSONArray("Content");
            for(int i=0;i<=contentJsonArray.length();++i){
                JSONObject movieObject=moviesJsonArray.getJSONObject(i);
                String title=movieObject.getString("Title");
                String poster=movieObject.getString("Poster");
                int id=movieObject.getInt("ID");
                int sort=movieObject.getInt("Sort");
                mAdapter.add(new Movie(title,poster,id,sort,Movie.Type.SQUARE_BIG));
            }

            JSONObject topicsContentObject=contentJsonArray.getJSONObject(1);
            JSONArray topicsJsonArray=topicsContentObject.getJSONArray("Content");
            for(int i=0;i<=contentJsonArray.length();++i){
                JSONObject movieObject=topicsJsonArray.getJSONObject(i);
                String title=movieObject.getString("Title");
                String poster=movieObject.getString("Poster");
                int id=movieObject.getInt("ID");
                int sort=movieObject.getInt("Sort");
                mAdapter.add(new Movie(title,poster,id,sort,Movie.Type.SQUARE_BIG));
            }



            ArrayObjectAdapter windowAdapter=new ArrayObjectAdapter(new ListRowPresenter());
            windowAdapter.add(new ListRow(mAdapter));
            windowAdapter.add(new ListRow(mAdapter));

            setAdapter(windowAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
