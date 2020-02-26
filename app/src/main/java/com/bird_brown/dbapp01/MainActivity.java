package com.bird_brown.dbapp01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper helper;    //DBヘルパー

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DBヘルパーのオブジェクト作成
        helper = new DBHelper(this);

        //登録ボタンの取得とイベントリスナ登録
        Button regist = (Button)findViewById(R.id.button1);
        regist.setOnClickListener(this);

        //一覧ボタンの取得とイベントリスナ登録
        Button list = (Button)findViewById(R.id.button2);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //押されたボタンのIDを取得
        int id = view.getId();

        if (id == R.id.button1) {
            insertRecord();
        } else {    //一覧ボタンが押された時
            displayRecords();
        }
    }

    //データベース登録用メソッド
    private void insertRecord() {
        //テキストボックスのオブジェクトを取得
        EditText e = (EditText)findViewById(R.id.editText1);

        //書込みフルーツ名を取得
        String fruitName = e.getText().toString();

        //SQLiteDatabaseクラスの取得（書込み用）
        SQLiteDatabase database = helper.getWritableDatabase();

        //テーブルに登録するレコードの設定準備
        ContentValues values = new ContentValues();
        values.put("name", fruitName);

        //Fruitsテーブルに登録
        database.insert("Fruits", null, values);

        //データベースをクローズ
        database.close();

        //テキストボックスをクリア
        e.setText("");

        //トーストで結果表示
        Toast t = Toast.makeText(this, "登録しました。", Toast.LENGTH_SHORT);
        t.show();
    }

    //データベース一覧表示用メソッド
    private void displayRecords() {
        //SQLiteDatabaseクラスの取得（書込み用）
        SQLiteDatabase database = helper.getReadableDatabase();

        //テーブルから取得する別名を設定
        String[] columns = {"name"};

        //Fruitsテーブルからレコードを取得
        Cursor cursor = database.query("Fruits", columns, null, null, null, null, null);

        //フルーツ名格納用配列
        ArrayList<String> fruits = new ArrayList<String>();

        //配列にフルーツ名を順次格納
        while (cursor.moveToNext()) {
            fruits.add(cursor.getString(0));
        }

        //データベースをクローズ
        database.close();

        String s = "";  //トースト表示用変数

        //トーストで表示させるフルーツ一覧の設定
        for (String fruit : fruits) {
            s += fruit + "\n";
        }

        //トーストで一覧表示
        //Toast t = Toast.makeText(this, s, Toast.LENGTH_LONG);
        Toast t = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        t.show();
    }
}
