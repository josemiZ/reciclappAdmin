package com.cerezaconsulting.reciclappadmin.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseActivity;
import com.cerezaconsulting.reciclappadmin.presentation.fragments.AccountFragment;
import com.cerezaconsulting.reciclappadmin.presentation.presenters.AccountPresenter;
import com.cerezaconsulting.reciclappadmin.presentation.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by miguel on 13/06/17.
 */

public class AccountActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        AccountFragment fragment = (AccountFragment) getSupportFragmentManager().findFragmentById(R.id.body);
        if(fragment == null){
            fragment = AccountFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body);
        }
        new AccountPresenter(fragment,getApplicationContext());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
