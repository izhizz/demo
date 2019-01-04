package cc.lucene.persistence.dao;

import cc.lucene.persistence.entity.Text;
import cc.lucene.persistence.entity.TextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TextMapper {
    int deleteByExample(TextExample example);

    int deleteByPrimaryKey(String id);

    int insert(Text record);

    int insertSelective(Text record);

    List<Text> selectByExampleWithBLOBs(TextExample example);

    List<Text> selectByExample(TextExample example);

    Text selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Text record, @Param("example") TextExample example);

    int updateByExampleWithBLOBs(@Param("record") Text record, @Param("example") TextExample example);

    int updateByExample(@Param("record") Text record, @Param("example") TextExample example);

    int updateByPrimaryKeySelective(Text record);

    int updateByPrimaryKeyWithBLOBs(Text record);

    int updateByPrimaryKey(Text record);
}