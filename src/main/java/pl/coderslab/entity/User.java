package pl.coderslab.entity;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;

    public User (int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public User ( String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public void  setId(int id){
        this.id = id;
    }
    public int getId (){
        return id;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName (){
        return userName;
    }
    public void  setEmail (String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public String getPassword (){
        return email;
    }


}
