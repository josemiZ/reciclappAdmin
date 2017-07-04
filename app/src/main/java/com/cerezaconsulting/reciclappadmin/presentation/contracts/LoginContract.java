package com.cerezaconsulting.reciclappadmin.presentation.contracts;

import com.cerezaconsulting.reciclappadmin.core.BasePresenter;
import com.cerezaconsulting.reciclappadmin.core.BaseView;

/**
 * Created by miguel on 29/06/17.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginSuccessfully();
    }
    interface Presenter extends BasePresenter{
        void login(String email,String password);
    }
}
