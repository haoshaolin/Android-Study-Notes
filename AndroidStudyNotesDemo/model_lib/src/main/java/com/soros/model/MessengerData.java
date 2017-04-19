package com.soros.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Soros on 2016/12/15.
 * Email: haoshaolin@126.com
 */

public class MessengerData implements Parcelable {

    public String data;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
    }

    public MessengerData() {
    }

    protected MessengerData(Parcel in) {
        this.data = in.readString();
    }

    public static final Parcelable.Creator<MessengerData> CREATOR = new Parcelable.Creator<MessengerData>() {
        public MessengerData createFromParcel(Parcel source) {
            return new MessengerData(source);
        }

        public MessengerData[] newArray(int size) {
            return new MessengerData[size];
        }
    };
}
