package xcini.rv.com.xcini.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 3/23/2017.
 */

public class Web_Service {

  //  public static String BASE_URL = "http://35.163.30.111/api/v1/";
 public static String BASE_URL = "http://ec2-52-13-27-16.us-west-2.compute.amazonaws.com/api/v1/";

    public API api;

    public Web_Service() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(API.class);
    }

    public API getApiService() {
        return api;
    }
}
