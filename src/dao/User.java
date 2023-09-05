package dao;

import java.util.Date;
import java.util.List;

public class User {
    private long userid;
    private String email;
    private String password;

    private boolean isOnline;

    private List<Article> articles;

    private Date joinDate;

    private String avatarLink;

    private boolean isWriter;

    private boolean isAdmin;

    public User (long userid, String email) {
        this.userid = userid;
        this.email = email;
    }

    public User (long userid, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }

    public long getUserid () {
        return this.userid;
    }

    public String getEmail () {
        return this.email;
    }
}
