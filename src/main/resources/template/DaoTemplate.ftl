package ${packagePath};


import ${packagePath}.${entityName};
import java.util.List;
/**
 * @author ${author}
 * @version 1.0 ${.now}
 */
public interface ${daoName}{

   int insert(${entityName} ${entityName?uncap_first});

   List<${entityName}> selectList(${entityName} ${entityName?uncap_first});

   int update(${entityName} ${entityName?uncap_first});

   int updateNotEmpty(${entityName} ${entityName?uncap_first});
}
