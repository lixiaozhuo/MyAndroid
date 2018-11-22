package com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lixiaozhuo.androidcomponent.R;

/**
 * AsyncTask1
 */
public class AsyncTask1Activity extends Activity {
    //标志
    private static final String TAG = "AndroidApplication";
    //加载数据
    private Button btn_progress;
    //进度条
    private ProgressDialog progressDialog;
    //
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask1);
        //获取控件并绑定事件
        btn_progress =findViewById(R.id.btn_progress);
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(AsyncTask1Activity.this);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);
                myAsyncTask = new MyAsyncTask();
                //启动异步任务的处理
                myAsyncTask.execute();
            }
        });

    }

    /**
     *
     */
    public class MyAsyncTask extends AsyncTask<Void,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //显示进度条对话框（更改UI组件）
            progressDialog.show();
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //通过publishProgress方法传过来的值进行进度条的更新.
            progressDialog.setProgress(values[0]);
            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");
        }
        @Override
        protected String doInBackground(Void... params) {
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground Begin");
            //模拟进度条的进度.
            for (int i = 0;i < 100; i ++){
                //自动触发onProgressUpdate方法进行进度条的更新.
                publishProgress(i);
                try {
                    //模拟耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground End");
            return null;
        }
        @Override
        protected void onPostExecute(String string ){
            super.onPostExecute(string);
            //将进度条释放
            progressDialog.dismiss();
            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
        }

    }
}
