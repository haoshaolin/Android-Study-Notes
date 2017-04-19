package com.soros.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Soros on 2016/12/25.
 * Email: haoshaolin@126.com
 */

public class Person implements Parcelable {
    private char firstLetter;
    private String hobby;
    private String name;
    private int age;
    private int sex;
    private boolean isMale;
    private long height;

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(firstLetter);
        dest.writeString(hobby);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeInt(this.sex);
        dest.writeByte(isMale ? (byte) 1 : (byte) 0);
        dest.writeLong(this.height);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.firstLetter = (char) in.readInt();
        this.hobby = in.readString();
        this.name = in.readString();
        this.age = in.readInt();
        this.sex = in.readInt();
        this.isMale = in.readByte() != 0;
        this.height = in.readLong();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "firstLetter=" + firstLetter +
                ", hobby='" + hobby + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", isMale=" + isMale +
                ", height=" + height +
                '}';
    }
}
