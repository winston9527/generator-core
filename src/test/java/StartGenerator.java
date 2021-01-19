import com.winston.code.generator.core.common.enums.DBTypeEnum;
import com.winston.code.generator.core.factory.QueryServiceFactory;
import com.winston.code.generator.core.service.QueryService;
import com.winston.code.generator.core.ui.TabColumnFrame;
import com.winston.code.generator.core.util.PropertiesHolder;
import com.winston.code.generator.core.util.SqlSessionFactoryUtil;

public class StartGenerator {
    public static void main(String[] args) {

        String url = "jdbc:mysql://1111/task_service";
        try {
            SqlSessionFactoryUtil
              .createSqlSessionFactoryInstance(DBTypeEnum.MYSQL, url, "1111", "111");
            PropertiesHolder.setDataBase("ddd");
            PropertiesHolder.setDbTypeEnum(DBTypeEnum.MYSQL);
            QueryService queryService = QueryServiceFactory.getQueryServiceByDbType(DBTypeEnum.MYSQL);
            queryService.queryUserTables(null);
        } catch (Exception ep) {
            ep.printStackTrace();
            return;
        }
        //new TableNameFrame("","");
        new TabColumnFrame("bill_items", "", "", url);
        // new MyDialog(new TableNameFrame("",""));
    }
}
