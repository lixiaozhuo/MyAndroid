package com.lixiaozhuo.game.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *游戏记录数据库帮助器(管理数据库的创建和版本的管理)
 */
public class GameRecordSQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context 上下文
     */
    public GameRecordSQLiteOpenHelper(Context context) {
        super(context, "tb_game_record", null, 1);
    }
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //新建记录表
        db.execSQL("CREATE TABLE tb_game_record(level_id INTEGER PRIMARY KEY,score INTEGER)");
        //插入空数据
        db.execSQL("INSERT INTO tb_game_record(level_id,score) VALUES("+R.id.radioLevel1+",0)");
        db.execSQL("INSERT INTO tb_game_record(level_id,score) VALUES("+R.id.radioLevel2+",0)");
        db.execSQL("INSERT INTO tb_game_record(level_id,score) VALUES("+R.id.radioLevel3+",0)");
        db.execSQL("INSERT INTO tb_game_record(level_id,score) VALUES("+R.id.radioLevel4+",0)");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 更新数据
     * @param db
     */
    public  void  onUpdate(SQLiteDatabase db,GameScore gameScore){
        Log.e("AndroidApplication",gameScore.getLevel()+":" + gameScore.getScore());
        db.execSQL("UPDATE tb_game_record SET score="+gameScore.getScore()+
                " WHERE level_id=" + gameScore.getLevel()+ " AND score<" + gameScore.getScore());
    }

    /**
     * 清除数据
     * @param db
     */
    public  void  onClear(SQLiteDatabase db){
        db.execSQL("UPDATE tb_game_record SET score=0");
    }

    /**
     * 查询数据
     * @param db
     * @return
     */
    public List<GameScore> onList(SQLiteDatabase db){
        //存储的查询的数据
        List<GameScore> list = new ArrayList<>();
        //开始查询
        Cursor cursor = db.rawQuery("SELECT  level_id,score FROM tb_game_record", null);
        //转换数据
        while(cursor.moveToNext()){
            //获得信息
            int levelId = cursor.getInt(cursor.getColumnIndex("level_id"));
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            Log.e("AndroidApplication",levelId+":"+score );
            if(score >0){
                list.add(new GameScore(levelId,score));
            }
        }
        //对数据进行排序
        list.sort(new Comparator<GameScore>() {
            @Override
            public int compare(GameScore o1, GameScore o2) {
                return o1.getLevelName().compareTo(o2.getLevelName());
            }
        });
        return list;
    }
}
