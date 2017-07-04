package com.cerezaconsulting.reciclappadmin.data.repositories.remote.request;

import com.cerezaconsulting.reciclappadmin.data.repositories.remote.ApiConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by miguel on 29/06/17.
 */

public interface DeliveryRequest {
    @FormUrlEncoded
    @POST(ApiConstants.DELIVERIES)
    Call<Void> registerDelivery(@Header("Authorization") String token, @Header("Accept") String json,
                                @Field("colaborador_id") String idClient, @Field("empleado_id") String userId,
                                @Field("acopio_id") String place_id,@Field("cantidad_papel") String paper,
                                @Field("cantidad_vidrio") String glass, @Field("cantidad_plastico") String plastic);
}
