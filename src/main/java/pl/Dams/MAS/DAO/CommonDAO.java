package pl.Dams.MAS.DAO;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.Dams.MAS.Models.IModel;
import pl.Dams.MAS.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

public abstract class CommonDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonDAO.class);
    protected final ConnectionSource connectionSource;

    protected CommonDAO() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends IModel, I> void createOrUpdate(IModel iModel) {
        Dao<T, I> dao = getDao((Class<T>) iModel.getClass());
        try {
            dao.createOrUpdate((T) iModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends IModel, I> void refresh(IModel iModel) {
        Dao<T, I> dao = getDao((Class<T>) iModel.getClass());
        try {
            dao.refresh((T) iModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends IModel, I> void delete(IModel iModel) {
        Dao<T, I> dao = getDao((Class) iModel.getClass());
        try {
            dao.delete((T) iModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public <T extends IModel, I> List<T> queryForAll(Class<T> cls){
        Dao<T, I> dao = getDao(cls);
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public <T extends IModel, I> T queryForId(Class<T> cls, I id) {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId(id);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    public <T extends IModel, I> Dao<T, I> getDao(Class<T> cls) {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

}
