package model;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private String contactDetails;
    private String rol;

    public User(int id, String name, String email, String password, String contactDetails, String rol) {
        this.id = id;
        this.userName = name;
        this.email = email;
        this.password = password;
        this.contactDetails = contactDetails;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return userName;
    }

    public void setNameUser(String userName) {
        this.userName = userName;
    }

    public String getEmailUser() {
        return email;
    }

    public void setEmailUser(String emailUser) {
        this.email = emailUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}
