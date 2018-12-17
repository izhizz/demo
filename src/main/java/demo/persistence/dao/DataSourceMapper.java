package demo.persistence.dao;

import demo.persistence.entity.DataSource;
import demo.persistence.entity.DataSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataSourceMapper {
    int deleteByExample(DataSourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataSource record);

    int insertSelective(DataSource record);

    List<DataSource> selectByExample(DataSourceExample example);

    int updateByExampleSelective(@Param("record") DataSource record, @Param("example") DataSourceExample example);

    int updateByExample(@Param("record") DataSource record, @Param("example") DataSourceExample example);
}