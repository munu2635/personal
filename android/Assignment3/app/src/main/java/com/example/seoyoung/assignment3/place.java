package com.example.seoyoung.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

import static android.content.ComponentName.readFromParcel;

class place implements Parcelable {
    public String p_name;
    public String ap1_SSRD;
    public String ap2_SSRD;

    place() {
        p_name = null;
        ap1_SSRD = null;
        ap2_SSRD = null;
    }

    place(Parcel in) {
        readFromParcel(in);
    }

    place(String place,  String ap1_S,  String ap2_S) {
        p_name = place;
        ap1_SSRD = ap1_S;
        ap2_SSRD = ap2_S;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(p_name);
        dest.writeString(ap1_SSRD);
        dest.writeString(ap2_SSRD);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<place> CREATOR = new Creator<place>() {
        @Override
        public place createFromParcel(Parcel in) {
            return new place(in);
        }

        @Override
        public place[] newArray(int size) {
            return new place[size];
        }
    };

    void set_data(String place,  String ap1_S, String ap2_S) {
        p_name = place;
        ap1_SSRD = ap1_S;
        ap2_SSRD = ap2_S;
    }
}