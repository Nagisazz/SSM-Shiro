package com.ssmshiro.common.constants;

/**
 * 常量
 */
public class Constants {
    public static final String TEST_REDIS = "redis";

    //token存储常量
    public static final String ID_TOKEN = "token";

    //token过期常量(默认为10天未登录清除token)
    public static final int TOKEN_TIME = 864000;

    //用户类别为admin
    public static final String USER_ADMIN = "admin";

    //用户类别为person
    public static final String USER_PERSON = "person";

    //指定有偏好值时相近用户数量
    public final static int EVALUATE_NEIGHBORHOOD_NUM = 10;

    //指定有偏好值时推荐的药物数量
    public final static int EVALUATE_RECOMMENDER_NUM = 6;

    //指定无偏好值时相近用户数量
    public final static int BOOLEAN_NEIGHBORHOOD_NUM = 20;

    //指定无偏好值时推荐的药物数量
    public final static int BOOLEAN_RECOMMENDER_NUM = 10;
}
