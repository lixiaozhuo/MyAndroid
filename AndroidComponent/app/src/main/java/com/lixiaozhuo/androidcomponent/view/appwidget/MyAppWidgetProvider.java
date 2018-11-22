package com.lixiaozhuo.androidcomponent.view.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 桌面小部件
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
	//上一个
	private final String UPDATE_ACTION_PREVIEW = "com.lixiaozhuo.androidcomponent.view.appwidget.preview";
	//下一个
	private final String UPDATE_ACTION_NEXT = "com.lixiaozhuo.androidcomponent.view.appwidget.next";

	@Override
	public void onReceive(Context context, Intent intent) {
		//通过远程对象来设置AppWidget中的控件状态
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		//AppWidget管理对象，控制更新操作
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		//获得所有本程序创建的AppWidget
		ComponentName componentName = new ComponentName(context,MyAppWidgetProvider.class);

			//上一个
		if (intent.getAction().equals(UPDATE_ACTION_PREVIEW)) {
			remoteViews.setTextViewText(R.id.textView1, "上一个");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.icon);
			//下一个
		} else if (intent.getAction().equals(UPDATE_ACTION_NEXT)) {
			remoteViews.setTextViewText(R.id.textView1, "下一个");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_contact_list_picture);
		} else {
			remoteViews.setTextViewText(R.id.textView1, "系统自动更新广播");
			remoteViews.setImageViewResource(R.id.imageView1, R.drawable.cat);
		}
		// 更新AppWidget
		appWidgetManager.updateAppWidget(componentName, remoteViews);
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		//通过远程对象来设置AppWidget中的控件状态
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget);
		//绑定事件
		update(context, remoteViews, UPDATE_ACTION_PREVIEW, R.id.btn_pre);
		update(context, remoteViews, UPDATE_ACTION_NEXT, R.id.btn_next);
		//更新AppWidget
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	private void update(Context context,RemoteViews remoteViews, String action, int id) {
		Intent intent = new Intent(action);
		//安卓8.0必须添加
		intent.setComponent(new ComponentName(context,MyAppWidgetProvider.class));
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//绑定事件
		remoteViews.setOnClickPendingIntent(id, pendingIntent);
	}
	
	//AppWidget删除时
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	//AppWidget可以使用时
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	//AppWidget被禁用
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

}
