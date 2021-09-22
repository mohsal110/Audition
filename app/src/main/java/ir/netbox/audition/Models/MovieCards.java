package ir.netbox.audition.Models;

import android.graphics.Bitmap;

import androidx.leanback.widget.ImageCardView;

public class MovieCards {

    ImageCardView imageCardView;
    Bitmap imageBitmap;
    String imageUrl;
    String title;
    int type;

    public MovieCards(ImageCardView imageCardView, Bitmap imageBitmap) {
        this.imageCardView = imageCardView;
        this.imageBitmap = imageBitmap;
    }

    public MovieCards(ImageCardView imageCardView, Bitmap imageBitmap, String imageUrl, String title, int type) {
        this.imageCardView = imageCardView;
        this.imageBitmap = imageBitmap;
        this.imageUrl = imageUrl;
        this.title = title;
        this.type = type;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageCardView getImageCardView() {
        return imageCardView;
    }

    public void setImageCardView(ImageCardView imageCardView) {
        this.imageCardView = imageCardView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
