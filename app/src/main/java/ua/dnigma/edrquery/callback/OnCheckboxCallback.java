package ua.dnigma.edrquery.callback;

import ua.dnigma.edrquery.model.Item;

/**
 * Created by Даниил on 13.01.2018.
 */

public interface OnCheckboxCallback {

    void onChecked(Item item);
    void unChecked(String id);
}
