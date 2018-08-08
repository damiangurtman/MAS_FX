package pl.Dams.MAS.Utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.Dams.MAS.Models.*;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    public static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_H2 = "jdbc:h2:./database";
    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_H2, "admin", "");
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {
        if(connectionSource == null)
            createConnectionSource();
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if(connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Produkt.class);
            TableUtils.createTableIfNotExists(connectionSource, Pracownik.class);
            TableUtils.createTableIfNotExists(connectionSource, Specjalizacja.class);
            TableUtils.createTableIfNotExists(connectionSource, AdresZamieszkania.class);
            TableUtils.createTableIfNotExists(connectionSource, KlientNieaktywny.class);
            TableUtils.createTableIfNotExists(connectionSource, KlientNormalny.class);
            TableUtils.createTableIfNotExists(connectionSource, ZamowienieDetal.class);
            TableUtils.createTableIfNotExists(connectionSource, KlientPracownik.class);
            TableUtils.createTableIfNotExists(connectionSource, ZamowienieHurt.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Produkt.class, true);
            TableUtils.dropTable(connectionSource, Pracownik.class, true);
            TableUtils.dropTable(connectionSource, Specjalizacja.class, true);
            TableUtils.dropTable(connectionSource, AdresZamieszkania.class, true);
            TableUtils.dropTable(connectionSource, KlientNieaktywny.class, true);
            TableUtils.dropTable(connectionSource, KlientNormalny.class, true);
            TableUtils.dropTable(connectionSource, ZamowienieDetal.class, true);
            TableUtils.dropTable(connectionSource, KlientPracownik.class, true);
            TableUtils.dropTable(connectionSource, ZamowienieHurt.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }


}
