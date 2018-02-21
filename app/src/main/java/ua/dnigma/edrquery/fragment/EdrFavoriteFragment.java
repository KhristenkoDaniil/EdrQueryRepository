package ua.dnigma.edrquery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.adapter.EdrFavoriteRecyclerAdapter;
import ua.dnigma.edrquery.adapter.EdrRecyclerAdapter;
import ua.dnigma.edrquery.callback.OnCheckboxCallback;
import ua.dnigma.edrquery.callback.OnEdrQueryCallback;
import ua.dnigma.edrquery.data.DataBaseManager;
import ua.dnigma.edrquery.manager.EdrQueryManager;
import ua.dnigma.edrquery.model.EdrQuery;
import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 29.11.2017.
 */

public class EdrFavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private EdrFavoriteRecyclerAdapter edrFavoriteRecyclerAdapter;
    List<Item> itemList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_favorite_fragment_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.edr_favorite_recyclerView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemList = DataBaseManager.getInstance(getContext()).getEdrFavoriteTableDao()
                .showFavoriteItems();

        edrFavoriteRecyclerAdapter = new EdrFavoriteRecyclerAdapter(getContext(), itemList,
                null);
        recyclerView.setAdapter(edrFavoriteRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


}
