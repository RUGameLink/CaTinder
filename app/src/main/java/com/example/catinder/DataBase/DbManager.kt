package com.example.catinder.DataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager (context: Context) {
    val dbHelper = DbHelper(context)
    var dataBase: SQLiteDatabase?= null

    fun openDb(){ //Функция открытия БД
        dataBase = dbHelper.writableDatabase
    }
    fun insertToDb(url: String){//Функция записи БД
        val values = ContentValues().apply {
            put(DbName.COLUMN_NAME_TITLE, url)
        }
        dataBase?.insert(DbName.TABLE_NAME, null, values)//Запись в бд в соответствующий столбец
    }

    @SuppressLint("Range")
    fun readDbDataNames(): ArrayList<String>{ //Считывание из бд в лист
        val dataList = ArrayList<String>()

        val cursor = dataBase?.query(
            DbName.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)
        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(DbName.COLUMN_NAME_TITLE))//Считывание в переменную из столбца
                dataList.add(dataText.toString())
            }
        }
        cursor?.close()
        return dataList//Возврат листа
    }

    fun closeDb(){//Функция закрытия БД
        dbHelper.close()
    }
}