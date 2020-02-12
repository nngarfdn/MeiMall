package com.example.meimall.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private int groceryId ;
    private String userName;
    private String date;
    private String text;

    public Review(int groceryId, String userName, String date, String text) {
        this.groceryId = groceryId;
        this.userName = userName;
        this.date = date;
        this.text = text;
    }

    protected Review(Parcel in) {
        groceryId = in.readInt();
        userName = in.readString();
        date = in.readString();
        text = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "groceryId=" + groceryId +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(groceryId);
        dest.writeString(userName);
        dest.writeString(date);
        dest.writeString(text);
    }
}
