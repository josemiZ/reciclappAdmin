package com.cerezaconsulting.reciclappadmin.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseActivity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;

/**
 * Created by miguel on 29/06/17.
 */

public class LoadActivity extends BaseActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        sessionManager = new SessionManager(getApplicationContext());
        initializeView();
    }

    private void initializeView(){
        next(this, null, sessionManager.isLogin() ? MainActivity.class : LoginActivity.class, true);
    }
}
