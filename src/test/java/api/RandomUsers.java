package api;

import models.CreateUsers;

public class RandomUsers {

    public static CreateUsers generate() {
        long timestamp = System.currentTimeMillis();
        String uniqueEmail = "user" + timestamp + "@yandex.ru";
        String password = "password" + timestamp;
        String name = "Name" + timestamp;
        return new CreateUsers(uniqueEmail, password, name);
    }
}