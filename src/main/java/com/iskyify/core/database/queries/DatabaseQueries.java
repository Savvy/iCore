package com.iskyify.core.database.queries;

import lombok.Getter;

public enum DatabaseQueries {

    CREATE_USERS_TABLE("CREATE TABLE `users` (`id` int(5) AUTO_INCREMENT NOT NULL, `unique_id` varchar(36) NOT NULL UNIQUE, PRIMARY KEY(id)) ENGINE=InnoDB;"),
    CREATE_USER_DATA_TABLE("CREATE TABLE `user_data` (`id` int(5) NOT NULL, `last_joined` int(9) NOT NULL, `suffix` varchar(9) NULL, PRIMARY KEY(id),  FOREIGN KEY (id) REFERENCES users(id)) ENGINE=InnoDB;"),
    SELECT_USER("SELECT * FROM `users` WHERE `unique_id` = ? FULL JOIN `user_data`;");

    @Getter private String query;
    DatabaseQueries(String s) {
        this.query = s;
    }
}
