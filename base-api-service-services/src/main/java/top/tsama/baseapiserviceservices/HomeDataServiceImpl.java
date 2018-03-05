package top.tsama.baseapiserviceservices;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.HomeDataService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.DateUtils;
import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicedao.mapper.CourseMapper;
import top.tsama.baseapiservicedomain.model.Course;
import top.tsama.baseapiservicedomain.model.HomeData;
import top.tsama.baseapiservicedomain.model.HomeDataDetail;

import java.util.List;

@Service
public class HomeDataServiceImpl implements HomeDataService {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<HomeData> selectHomeData() {
        try {
            List<HomeData> list = courseMapper.selectHomeData();
            if (list == null || list.size() <= 0) {
                return null;
            } else {
                int sum = list.size();
                for (int i = 0; i < sum; i++) {
                    list.get(i).setCourseStartTimeStr(DateUtils.getMonth(list.get(i).getCourseStartTime()) +
                            "月" + DateUtils.getDayOfMonth(list.get(i).getCourseStartTime()) + "日"
                    );
                    list.get(i).setCourseStopTimeStr(DateUtils.getDayOfMonth(list.get(i).getCourseStopTime()) + "日"
                    );
                    list.get(i).setImgUrl(ActionUtil.ROOTURL + list.get(i).getImgUrl());
                }
                return list;
            }
        } catch (Exception e) {
            log.error("获取首页数据异常" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HomeDataDetail selectHomeDataDetail(Integer id) {
        try {
            HomeDataDetail homeData = courseMapper.selectHomeDataDetail(id);
            homeData.setCourseStartTimeStr(DateUtils.getMonth(homeData.getCourseStartTime()) +
                    "月" + DateUtils.getDayOfMonth(homeData.getCourseStartTime()) + "日"
            );
            homeData.setCourseStopTimeStr(DateUtils.getDayOfMonth(homeData.getCourseStopTime()) + "日"
            );
            homeData.setImgUrl(ActionUtil.ROOTURL + homeData.getImgUrl());
            return homeData;
        } catch (Exception e) {
            log.error("获取首页详情数据异常" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertBatch(List<Course> record) {
        try{
            int flag=courseMapper.insertBatch(record);
            if(flag!=0){
                return true;
            }
        }catch (Exception  e){
            e.printStackTrace();
            log.error("批量插入课程信息失败"+e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> selectCoursebyteacher(Integer teacherid, Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try {
            List<Course> courses=courseMapper.selectCoursebyteacher(teacherid);
            if(courses!=null){
                return courses;
            }
        }
        catch (Exception e){
            log.error("根据专家ID查询课程失败"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
