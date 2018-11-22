package com.lixiaozhuo.androidcomponent.content_provider.system_book;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取系统手机本
 */
public class ContentProvider_SystemBookActivity extends Activity {
    //数组适配器
    ArrayAdapter<String> adapter;
    //存放读取的数据
    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_book);
        //获取控件
        ListView contactsView =  findViewById(R.id.contacts_view);
        //实例化适配器
        adapter = new ArrayAdapter<>(this, android.R.layout. simple_list_item_1, contactsList);
        //设置适配器
        contactsView.setAdapter(adapter);
        //检查是否有权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS }, 1);
        } else {
            readContacts();
        }
    }
    // 查询联系人数据
    private void readContacts() {
        //游标
        Cursor cursor = null;
        try {
            //获取游标
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    // 获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //添加数据
                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                //关闭游标
                cursor.close();
            }
        }
    }

    /**
     * 请求权限时调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "请允许此权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
