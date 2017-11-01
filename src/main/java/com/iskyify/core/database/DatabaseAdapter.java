package com.iskyify.core.database;

import com.iskyify.core.iCore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseAdapter {

    private static DatabaseAdapter instance;
    private iCore coreInstance;
    private JdbcTemplate jdbcTemplate;
    private DatabaseAdapter() {
        instance = this;
        coreInstance = iCore.getInstance();
    }

    public void check() {
        if (jdbcTemplate != null) {
            return;
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(
                String.format("jdbc:mysql://%s:%s/%s", coreInstance.getConfig().getString("mysql.hostname"),
                        coreInstance.getConfig().getString("mysql.port"), coreInstance.getConfig().getString("mysql.database")));
        dataSource.setUsername(coreInstance.getConfig().getString("mysql.username"));
        dataSource.setPassword(coreInstance.getConfig().getString("mysql.password"));
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate get() {
        check();
        return jdbcTemplate;
    }

    public static DatabaseAdapter getInstance() {
        return ( (instance == null) ? instance = new DatabaseAdapter() : instance );
    }
}