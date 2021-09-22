package ir.netbox.audition.Presenters.DataPresenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.leanback.widget.ImageCardView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ir.netbox.audition.Models.Movie;
import ir.netbox.audition.Models.MovieCards;

public class DownloadImageTask  {
    Context context;
    ImageCardView imageCardView;
    String imageURL;
    List<MovieCards> moviesList;
    MovieCards movieCards;

    public DownloadImageTask(Context context, ImageCardView imageCardView,String imageURL) {
        this.context=context;
        this.imageCardView=imageCardView;
        this.imageURL=imageURL;
        moviesList=new ArrayList<>();

    }
    public void loadImage(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                getImage();
            }
        }.start();
    }


    public void getImage(){
        try {

            URL url = new URL(imageURL);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            moviesList.add(new MovieCards(imageCardView,bmp));
            for(int i=0;i<=moviesList.size();++i){
                Bitmap imageBitmap=moviesList.get(i).getImageBitmap();
                ImageCardView bitmapImageCardView=moviesList.get(i).getImageCardView();
                onPostExecute(bitmapImageCardView,imageBitmap);
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage().toString());

        }
    }

    public void onPostExecute(ImageCardView bitmapImageCardView,Bitmap result) {
        Drawable drawableResult = new BitmapDrawable(context.getResources(), result);
        bitmapImageCardView.setMainImage(drawableResult);

        Toast.makeText(context, "Hellooooo", Toast.LENGTH_SHORT).show();
    }
}
