//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.16
//
package com.amazonaws.mobilehelper.auth.signin.userpools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.mobilehelper.auth.signin.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobilehelper.util.ViewHelper;
import com.github.williams.matt.routemaster.R;


/**
 * Activity to prompt for sign-up confirmation information.
 */
public class SignUpConfirmActivity extends Activity {
    /** Log tag. */
    private static final String LOG_TAG = SignUpConfirmActivity.class.getSimpleName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirm);

        final String username = getIntent().getStringExtra(
            CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        ViewHelper.setEditTextStringValue(this, R.id.confirm_account_username, username);
    }

    /**
     * Retrieve input and return to caller.
     * @param view the Android View
     */
    public void confirmAccount(final View view) {
        final String username =
            ViewHelper.getEditTextStringValue(this, R.id.confirm_account_username);
        final String verificationCode =
            ViewHelper.getEditTextStringValue(this, R.id.confirm_account_confirmation_code);

        Log.d(LOG_TAG, "username = " + username);
        Log.d(LOG_TAG, "verificationCode = " + verificationCode);

        final Intent intent = new Intent();
        intent.putExtra(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, username);
        intent.putExtra(CognitoUserPoolsSignInProvider.AttributeKeys.VERIFICATION_CODE, verificationCode);

        setResult(RESULT_OK, intent);

        finish();
    }
}
