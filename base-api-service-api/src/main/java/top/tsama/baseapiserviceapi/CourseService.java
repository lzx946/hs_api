package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedomain.model.CourseVo;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/25.
 */
public interface CourseService {
    /**
     * 根据专家id和课程名查询不同地点的课程
     * @param courseVo
     * @return
     */
    List<CourseVo> selectByteach(CourseVo courseVo);
    /**
     * 查询所有的课程
     * @return
     */
    List<CourseVo> selectAll(CourseVo courseVo, Pagination pagination);
}
