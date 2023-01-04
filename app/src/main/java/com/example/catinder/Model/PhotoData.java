package com.example.catinder.Model;

import android.os.Parcel;
import android.os.Parcelable;

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

    public static  PhotoData[] getSpacePhotos() {

        return new PhotoData[]{
                new PhotoData("http://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                new PhotoData("http://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                new PhotoData("http://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                new PhotoData("http://i.imgur.com/qpr5LR2.jpg", "Earth"),
                new PhotoData("http://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                new PhotoData("http://i.imgur.com/3wQcZeY.jpg", "Satellite"),
        };
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