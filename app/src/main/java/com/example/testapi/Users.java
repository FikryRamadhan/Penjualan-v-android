package com.example.testapi;

public class Users {


    public int ID;

    public String name;

    public String username;

    public String alamat;

    public String password;

    public String type_user;


    public Users( String _name,String _alamat, String _username, String _password, String _typeUser)
    {
        name = _name;
        alamat = _alamat;
        username = _username;
        password = _password;
        type_user = _typeUser;
    }

    public Users(){
    }




    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

}
