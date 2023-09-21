package postgresql.DAO;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceConfiguration {
    private static volatile DataSourceConfiguration instance;
    private PGSimpleDataSource dataSource;

    private DataSourceConfiguration(){
        this.dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("center_mgmt");
        dataSource.setUser("postgres");
        dataSource.setPassword("1213");
    }

    public static DataSourceConfiguration getInstance() {
        if (instance == null) {
            synchronized (DataSourceConfiguration.class) {
                if (instance == null) {
                    instance = new DataSourceConfiguration();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
