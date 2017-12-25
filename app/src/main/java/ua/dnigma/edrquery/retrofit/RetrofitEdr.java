package ua.dnigma.edrquery.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Даниил on 21.11.2017.
 */

public class RetrofitEdr {

    public static final String URL = "https://public-api.nazk.gov.ua";
    Handler handler = new Handler();

    private static RetrofitEdr instance;
    private Retrofit retrofit;
    private ConnectivityManager connectivityManager;
    private Context context;

    private RetrofitEdr() {
        initRetrofit();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitEdr getInstance() {
        if(instance == null) {
            instance = new RetrofitEdr();
        }
        return instance;
    }

    private Retrofit initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        if (isConnected()) {
                            return chain.proceed(chain.request());
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Нет подключения",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                            throw new IOException();
                        }
                    }
                })
                .build();

         retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                 .client(okHttpClient)
                .build();
        return retrofit;
    }

    private boolean isConnected() {

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public void initConnectivityManager(Context context) {
        this.context = context;
        connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
