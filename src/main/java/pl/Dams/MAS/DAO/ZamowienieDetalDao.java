package pl.Dams.MAS.DAO;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import pl.Dams.MAS.DAO.CommonDAO;
import pl.Dams.MAS.Models.KlientNormalny;
import pl.Dams.MAS.Models.Zamowienie;
import pl.Dams.MAS.Models.ZamowienieDetal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZamowienieDetalDao extends CommonDAO {

    public List<ZamowienieDetal> znajdzZamowienia(KlientNormalny klientNormalny) {
        QueryBuilder<ZamowienieDetal, Object> queryBuilder = this.getDao(ZamowienieDetal.class).queryBuilder();
        List<ZamowienieDetal> zamowieniaDetal = new ArrayList<>();
        try {
            queryBuilder.where().eq("KLIENT_ID", klientNormalny);
            PreparedQuery preparedQuery = queryBuilder.prepare();
            zamowieniaDetal = this.getDao(ZamowienieDetal.class).query(preparedQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zamowieniaDetal;
    }
}
