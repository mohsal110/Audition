package ir.netbox.audition.Views;

import android.os.Bundle;
import android.os.Handler;

import androidx.leanback.app.VerticalGridFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.VerticalGridPresenter;

import com.google.gson.Gson;

import ir.netbox.audition.Models.CardRow;
import ir.netbox.audition.Presenters.CardPresenterSelector;
import ir.netbox.audition.Presenters.DataPresenters.GetCardsInformation;
import ir.netbox.audition.Presenters.utils.Utils;
import ir.netbox.audition.R;

public class MainFragment extends VerticalGridFragment {

    private static final int COLUMNS = 3;
    private static final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_MEDIUM;

    private ArrayObjectAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Net-Box Audition");
        setupRowAdapter();
    }

    private void setupRowAdapter() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter(ZOOM_FACTOR);
        gridPresenter.setNumberOfColumns(COLUMNS);
        setGridPresenter(gridPresenter);

        PresenterSelector cardPresenterSelector = new CardPresenterSelector(getActivity());
        mAdapter = new ArrayObjectAdapter(cardPresenterSelector);
        setAdapter(mAdapter);

        prepareEntranceTransition();

        createRows();
        startEntranceTransition();

    }

    private void createRows() {
        GetCardsInformation getCardsInformation=new GetCardsInformation(mAdapter);
        getCardsInformation.startThread();
    }
}
