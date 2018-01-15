package ua.dnigma.edrquery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.adapter.EdrRecyclerAdapter;
import ua.dnigma.edrquery.callback.OnCheckboxCallback;
import ua.dnigma.edrquery.callback.OnEdrQueryCallback;
import ua.dnigma.edrquery.data.DBHelper;
import ua.dnigma.edrquery.data.DataBaseManager;
import ua.dnigma.edrquery.manager.EdrQueryManager;
import ua.dnigma.edrquery.model.EdrQuery;
import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 29.11.2017.
 */

public class EdrQueryFragment extends Fragment implements OnEdrQueryCallback, OnCheckboxCallback{

    private RecyclerView recyclerView;
    private EdrRecyclerAdapter edrRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.edr_recyclerView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new EdrQueryManager(getContext()).getDeclarationAsync(this);
    }

    @Override
    public void onSucsses(EdrQuery edrQueries) {

        edrRecyclerAdapter = new EdrRecyclerAdapter(getContext(), edrQueries.getItems(),
                this);
        recyclerView.setAdapter(edrRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onChecked(Item item) {
        DataBaseManager.getInstance(getContext()).getEdrFavoriteTableDao()
                .addInterestingEDRItemToDB(item);
    }

    @Override
    public void unChecked(String id) {
        DataBaseManager.getInstance(getContext()).getEdrFavoriteTableDao()
                .deleteNotInterestingEDRItemFromDB(id);

    }
}
