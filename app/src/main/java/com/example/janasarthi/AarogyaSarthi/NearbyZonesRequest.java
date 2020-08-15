package com.example.janasarthi.AarogyaSarthi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NearbyZonesRequest {
    @Headers("Content-Type:application/json")
    @POST("nearbyzones")
//    Call<GeoIqRequestObject> retreiveZones(@Body GeoIqRequestObject geoIqRequestObject);
    Call<JsonObject>retreiveZones(@Body GeoIqRequestObject geoIqRequestObject);
}
