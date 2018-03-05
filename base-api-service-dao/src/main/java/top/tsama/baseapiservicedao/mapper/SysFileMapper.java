package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.SysFile;
@Component
public interface SysFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    SysFile selectByPrimaryKey(Integer id);

    List<SysFile> selectAll();

    int updateByPrimaryKey(SysFile record);
}