package top.tsama.baseapiserviceweb.controller;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiserviceapi.HomeDataService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.DateUtils;
import top.tsama.baseapiservicecommon.webUtil;
import top.tsama.baseapiservicedomain.model.Course;
import top.tsama.baseapiservicedomain.model.HomeData;
import top.tsama.baseapiservicedomain.model.HomeDataDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HomeController {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    HomeDataService homeDataService ;

    /**
     * 获取首页Poster数据
     * @param response
     * @return
     */
    @RequestMapping("/homeData")
    public List<HomeData> homeData(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        List<HomeData> list =homeDataService.selectHomeData();
        return list;
    }

    /**
     * 根据课程id获取Poster数据详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/homeDataDetail" )
    public HomeDataDetail homeDataDetail(HttpServletResponse response, Integer id){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(id==null){
            return null;
        }
        HomeDataDetail homeData =homeDataService.selectHomeDataDetail(id);
        return homeData;
    }
    @RequestMapping("/insertCourse")
    public String insertCourse(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        String name=request.getParameter("courseName");
        String summary=request.getParameter("courseIntroduction");
        String teacherid=request.getParameter("teacherid");
        String info=request.getParameter("timeLocals");
        List<Course> courses=new ArrayList<Course>();
        JSONArray jsonArray = JSONArray.fromObject(info);
        for(int i=0;i<jsonArray.size();i++){
            Course course=new Course();
            course.setName(name);
            course.setSummary(summary);
            course.setTeacherid(Integer.parseInt(teacherid));
            course.setContent(999);
            course.setClassroom(((JSONObject)jsonArray.get(i)).getString("ban"));
            course.setRoom(((JSONObject)jsonArray.get(i)).getString("local"));
            course.setStarttime(DateUtils.getFormatDate(((JSONObject)jsonArray.get(i)).getString("timeStart"),DateUtils.yyyyMMdd));
            course.setStoptime(DateUtils.getFormatDate(((JSONObject)jsonArray.get(i)).getString("timeStop"),DateUtils.yyyyMMdd));
            course.setCreatetime(new Date());
            course.setState(0);//待审核
            courses.add(course);
       }
       try{
           boolean flag= homeDataService.insertBatch(courses);
           if(flag==true){
               return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "课程提交成功", flag);
           }
       }
       catch (Exception  e){
            e.printStackTrace();
       }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "课程提交失败", null);
    }

}
