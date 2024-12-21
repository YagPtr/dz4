package api.answers;

import api.users.UserData;

public class PostPostUser extends UserData {
    private String updatedAt;

    public PostPostUser(Integer id, String email, String first_name, String last_name, String avatar,String updatedAt) {
        super(id, email, first_name, last_name, avatar);
        this.updatedAt = updatedAt;
    }

    public PostPostUser(){

    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
