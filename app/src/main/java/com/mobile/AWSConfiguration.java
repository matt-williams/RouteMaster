//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.16
//
package com.mobile;

import com.amazonaws.mobilehelper.config.AWSMobileHelperConfiguration;
import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {
    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub af73aad1-69ce-455c-ae5a-56af85fefbc8 aws-my-sample-app-android-v0.16";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("eu-west-2");
    public static final String AMAZON_COGNITO_IDENTITY_POOL_ID =
        "eu-west-2:52ebc8de-5594-4c44-acb6-099778d89ed3";
    // GOOGLE CLOUD MESSAGING SENDER ID
    public static final String GOOGLE_CLOUD_MESSAGING_SENDER_ID =
        "384574105480";
    // SNS PLATFORM APPLICATION ARN
    public static final String AMAZON_SNS_PLATFORM_APPLICATION_ARN =
        "arn:aws:sns:eu-west-1:326866182998:app/GCM/routemaster_MOBILEHUB_11798843";
    public static final Regions AMAZON_SNS_REGION =
         Regions.fromName("eu-west-1");
    // SNS DEFAULT TOPIC ARN
    public static final String AMAZON_SNS_DEFAULT_TOPIC_ARN =
        "arn:aws:sns:eu-west-1:326866182998:routemaster_alldevices_MOBILEHUB_11798843";
    // SNS PLATFORM TOPIC ARNS
    public static final String[] AMAZON_SNS_TOPIC_ARNS =
        {"arn:aws:sns:eu-west-1:326866182998:routemaster_imposterdetected_MOBILEHUB_11798843"};
    public static final String AMAZON_COGNITO_USER_POOL_ID =
        "eu-west-2_NgCwtQ5Ip";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_ID =
        "t5kd5ecu468og0lh1g3277ioa";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_SECRET =
        "15ie03dbe3ooa6bvbulmorvudd0ib4va2t49hr6gnvhscfdjuc3c";

    private static final AWSMobileHelperConfiguration helperConfiguration = new AWSMobileHelperConfiguration.Builder()
        .withCognitoRegion(AMAZON_COGNITO_REGION)
        .withCognitoIdentityPoolId(AMAZON_COGNITO_IDENTITY_POOL_ID)
        .withCognitoUserPool(AMAZON_COGNITO_USER_POOL_ID,
            AMAZON_COGNITO_USER_POOL_CLIENT_ID, AMAZON_COGNITO_USER_POOL_CLIENT_SECRET)
        .build();
    /**
     * @return the configuration for AWSKit.
     */
    public static AWSMobileHelperConfiguration getAWSMobileHelperConfiguration() {
        return helperConfiguration;
    }
}