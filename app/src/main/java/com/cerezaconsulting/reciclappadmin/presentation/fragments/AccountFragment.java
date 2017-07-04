package com.cerezaconsulting.reciclappadmin.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseFragment;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.presentation.activities.LoadActivity;
import com.cerezaconsulting.reciclappadmin.presentation.contracts.AccountContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by miguel on 13/06/17.
 */

public class AccountFragment extends BaseFragment implements AccountContract.View {

    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_full_name)
    TextView tvFullName;
    @BindView(R.id.tv_document_number)
    TextView tvDocumentNumber;
    @BindView(R.id.tv_birth_date)
    TextView tvBirthDate;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    Unbinder unbinder;
    @BindView(R.id.btn_close_session)
    Button btnCloseSession;

    private AccountContract.Presenter presenter;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loadUser(UserEntity userEntity) {
        tvFullName.setText(userEntity.getFullName());
        tvDocumentNumber.setText(userEntity.getDni());
        tvBirthDate.setText(userEntity.getBirth_date());
        tvEmail.setText(userEntity.getEmail());
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setMessageError(String error) {

    }

    @Override
    public void setDialogMessage(String message) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.btn_close_session)
    public void onViewClicked() {
        presenter.closeSession();
        newFlag(getActivity(),null, LoadActivity.class);
    }
}
