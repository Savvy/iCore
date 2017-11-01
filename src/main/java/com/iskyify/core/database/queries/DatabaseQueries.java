package com.iskyify.core.database.queries;

import lombok.Getter;

public enum DatabaseQueries {

    CREATE_USERS_TABLE("CREATE TABLE `users` (`id` int(5) AUTO_INCREMENT NOT NULL, `unique_id` varchar(36) NOT NULL, PRIMARY KEY(id));"),
    CREATE_USER_DATA_TABLE(""),
    SELECT_USER("SELECT * FROM `users` FULL JOIN `user_data`");

    @Getter private String query;
    DatabaseQueries(String s) {
        this.query = s;
    }
}
