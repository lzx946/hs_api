package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.SysDictionary;
@Component
public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    List<SysDictionary> selectAll(SysDictionary sysDictionary);

    int updateByPrimaryKey(SysDictionary record);
}