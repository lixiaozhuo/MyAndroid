package com.lixiaozhuo.androidcomponent.network.network_operate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

/**
 * 网络改变监控广播
 * 监听网络的改变状态,只有在用户操作网络连接开关(wifi,mobile)的时候接受广播,
 * 显示当前网络状态
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    /**
     * 标记第一种监听方式
     */
    public static final String TAG0 = "监听方式1:first";
    /**
     * 标记第二种监听方式
     */
    public static final String TAG1 = "监听方式2:second";
    /**
     * 标记第三种监听方式
     */
    public static final String TAG2 = "监听方式3:third";
    /**
     * 存储当前网络状态
     */
    public static  String networkState;
    /**
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //监听wifi的打开与关闭
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            //获取wifi状态
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.e(TAG0, "WIFI状态:" + wifiState);
            switch (wifiState) {
                //已关闭
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.e(TAG0, "WIFI状态:已关闭-WIFI_STATE_DISABLED");
                    break;
                //关闭中
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.e(TAG0, "WIFI状态:关闭中-WIFI_STATE_DISABLING");
                    break;
                //连接中
                case WifiManager.WIFI_STATE_ENABLING:
                    Log.e(TAG0, "WIFI状态:连接中-WIFI_STATE_ENABLING");
                    break;
                //已连接
                case WifiManager.WIFI_STATE_ENABLED:
                    Log.e(TAG0, "WIFI状态:已连接-WIFI_STATE_ENABLED");
                    break;
                //未知
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Log.e(TAG0, "WIFI状态:未知-WIFI_STATE_UNKNOWN");
                    break;
                default:
                    break;
            }
        }
        // 监听网络的连接状态
        // 广播状态是WIFI_STATE_DISABLING和WIFI_STATE_DISABLED不会接到这个广播
        // 广播状态是WIFI_STATE_ENABLED状态的同时也会接到这个广播，
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            //获取wifi的连接状态
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                //判断网络连接状态
                boolean isConnected = networkInfo.getState() == NetworkInfo.State.CONNECTED;
                Log.e(TAG1, "WIFI连接状态：" + isConnected);
            }
        }

        // 监听网络连接的设置(包括wifi和移动数据的打开和关闭)
        // 比上面两个广播的反应要慢，如果只是要监听wifi，推荐使用上面两个广播配合使用
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取网络连接管理器
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Log.i(TAG2, "CONNECTIVITY_ACTION");
            networkState = "\n您的手机当前网络状态是：\n\n";
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                //网络已连接
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        //连接类型为WIFI
                        networkState += "当前WiFi连接可用 ";
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        //连接类型为移动网络
                        networkState += "当前移动网络连接可用 ";
                    }
                } else {
                    networkState += "当前没有网络连接，请确保你已经打开网络 ";
                }
                //获取网络状态
                networkState += "\n类型名：" + activeNetwork.getTypeName() +
                        "\n子类型名：" + activeNetwork.getSubtypeName() +
                        "\n状态：" + activeNetwork.getState() +
                        "\n详细状态：" + activeNetwork.getDetailedState().name() +
                        "\n额外状态：" + activeNetwork.getExtraInfo() +
                        "\n类型：" + activeNetwork.getType();
            } else {
                networkState += "当前没有网络连接，请确保你已经打开网络 ";
            }
            Log.e("String:", networkState);
        }

    }
}
