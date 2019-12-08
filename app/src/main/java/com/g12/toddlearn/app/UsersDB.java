package com.g12.toddlearn.app;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UsersDB extends RealmObject {
    @PrimaryKey
    private long id;
    private String email;
    private ChildsDB child;
    private int maxTime;
    private String password;

    public UsersDB(){
        this.id = MyApp.userID.incrementAndGet();
    }

    public UsersDB(String email, String password){
        this();
        this.email = email;
        this.maxTime = 15;
        this.password = password;
    }

    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ChildsDB getChild() {
        return child;
    }

    public void setChild(ChildsDB childName) {
        this.child = childName;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersDB{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", child=" + child +
                ", maxTime=" + maxTime +
                ", password='" + password + '\'' +
                '}';
    }
}
