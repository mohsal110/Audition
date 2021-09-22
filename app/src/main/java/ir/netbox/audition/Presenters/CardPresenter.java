/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package ir.netbox.audition.Presenters;

import android.content.Context;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ir.netbox.audition.Models.Card;
import ir.netbox.audition.Models.Movie;
import ir.netbox.audition.Presenters.DataPresenters.DownloadImageTask;

class CardPresenter<T extends BaseCardView> extends Presenter {

    private static final String TAG = "CardPresenter";
    private final Context mContext;
    String imageURL;
    Drawable downloaded_image;
    DownloadImageTask downloadImageTask;

    public CardPresenter(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent) {

        ImageCardView imageCardView = new ImageCardView(parent.getContext());
        imageCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA);
        imageCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ACTIVATED);

        return new ViewHolder(imageCardView);
    }

    @Override
    public final void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Movie movie = (Movie) item;


        ImageCardView imageCardView = (ImageCardView) viewHolder.view;
        //imageCardView.setMainImage(movie.getImage());
        imageURL = movie.getPosterAddress();
        downloadImageTask=new DownloadImageTask(getContext(),imageCardView,imageURL);
        downloadImageTask.loadImage();



        //imageCardView.setMainImage(downloaded_image);
        imageCardView.setMainImageDimensions(313, 176);
        imageCardView.setTitleText(movie.getTitle());
        imageCardView.setContentText("more information...");
    }


    @Override
    public final void onUnbindViewHolder(ViewHolder viewHolder) {

    }



}
