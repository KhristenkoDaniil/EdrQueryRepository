package ua.dnigma.edrquery.callback;


import ua.dnigma.edrquery.model.EdrQuery;

/**
 * Created by Даниил on 27.11.2017.
 */

public interface OnEdrQueryCallback {

    void onSucsses(EdrQuery edrQuery);
    void onFailure (String error);
}
