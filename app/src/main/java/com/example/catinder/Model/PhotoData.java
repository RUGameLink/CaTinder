package com.example.catinder.Model;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.catinder.DataBase.DbManager;

import java.util.ArrayList;
import java.util.List;

public class PhotoData implements Parcelable {

    private String mUrl;
    private String mTitle;


    public PhotoData(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected PhotoData(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<PhotoData> CREATOR = new Creator<PhotoData>() {
        @Override
        public PhotoData createFromParcel(Parcel in) {
            return new PhotoData(in);
        }

        @Override
        public PhotoData[] newArray(int size) {
            return new PhotoData[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<PhotoData> getSpacePhotos(Context context) {
        DbManager dbManager = new DbManager(context); //Инициализация бд-менеджера
        dbManager.openDb(); //Открытие бд
        ArrayList<PhotoData> urlData = dbManager.readDbDataNames();
        dbManager.closeDb(); //Закрытие бд

        return urlData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}