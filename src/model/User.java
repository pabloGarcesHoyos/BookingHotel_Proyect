package model;

public class User {

    private int id;
    private String nameUser;
    private String emailUser;
    private String password;
    private String contactDetails;
    private String rol;

    public User(int id, String nameUser, String emailUser, String password, String contactDetails, String rol) {
        this.id = id;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
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
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
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
