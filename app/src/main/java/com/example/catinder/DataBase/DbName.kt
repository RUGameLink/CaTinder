package com.example.catinder.DataBase

import android.provider.BaseColumns

object DbName: BaseColumns {//Набор констант для бд
const val TABLE_NAME = "cats"//Имя таблицы
    const val COLUMN_NAME_TITLE = "url"//Имя колонки

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "cats.db"//Название бд

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"//Константа запроса удаления таблицы

}