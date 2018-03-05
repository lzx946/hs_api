package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.Course;
import top.tsama.baseapiservicedomain.model.HomeData;
import top.tsama.baseapiservicedomain.model.HomeDataDetail;

import java.util.List;

public interface HomeDataService {


    /**
     * 获取首页海报数据
     *
     * @return
     */
    List<HomeData> selectHomeData();

    /**
     * 获取首页详情数据
     *
     * @return
     */
    HomeDataDetail selectHomeDataDetail(Integer id);

    /**
     * 批量插入信息
     * @param record
     * @return
     */
    boolean insertBatch(List<Course> record);

    /**
     * 根据专家ID获取课程信息
     * @param teacherid
     * @return
     */
    List<Course> selectCoursebyteacher(Integer teacherid, Pagination pagination);
}
