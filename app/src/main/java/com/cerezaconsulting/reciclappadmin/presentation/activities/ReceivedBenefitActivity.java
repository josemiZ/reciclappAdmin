package com.cerezaconsulting.reciclappadmin.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseActivity;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.presentation.fragments.ReceivedBenefitFragment;
import com.cerezaconsulting.reciclappadmin.presentation.fragments.RegisterDeliveryFragment;
import com.cerezaconsulting.reciclappadmin.presentation.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by miguel on 13/06/17.
 */

public class ReceivedBenefitActivity extends BaseActivity {
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

        ReceivedBenefitFragment fragment = (ReceivedBenefitFragment) getSupportFragmentManager().findFragmentById(R.id.body);
        if(fragment==null){
            fragment = ReceivedBenefitFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.body);
        }
    }

    public void sendUser(String userEntity){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user",userEntity);
        setResult(RegisterDeliveryFragment.USER_CODE,returnIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
