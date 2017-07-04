package com.cerezaconsulting.reciclappadmin.presentation.presenters;

import android.content.Context;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.data.entities.AccessTokenEntity;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.ApiConstants;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.ServiceFactory;
import com.cerezaconsulting.reciclappadmin.data.repositories.remote.request.UserRequest;
import com.cerezaconsulting.reciclappadmin.presentation.contracts.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by miguel on 29/06/17.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;
    private Context context;
    private SessionManager sessionManager;

    public LoginPresenter(LoginContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        sessionManager = new SessionManager(context);
        this.mView.setPresenter(this);
    }

    @Override
    public void login(String email, String password) {
        mView.setLoadingIndicator(true);
        UserRequest userRequest = ServiceFactory.createService(UserRequest.class);
        Call<AccessTokenEntity> call = userRequest.login(email,password, ApiConstants.GRANT_TYPE,ApiConstants.CLIENT_ID,ApiConstants.CLIENT_SECRET);
        call.enqueue(new Callback<AccessTokenEntity>() {
            @Override
            public void onResponse(Call<AccessTokenEntity> call, Response<AccessTokenEntity> response) {
                if(!mView.isActive()){
                    return;
                }
                if(response.isSuccessful()){
                    getAccount(response.body());
                }
                else{
                    mView.setLoadingIndicator(false);
                    mView.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
                }
            }

            @Override
            public void onFailure(Call<AccessTokenEntity> call, Throwable t) {
                if(!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.setMessageError(context.getString(R.string.no_server_connection_try_it_later));
            }
        });
    }

    private void getAccount(final AccessTokenEntity accessTokenEntity){
        UserRequest userRequest = ServiceFactory.createService(UserRequest.class);
        Call<UserEntity> call = userRequest.getUser("Bearer "+accessTokenEntity.getAccess_token());
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(!mView.isActive()){
                    return;
                }
                if(response.isSuccessful()){
                    openSession(accessTokenEntity,response.body());
                }
                else{
                    mView.setLoadingIndicator(false);
                    mView.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                if(!mView.isActive()){
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.setMessageError(context.getString(R.string.no_server_connection_try_it_later));
            }
        });
    }

    private void openSession(AccessTokenEntity accessTokenEntity, UserEntity userEntity){
        mView.setLoadingIndicator(false);
        sessionManager.openSession(accessTokenEntity.getAccess_token(),userEntity);
        mView.loginSuccessfully();
    }

    @Override
    public void start() {

    }
}
