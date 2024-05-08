/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.util;

/**
 *
 * @author nguyen
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String START_UP_CONTROLLER = "startUpController";
        public static final String DISPATCH_CONTROLLER = "dispatchController";
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String INVALID_PAGE = "invalidPage";
        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_PAGE = "searchStaticPage";
    }

    public class LoginFeatures {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String CREATE_NEW_ACCOUNT_PAGE = "createNewAccountPage";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
        public static final String LOG_OUT_CONTROLLER = "logOutController";
    }

    public class SearchFeature {

        public static final String DELETE_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
    }

    public class ProductFeature {

        public static final String SHOW_PRODUCT_CONTROLLER = "getProductController";
        public static final String ADD_TO_CART_CONTROLLER = "addToCartController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
        public static final String VIEW_PRODUCT_PAGE = "productPage";
        public static final String GO_BACK_TO_PRODUCT_LIST = "goBackToProductList";
    }
}
