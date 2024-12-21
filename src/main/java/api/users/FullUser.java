package api.users;

import api.Support;

public class FullUser {
    UserData data;
    Support support;

    public FullUser(UserData data, Support support) {
        this.data = data;
        this.support = support;
    }

    public Support getSupport() {
        return support;
    }

    public UserData getData() {
        return data;
    }

    public FullUser() {
    }
}
