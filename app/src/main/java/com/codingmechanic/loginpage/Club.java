package com.codingmechanic.loginpage;

/**
 * Created by mofi on 10/15/16.
 */

public class Club {
    private int collegeId;
    private String name;
    private String type;
    private String email;
    private String desc;

    public Club(String desc, String email, String name, String type) {
        this.collegeId = collegeId;
        this.desc = desc;
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
