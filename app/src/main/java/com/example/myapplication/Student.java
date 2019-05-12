package com.example.myapplication;

public class Student {
    private String num;
    private String name;
    private int sound;


    public Student(String num, String name, int sound) {
        this.num = num;
        this.name = name;
        this.sound = sound;
    }

    public Student(String num, String name) {
        this.num = num;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getSound() {
        return sound;
    }
}
