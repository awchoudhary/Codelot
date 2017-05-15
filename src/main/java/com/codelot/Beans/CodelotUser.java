package com.codelot.Beans;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CodelotUser {
    @Id public Long id;
    @Index
    private String user_id;
    public String user_email;
    public String fullname;
    public int age;
    public String username;
    public String avatarImage;
    private JavaCodelot javaCodelot;
    private PythonCodelot pythonCodelot;
    private JavaScriptCodelot javaScriptCodelot;

    public CodelotUser() {}

    public CodelotUser(String fullname, int age, String username, String avatarImage) {
        //this.email = user_email --> will be gmail
        //this.user_id = user.id
        this.fullname = fullname;
        this.age = age;
        this.username = username;
        this.avatarImage = avatarImage;
        javaCodelot = new JavaCodelot();
        pythonCodelot = new PythonCodelot();
        javaScriptCodelot = new JavaScriptCodelot();
    }

    public void setUser (String email, String user_id) {
        this.user_email = email;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public JavaCodelot getJavaCodelot() {
        return javaCodelot;
    }

    public PythonCodelot getPythonCodelot() {
        return pythonCodelot;
    }

    public JavaScriptCodelot getJavaScriptCodelot() {
        return javaScriptCodelot;
    }

    public void setJavaCodelot(JavaCodelot javaCodelot) {
        this.javaCodelot = javaCodelot;
    }

    public void setPythonCodelot(PythonCodelot pythonCodelot) {
        this.pythonCodelot = pythonCodelot;
    }

    public void setJavaScriptCodelot(JavaScriptCodelot javaScriptCodelot) {
        this.javaScriptCodelot = javaScriptCodelot;
    }
}
