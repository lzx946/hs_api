package top.tsama.baseapiservicedao.mapper;

import java.util.List;
import top.tsama.baseapiservicedomain.model.Backgroudinfo;

public interface BackgroudinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Backgroudinfo record);

    Backgroudinfo selectByPrimaryKey(Integer id);

    List<Backgroudinfo> selectAll();

    int updateByPrimaryKey(Backgroudinfo record);
}