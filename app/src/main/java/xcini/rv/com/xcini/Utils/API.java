package xcini.rv.com.xcini.Utils;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import xcini.rv.com.xcini.Beans.Countries_Pojo;
import xcini.rv.com.xcini.Beans.ForgotPassword_Pojo;
import xcini.rv.com.xcini.Beans.Login_Pojo;
import xcini.rv.com.xcini.Beans.PlaceDetails_Pojo;
import xcini.rv.com.xcini.Beans.PlacePredictions_Pojo;

public interface API {

    @GET()
    Call<PlacePredictions_Pojo> getLocations(@Url String url);

    @GET()
    Call<PlaceDetails_Pojo> getLocationDetails(@Url String url);


    @FormUrlEncoded
    @POST("login")
    Call<Login_Pojo> login(@Header("Content-Type") String Authorization,
                           @Header("Accept") String Accept,
                           @Field("user[email]") String email,
                           @Field("user[phone_number]") String phone_number,
                           @Field("user[password]") String password);

    @FormUrlEncoded
    @POST("forgot-password")
    Call<ForgotPassword_Pojo> forgotPassword(@Field("email") String email);


    @GET("countries")
    Call<Countries_Pojo> getCountries(@Header("Accept") String Accept);

    @Multipart
    @POST("register")
    Call<ForgotPassword_Pojo> signup(
            @Header("Authorization") String Authorization,
            @Header("Accept") String Accept,

            @Part("user[role]") RequestBody userrole,
            @Part("user[first_name]") RequestBody firstname,
            @Part("user[last_name]") RequestBody lastname,
            @Part("user[email]") RequestBody email,
            @Part("user[phone_number]") RequestBody userphonenumber,
            @Part("user[password]") RequestBody paswsword,
            @Part("user[password_confirmation]") RequestBody confirm_password,
            @Part("user[country_id]") RequestBody countryid,
            @Part("user[eula]") RequestBody eula
    );

   /* @Multipart
    @POST("register")
    Call<ForgotPassword_Pojo> signup(
            @Header("Authorization") String Authorization,
            @Header("Accept") String Accept,

            @Part("user[role]") RequestBody userrole,
            @Part("user[first_name]") RequestBody firstname,
            @Part("user[last_name]") RequestBody lastname,
            @Part("user[email]") RequestBody email,
            @Part("user[phone_number]") RequestBody userphonenumber,
            @Part("user[password]") RequestBody paswsword,
            @Part("user[password_confirmation]") RequestBody confirm_password,
            @Part("user[country_id]") RequestBody countryid,
            @Part("user[profile_attributes][dob]") RequestBody dob,
            @Part("user[profile_attributes][public_id]") RequestBody publicid,
            @Part("user[profile_attributes][license_number]") RequestBody licencenumber,
            @Part("user[profile_attributes][interests][]") RequestBody interests,
            @Part("user[transportation_attributes][type]") RequestBody transportationtype,
            @Part("user[transportation_attributes][make]") RequestBody make,
            @Part("user[transportation_attributes][year]") RequestBody year,
            @Part("user[transportation_attributes][title]") RequestBody title,
            @Part("user[insurence_ids][]") RequestBody insuranceids,
            @Part("user[eula]") RequestBody eula,
            @Part MultipartBody.Part profile);*/

    @FormUrlEncoded
    @POST("send_otp")
    Call<ForgotPassword_Pojo> sendOtp(@Header("Content-Type") String Authorization, @Header("Accept") String Accept, @Field("phone_number") String email);

    @FormUrlEncoded
    @POST("verify_otp")
    Call<Login_Pojo> verifyOtp(@Header("Content-Type") String Authorization, @Header("Accept") String Accept, @Field("phone_number") String email, @Field("otp") String otp);


}
