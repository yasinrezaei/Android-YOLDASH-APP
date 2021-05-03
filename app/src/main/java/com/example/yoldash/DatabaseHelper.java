package com.example.yoldash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import javax.naming.Context;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Vocab.db";
    public static final String TABLE1="All_Vocab";
    public static final String TABLE2="All_Mem_Vocab";
    public static final String TABLE3="Hard_Box";
    public static final String TABLE4="Middle_Box";
    public static final String TABLE5="Easy_Box";
    ///table 1 ---> all vocab
    public static  final String COL1="ID";
    public static  final String COL2="VOCAB";
    public static  final String COL3="TEXT";
    public static  final String COL4="LEVEL";
    ///table 2 ---> mem vocab
    public static  final String M_COL1="ID";
    public static  final String M_COL2="VOCAB";
    public static  final String M_COL3="TEXT";
    public static  final String M_COL4="LEVEL";
    ///table 3 ---> hard box
    public static  final String H_COL1="ID";
    public static  final String H_COL2="VOCAB";
    public static  final String H_COL3="TEXT";
    public static  final String H_COL4="LEVEL";
    public static  final String H_COL5="COUNT";

    ///table 4 ---> middle box
    public static  final String MI_COL1="ID";
    public static  final String MI_COL2="VOCAB";
    public static  final String MI_COL3="TEXT";
    public static  final String MI_COL4="LEVEL";
    public static  final String MI_COL5="COUNT";
    ///table 5 ---> easy box
    public static  final String E_COL1="ID";
    public static  final String E_COL2="VOCAB";
    public static  final String E_COL3="TEXT";
    public static  final String E_COL4="LEVEL";
    public static  final String E_COL5="COUNT";


    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1="CREATE TABLE "+TABLE1+"(id INTEGER PRIMARY KEY,vocab TEXT,text TEXT,level TEXT)";
        String table2="CREATE TABLE "+TABLE2+"(id INTEGER PRIMARY KEY,vocab TEXT,text TEXT,level TEXT)";
        String table3="CREATE TABLE "+TABLE3+"(id INTEGER PRIMARY KEY,vocab TEXT,text TEXT,level TEXT,count INTEGER)";
        String table4="CREATE TABLE "+TABLE4+"(id INTEGER PRIMARY KEY,vocab TEXT,text TEXT,level TEXT,count INTEGER)";
        String table5="CREATE TABLE "+TABLE5+"(id INTEGER PRIMARY KEY,vocab TEXT,text TEXT,level TEXT,count INTEGER)";

        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);
        db.execSQL(table4);
        db.execSQL(table5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE5);
    }

    ///Insert in ALL VOCAB
    public boolean insert_all(String vocab,String text,String level){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(COL2,vocab);
        contentValues1.put(COL3,text);
        contentValues1.put(COL4,level);
        long Result=db.insert(TABLE1,null,contentValues1);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    ///Insert in MEM VOCAB
    public boolean insert_mem(String vocab,String text,String level){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues2=new ContentValues();
        contentValues2.put(M_COL2,vocab);
        contentValues2.put(M_COL3,text);
        contentValues2.put(M_COL4,level);
        long Result=db.insert(TABLE2,null,contentValues2);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }
    ///Insert in Hard Box
    public boolean insert_hard(String vocab,String text,String level,int count){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues3=new ContentValues();
        contentValues3.put(H_COL2,vocab);
        contentValues3.put(H_COL3,text);
        contentValues3.put(H_COL4,level);
        contentValues3.put(H_COL5,count);
        long Result=db.insert(TABLE3,null,contentValues3);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }
    ///Insert in Middle Box
    public boolean insert_middle(String vocab,String text,String level,int count){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues4=new ContentValues();
        contentValues4.put(MI_COL2,vocab);
        contentValues4.put(MI_COL3,text);
        contentValues4.put(MI_COL4,level);
        contentValues4.put(MI_COL5,count);
        long Result=db.insert(TABLE4,null,contentValues4);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }
    ///Insert in Easy Box
    public boolean insert_easy(String vocab,String text,String level,int count){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues5=new ContentValues();
        contentValues5.put(E_COL2,vocab);
        contentValues5.put(E_COL3,text);
        contentValues5.put(E_COL4,level);
        contentValues5.put(E_COL5,count);
        long Result=db.insert(TABLE5,null,contentValues5);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    ///delete data from all
    public int deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE1,"ID=?",new String[]{id});
    }

    //////delete data from mem
    public int deleteData_mem(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE2,"ID=?",new String[]{id});
    }
    //////delete data from hard box
    public int deleteData_hard(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE3,"ID=?",new String[]{id});
    }
    //////delete data from middle box
    public int deleteData_middle(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE4,"ID=?",new String[]{id});
    }
    //////delete data from easy box
    public int deleteData_easy(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE5,"ID=?",new String[]{id});
    }


    ///get all vocab
    public Cursor getAllVocab(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE1 ,null);
        return res;
    }

    ///get all memvocab
    public Cursor getAllMemVocab(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE2 ,null);
        return res;
    }
    ///get all in hard box
    public Cursor getAllHardBox(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE3 ,null);
        return res;
    }
    ///get all in hard box
    public Cursor getAllMiddleBox(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE4 ,null);
        return res;
    }
    ///get all in hard box
    public Cursor getAllEasyBox(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE5 ,null);
        return res;
    }



    ///update hard box count
    public boolean update_box(String id,String count,int Flag){
        long Result = 1;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        if(Flag==0){
            values.put(H_COL5,count);
            Result =db.update(TABLE3,values,"ID=?",new String[]{id});
        }
        else if(Flag==1){
            values.put(MI_COL5,count);
            Result =db.update(TABLE4,values,"ID=?",new String[]{id});
        }
        else if(Flag==2){
            values.put(E_COL5,count);
            Result =db.update(TABLE5,values,"ID=?",new String[]{id});
        }

        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }

    }



}
