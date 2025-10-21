package com.example.letss_talk;

public class Users {
    String profilepic,mail,UserPhone,userName,password,userId,lastMessage,status;

   public Users(){

   }
    public Users(String userId, String userName, String emaill, String password, String profilepic, String status){
        this.userId = userId;
        this.userName = userName;
        this.mail = emaill;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
    }
    public Users(String userId, String userName, String UserPhone, String emaill, String password, String profilepic, String status){
        this.userId = userId;
        this.userName = userName;
        this.UserPhone = String.valueOf(UserPhone);
        this.mail = emaill;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
    }

    public String getPhone() {
        return UserPhone;
    }

    public void setPhone(String phone) {
        this.UserPhone = phone;
    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
