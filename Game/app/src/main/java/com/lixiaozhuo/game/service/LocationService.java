package com.lixiaozhuo.game.service;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lixiaozhuo.game.common.CommonData;

/**
 * 定位业务
 */
public class LocationService {
    //标志是否成功定位
    public static volatile Boolean isSuccess;
    //上下文
    private Context context;

    public LocationService(Context context){
        this.context = context;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        //初始化标志
        isSuccess = null;
        //声明并初始化AMapLocationClient类对象
        AMapLocationClient mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //解析amapLocation获取相应内容。
                        CommonData.ADCode = aMapLocation.getAdCode();
                        //更新标志
                        isSuccess = true;
                    }else {
                        //定位失败，通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("App-Game","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        //更新标志
                        isSuccess = false;
                    }
                }
            }
        });
        //设置参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果
        mLocationOption.setOnceLocationLatest(true);
        //设置定位请求超时时间
        mLocationOption.setHttpTimeOut(8000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
}
