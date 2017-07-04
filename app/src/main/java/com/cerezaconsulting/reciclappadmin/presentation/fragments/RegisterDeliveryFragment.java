package com.cerezaconsulting.reciclappadmin.presentation.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cerezaconsulting.reciclappadmin.R;
import com.cerezaconsulting.reciclappadmin.core.BaseFragment;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;
import com.cerezaconsulting.reciclappadmin.presentation.activities.ReceivedBenefitActivity;
import com.cerezaconsulting.reciclappadmin.presentation.contracts.RegisterDeliveryContract;
import com.cerezaconsulting.reciclappadmin.presentation.utils.ProgressDialogCustom;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by miguel on 13/06/17.
 */

public class RegisterDeliveryFragment extends BaseFragment implements RegisterDeliveryContract.View{

    public static int USER_CODE = 2314;

    @BindView(R.id.et_paper_quantity)
    EditText etPaperQuantity;
    @BindView(R.id.et_glass_quantity)
    EditText etGlassQuantity;
    @BindView(R.id.et_plastic_quantity)
    EditText etPlasticQuantity;
    @BindView(R.id.btn_read_qr)
    Button btnReadQr;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    Unbinder unbinder;
    @BindView(R.id.btn_make_delivery)
    Button btnMakeDelivery;

    private ProgressDialogCustom progressDialogCustom;
    private RegisterDeliveryContract.Presenter presenter;
    private UserEntity userEntity;


    public static RegisterDeliveryFragment newInstance() {
        return new RegisterDeliveryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_delivery, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_read_qr)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(),ReceivedBenefitActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==USER_CODE){
                String user = data.getStringExtra("user");
                try {
                    userEntity = new Gson().fromJson(user, UserEntity.class);
                }catch (Exception e){
                    setMessageError("No se pudo leer el código, intente nuevamente");
                }
                tvUserName.setText("Su usuario es: "+userEntity.getFullName());
                tvUserName.setVisibility(View.VISIBLE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_make_delivery)
    public void onClicked() {
        presenter.registerDelivery(userEntity.getUser_id(),etPaperQuantity.getText().toString(),etGlassQuantity.getText().toString(),etPlasticQuantity.getText().toString());
    }

    @Override
    public void registerSuccessfully() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Registro exitoso")
                .setPositiveButton("Aceptar",null)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        getActivity().finish();
                    }
                })
                .create();
        alertDialog.show();
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
    public void setPresenter(RegisterDeliveryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
