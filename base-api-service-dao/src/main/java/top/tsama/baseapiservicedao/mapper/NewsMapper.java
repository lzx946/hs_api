package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.News;
@Component
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<News> selectAll(News news);

    int updateByPrimaryKey(News record);
}