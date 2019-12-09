package com.g12.toddlearn.app;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
<<<<<<< HEAD
=======

>>>>>>> Registration problem fixed

public class ChildsDB extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private long timeGame1;
    private long timeGame2;
    private long timeBoth;

    public ChildsDB(){
        this.id = MyApp.childID.incrementAndGet();
    }

    public ChildsDB(String name){
        this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){ this.id = id; }
<<<<<<< HEAD
=======


>>>>>>> Registration problem fixed

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeGame1() {
        return timeGame1;
    }

    public void setTimeGame1(long timeGame1) {
        this.timeGame1 = timeGame1;
    }

    public long getTimeGame2() {
        return timeGame2;
    }

    public void setTimeGame2(long timeGame2) {
        this.timeGame2 = timeGame2;
    }

    public long getTimeBoth() {
        return timeBoth;
    }

    public void setTimeBoth(long timeBoth) {
        this.timeBoth = timeBoth;
    }

    @Override
    public String toString() {
        return "ChildsDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeGame1=" + timeGame1 +
                ", timeGame2=" + timeGame2 +
                ", timeBoth=" + timeBoth +
                '}';
    }
}
