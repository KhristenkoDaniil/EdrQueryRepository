package ua.dnigma.edrquery.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.dnigma.edrquery.model.EdrQuery;

/**
 * Created by Даниил on 21.11.2017.
 */

public interface EdrApi {

    @GET("/v1/declaration/")
    Call<EdrQuery> getEdrDeclaration(
            @Query("q") String lastName
    );
}
