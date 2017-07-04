package com.cerezaconsulting.reciclappadmin.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseActivity;
import com.cerezaconsulting.reciclappadmin.presentation.fragments.LoginFragment;
import com.cerezaconsulting.reciclappadmin.presentation.presenters.LoginPresenter;
import com.cerezaconsulting.reciclappadmin.presentation.utils.ActivityUtils;

/**
 * Created by miguel on 29/06/17.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.body);
        if (fragment == null){
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body);
        }
        new LoginPresenter(fragment,getApplicationContext());
    }
}
