package com.cerezaconsulting.reciclappadmin.presentation.contracts;

import com.cerezaconsulting.reciclappadmin.core.BasePresenter;
import com.cerezaconsulting.reciclappadmin.core.BaseView;
import com.cerezaconsulting.reciclappadmin.data.entities.UserEntity;

/**
 * Created by miguel on 30/06/17.
 */

public interface AccountContract {
    interface View extends BaseView<Presenter>{
        void loadUser(UserEntity userEntity);
    }
    interface Presenter extends BasePresenter{
        void closeSession();
    }
}
