package  by.academy.it.pool;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static by.academy.it.resources.constant.Constants.*;

public class SimpleBasicDataSource {
    private static SimpleBasicDataSource instance;
    private BasicDataSource bds;

    private SimpleBasicDataSource() {
        bds = new BasicDataSource();
        bds.setDriverClassName(PATH_JDBC_DRIVER);
        bds.setUrl(ADDRESS_JDBC);
        bds.setUsername(PARAM_LOGIN);
        bds.setPassword(PARAM_PASS);
    }

    public synchronized static SimpleBasicDataSource getInstance() {
        if (instance == null) {
            instance = new SimpleBasicDataSource();
        }
        return instance;
    }

    public Connection getConection() throws SQLException {
        return bds.getConnection();
    }
}
