package com.example.seoyoung.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

import static android.content.ComponentName.readFromParcel;

class place implements Parcelable {
    public String p_name;
    public String ap1_SSRD;
    public int ap1_RSSI;
    public String ap2_SSRD;
    public int ap2_RSSI;

    public  place() {
        p_name = null;
        ap1_SSRD = null;
        ap1_RSSI = 0;
        ap2_SSRD = null;
        ap2_RSSI = 0;
    }

    public place(Parcel in) {
        this.p_name = in.readString();
        this.ap1_SSRD = in.readString();
        this.ap2_SSRD = in.readString();
        this.ap1_RSSI = in.readInt();
        this.ap2_RSSI = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(p_name);
        dest.writeString(ap1_SSRD);
        dest.writeString(ap2_SSRD);
        dest.writeInt(ap1_RSSI);
        dest.writeInt(ap2_RSSI);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public place createFromParcel(Parcel in) {
            return new place(in);
        }

        @Override
        public place[] newArray(int size) {
            return new place[size];
        }
    };

    void set_data(String place,  String ap1_S, String ap2_S, int ap1_R, int ap2_R) {
        this.p_name = place;
        this.ap1_SSRD = ap1_S;
        this.ap2_SSRD = ap2_S;
        this.ap1_RSSI = ap1_R;
        this.ap2_RSSI = ap2_R;
    }
}