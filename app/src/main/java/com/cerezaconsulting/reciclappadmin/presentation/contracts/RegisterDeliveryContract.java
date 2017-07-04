package com.cerezaconsulting.reciclappadmin.presentation.contracts;

import com.cerezaconsulting.reciclappadmin.core.BasePresenter;
import com.cerezaconsulting.reciclappadmin.core.BaseView;

/**
 * Created by miguel on 30/06/17.
 */

public interface RegisterDeliveryContract {
    interface View extends BaseView<Presenter>{
        void registerSuccessfully();
    }
    interface Presenter extends BasePresenter{
        void registerDelivery(String userId,String paper,String glass,String plastic);
    }
}
