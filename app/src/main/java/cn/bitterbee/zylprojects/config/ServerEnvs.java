package cn.bitterbee.zylprojects.config;

import cn.bitterbee.zylprojects.BuildConfig;

/**
 * Created by zyl06 on 9/18/15.
 */
public class ServerEnvs {
    private static final String sBaseUrlDev = "http://yanxuanpay.hztest.mail.163.com";
    private static final String sBaseUrlPre = "https://payrelease.you.163.com";
    private static final String sBaseUrlOnline = "https://pay.you.163.com";

    //WZP的各个配置的host
    private static final String sWzpHostOnline = "lbs.client.163.com:8080";//线上服
    private static final String sWzpHostPre = "223.252.216.146:8080";   // 预发布
    private static final String sWzpHostDev = "220.181.12.248:8080";   // 开发服（测试服）

    public static String getAppBaseUrl() {
        if (isDebug()) {
            return sBaseUrlDev;
        } else {
            if (BuildConfig.FLAVOR.equals("betaOnline")) {
                return sBaseUrlOnline;
            } else if (BuildConfig.FLAVOR.equals("betaPre")) {
                return sBaseUrlPre;
            } else if (BuildConfig.FLAVOR.equals("betaDev")) {
                return sBaseUrlDev;
            } else {
                return sBaseUrlOnline;
            }
        }
    }

    public static String getWzpLocateHost() {
        if (isDebug()) {
            return sWzpHostDev;
        } else {
            if (BuildConfig.FLAVOR.equals("betaOnline")) {
                return sWzpHostOnline;
            } else if (BuildConfig.FLAVOR.equals("betaPre")) {
                return sWzpHostPre;
            } else if (BuildConfig.FLAVOR.equals("betaDev")) {
                return sWzpHostDev;
            } else {
                return sWzpHostOnline;
            }
        }
    }

    public static boolean isOnline() {
        return !BuildConfig.FLAVOR.equals("betaPre") && !BuildConfig.FLAVOR.equals("betaDev") ;
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static String getChannel() {
        return BuildConfig.FLAVOR;
    }

}
