package com.cerezaconsulting.reciclappadmin.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseFragment;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.data.repositories.local.SessionManager;
import com.cerezaconsulting.reciclappadmin.presentation.activities.AccountActivity;
import com.cerezaconsulting.reciclappadmin.presentation.activities.RegisterDeliveryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by miguel on 13/06/17.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.btn_register_delivery)
    LinearLayout btnRegisterDelivery;
    @BindView(R.id.btn_manage_deliveries)
    LinearLayout btnManageDeliveries;
    @BindView(R.id.btn_my_account)
    LinearLayout btnMyAccount;
    Unbinder unbinder;
    @BindView(R.id.tv_welcome_user)
    TextView tvWelcomeUser;

    private SessionManager sessionManager;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_panel, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(getContext());
        UserEntity userEntity = sessionManager.getUserEntity();
        tvWelcomeUser.setText(getString(R.string.hello) + " " + userEntity.getFullName() + getString(R.string.good_day));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_register_delivery, R.id.btn_manage_deliveries, R.id.btn_my_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_delivery:
                next(getActivity(), null, RegisterDeliveryActivity.class, false);
                break;
            case R.id.btn_manage_deliveries:
                break;
            case R.id.btn_my_account:
                next(getActivity(), null, AccountActivity.class, false);
                break;
        }
    }
}
