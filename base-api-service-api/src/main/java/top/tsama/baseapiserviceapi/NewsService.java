package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.News;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
public interface NewsService {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    /**
     * 查询新闻信息
     * @param news
     * @param pagination
     * @return
     */
//    List<News> selectAll(News news,Pagination pagination);

    List<News> selectAll(News news);

    int updateByPrimaryKey(News record);
}
