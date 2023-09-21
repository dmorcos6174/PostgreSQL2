package postgresql.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;

public abstract class BaseDAO<T> {
    protected final DataSource dataSource;

    protected BaseDAO() {
        this.dataSource = DataSourceConfiguration.getInstance().getDataSource();
    }

    protected BaseDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void delete(UUID id, String tableName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery(tableName))) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }

    public T getById(UUID id, String tableName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getSelectByIdQuery(tableName))) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToDTO(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return null;
    }

    public List<T> getAll(String tableName) {
        List<T> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getSelectAllQuery(tableName));
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                items.add(mapResultSetToDTO(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return items;
    }

    public abstract void insert(T item, String tableName);

    public abstract void update(T item, String tableName);

    protected abstract String getInsertQuery(String tableName);

    protected abstract String getUpdateQuery(String tableName);

    protected String getDeleteQuery(String tableName){
        return "DELETE FROM " + tableName + " WHERE id = ?";
    }

    protected String getSelectByIdQuery(String tableName){
        return "SELECT * FROM " + tableName + " WHERE id = ?";
    }

    protected String getSelectAllQuery(String tableName){
        return "SELECT * FROM " + tableName;
    }

    protected abstract T mapResultSetToDTO(ResultSet resultSet) throws SQLException;
}
