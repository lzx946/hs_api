package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.Course;
import top.tsama.baseapiservicedomain.model.CourseVo;
import top.tsama.baseapiservicedomain.model.HomeData;
import top.tsama.baseapiservicedomain.model.HomeDataDetail;

@Component
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量插入课程
     * @param record
     * @return
     */
    int insertBatch(List<Course> record);

    Course selectByPrimaryKey(Integer id);

    List<CourseVo> selectAll(CourseVo courseVo);


    /**
     * 更新课程
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);

    /**
     * 获取首页海报数据
     * @return
     */
    List<HomeData> selectHomeData();
    /**
     * 获取学习数据
     * @return
     */
    List<HomeData> selectHomeDataList();

    /**
     * 获取首页详情数据
     * @return
     */
    HomeDataDetail selectHomeDataDetail(Integer id);

    /**
     * 根据专家查询课程
     * @param teacherid
     * @return
     */
    List<Course> selectCoursebyteacher(Integer teacherid);

    /**
     * 根据专家id和课程名查询不同地点的课程
     * @param courseVo
     * @return
     */
    List<CourseVo> selectByteach(CourseVo courseVo);
}