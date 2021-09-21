package ir.netbox.audition.Presenters;

import android.content.Context;
import android.view.ContextThemeWrapper;

import androidx.leanback.widget.ImageCardView;


import ir.netbox.audition.R;


public class ImageCardViewPresenter extends CardPresenter<ImageCardView> {

    public ImageCardViewPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public ImageCardViewPresenter(Context context) {
        this(context, R.style.DefaultCardTheme);
    }


}
