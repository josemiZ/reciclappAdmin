package com.cerezaconsulting.reciclappadmin.presentation.presenters;

import android.content.Context;

import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;
import com.cerezaconsulting.reciclappadmin.presentation.contracts.AccountContract;

/**
 * Created by miguel on 30/06/17.
 */

public class AccountPresenter implements AccountContract.Presenter {

    private AccountContract.View mView;
    private Context context;
    private SessionManager sessionManager;

    public AccountPresenter(AccountContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        sessionManager = new SessionManager(context);
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        UserEntity userEntity = sessionManager.getUserEntity();
        mView.loadUser(userEntity);
    }

    @Override
    public void closeSession() {
        sessionManager.closeSession();;
    }
}
