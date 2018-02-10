package ua.dnigma.edrquery.manager;


import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ua.dnigma.edrquery.api.EdrApi;
import ua.dnigma.edrquery.callback.OnEdrQueryCallback;
import ua.dnigma.edrquery.data.DataBaseManager;
import ua.dnigma.edrquery.data.EdrFavoriteTableDao;
import ua.dnigma.edrquery.model.EdrQuery;
import ua.dnigma.edrquery.model.Item;
import ua.dnigma.edrquery.retrofit.RetrofitEdr;

/**
 * Created by Даниил on 22.11.2017.
 */

public class EdrQueryManager {

    private Context context;
    private Retrofit retrofit;

    public EdrQueryManager(Context context) {
        this.context = context;
        this.retrofit = RetrofitEdr.getInstance().getRetrofit();


        RetrofitEdr.getInstance().initConnectivityManager(context);
    }

    public void getDeclarationAsync(final OnEdrQueryCallback onEdrQueryCallback) {

        EdrApi edrApi = retrofit.create(EdrApi.class);

        Call<EdrQuery> call = edrApi.getEdrDeclaration("петр");

        call.enqueue(new Callback<EdrQuery>() {
            @Override
            public void onResponse(Call<EdrQuery> call, Response<EdrQuery> response) {

                onEdrQueryCallback.onSucsses(setFavoriteField(response.body()));

            }

            @Override
            public void onFailure(Call<EdrQuery> call, Throwable t) {

                onEdrQueryCallback.onFailure("Нет данных, заданных в поиске");

            }
        });


    }

    public EdrQuery setFavoriteField(EdrQuery edrQuery) {
        List<Item> items = edrQuery.getItems();

        for (int i = 0; i < items.size(); i++) {
            if (DataBaseManager.getInstance(context).getEdrFavoriteTableDao().isContainsId(items.get(i).getId())) {
                items.get(i).setFavorite(true);
            } else {
                items.get(i).setFavorite(false);
            }
        }
        edrQuery.setItems(items);
        return edrQuery;
    }
}
