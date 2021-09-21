package ir.netbox.audition.Presenters;

import android.content.Context;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import java.util.HashMap;


import ir.netbox.audition.Models.Movie;
import ir.netbox.audition.R;

import  ir.netbox.audition.Models.Movie;

public class CardPresenterSelector extends PresenterSelector {

    private final Context mContext;
    private final HashMap<Movie.Type, Presenter> presenters = new HashMap<Movie.Type, Presenter>();

    public CardPresenterSelector(Context context) {
        mContext = context;
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (!(item instanceof Movie)) throw new RuntimeException(
                String.format("The PresenterSelector only supports data items of type '%s'",
                        Movie.class.getName()));
        Movie movie = (Movie) item;
        Presenter presenter = presenters.get(movie.getType());
        if (presenter == null) {
            switch (movie.getType()) {

                case GAME: {
                    int themeResId = R.style.MovieCardSimpleTheme;
                    if (movie.getType() == Movie.Type.MOVIE_BASE) {
                        themeResId = R.style.MovieCardBasicTheme;
                    } else if (movie.getType() == Movie.Type.MOVIE_COMPLETE) {
                        themeResId = R.style.MovieCardCompleteTheme;
                    } else if (movie.getType() == Movie.Type.SQUARE_BIG) {
                        themeResId = R.style.SquareBigCardTheme;
                    } else if (movie.getType() == Movie.Type.GRID_SQUARE) {
                        themeResId = R.style.GridCardTheme;
                    } else if (movie.getType() == Movie.Type.GAME) {
                        themeResId = R.style.GameCardTheme;
                    }

                    presenter = new ImageCardViewPresenter(mContext, themeResId);
                    break;
                }

                default:
                    presenter = new ImageCardViewPresenter(mContext);
                    break;
            }
        }
        presenters.put(movie.getType(), presenter);
        return presenter;
    }

}
