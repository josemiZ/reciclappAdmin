package com.cerezaconsulting.reciclappadmin.presentation.presenters;

import android.content.Context;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.ApiConstants;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.ServiceFactory;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.request.DeliveryRequest;
import com.cerezaconsulting.reciclappadmin.presentation.contracts.RegisterDeliveryContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by miguel on 30/06/17.
 */

public class RegisterDeliveryPresenter implements RegisterDeliveryContract.Presenter {

    private RegisterDeliveryContract.View mView;
    private Context context;
    private SessionManager sessionManager;

    public RegisterDeliveryPresenter(RegisterDeliveryContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        sessionManager = new SessionManager(context);
        this.mView.setPresenter(this);
    }

    @Override
    public void registerDelivery(String userId, String paper, String glass, String plastic) {
        mView.setLoadingIndicator(true);
        String token = sessionManager.getUserToken();
        UserEntity userEntity = sessionManager.getUserEntity();
        DeliveryRequest deliveryRequest = ServiceFactory.createService(DeliveryRequest.class);
        Call<Void> call = deliveryRequest.registerDelivery("Bearer "+token, ApiConstants.APP_JSON,userId,userEntity.getUser_id(),"2",paper,glass,plastic);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                if(response.isSuccessful()){
                    mView.registerSuccessfully();
                }
                else {
                    mView.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if(!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.setMessageError(context.getString(R.string.no_server_connection_try_it_later));
            }
        });
    }

    @Override
    public void start() {

    }
}
