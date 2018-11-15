package com.lixiaozhuo.androidcomponent.content_provider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * ContentProvider数据提供者
 */
public class ContentProvider_Provider extends ContentProvider {
    /**
     *全部图书
     */
    public static final int BOOK_DIR = 0;
    /**
     * 指定图书
     */
    public static final int BOOK_ITEM = 1;
    /**
     * 全部分类
     */
    public static final int CATEGORY_DIR = 2;
    /**
     *指定分离
     */
    public static final int CATEGORY_ITEM = 3;
    /**
     *
     */
    public static final String AUTHORITY = "com.example.databasetest.provider";

    //帮助匹配URI
    private static UriMatcher uriMatcher;

    //自定义帮助器
    private ContentProvider_ProviderHelper dbHelper;
    //
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        //实例化数据库帮助器
        dbHelper = new ContentProvider_ProviderHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    /**
     * 查询数据
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //获取数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //游标
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                //查询所有图书数据
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                //查询指定图书数据
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                //查询所有分类信息
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                //查询指定分类信息
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    /**
     * 添加数据
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                //插入图书信息
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                //插入分类信息
                long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    /**
     *更新数据
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //获取数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //存储更新条数
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                //更新所有图书数据
                updatedRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                //更新指定图书数据
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book", values, "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                //更新所有分类信息
                updatedRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                //更新指定分类信息
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category", values, "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return updatedRows;
    }

    /**
     * 删除数据
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //获区数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //存储删除条数
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                //删除所有图书信息
                deletedRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                //删除指定图书信息
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book", "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                //删除所有分类信息
                deletedRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                //删除指定分类信息
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category", "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        //返货删除条数
        return deletedRows;
    }

    /**
     * 获区其类型
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest. provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest. provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest. provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest. provider.category";
        }
        return null;
    }

}