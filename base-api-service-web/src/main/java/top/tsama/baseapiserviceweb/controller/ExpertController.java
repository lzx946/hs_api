package top.tsama.baseapiserviceweb.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiserviceapi.ExpertService;
import top.tsama.baseapiserviceapi.HomeDataService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicecommon.webUtil;
import top.tsama.baseapiservicedomain.model.Course;
import top.tsama.baseapiservicedomain.model.ExpertsVoinfo;
import top.tsama.baseapiservicedomain.model.Expertsinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@RestController
@RequestMapping("expert")
public class ExpertController {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private ExpertService expertService;
    @Autowired
    private HomeDataService homeDataService;

    /**
     * 获取专家列表及详情
     * @param request
     * @param response
     * @param pagination
     * @return
     */
    @RequestMapping("getExpertlist")
    public List<ExpertsVoinfo> getExpertlist(HttpServletRequest request, HttpServletResponse response, Pagination pagination){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        ExpertsVoinfo expertsVoinfo=new ExpertsVoinfo();
        String id=request.getParameter("id");
        if(id!=null&&!"".equals(id)){
            expertsVoinfo.setId(Integer.parseInt(id));
        }
        List<ExpertsVoinfo> expertsVoinfoList=expertService.selectAll(expertsVoinfo,pagination);
        PageInfo<ExpertsVoinfo> page=new PageInfo<ExpertsVoinfo>(expertsVoinfoList);
        if(expertsVoinfoList!=null){
            logger.info("查询专家列表及详情成功");
            return expertsVoinfoList;
//            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "查询专家列表成功", expertsVoinfoList, page.getTotal());
        }
        logger.error("查询专家列表及详情失败");
        return null;
//        return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "查询专家列表失败", null, 0);
    }

    /**
     * 更新专家信息
     * @param request
     * @param response
     * @param expertsinfo
     * @param pagination
     * @return
     */
    @RequestMapping("updateExpertinfo")
    public String updateExpertinfo(HttpServletRequest request, HttpServletResponse response, Expertsinfo expertsinfo,Pagination pagination){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String flag=request.getParameter("id");
        if(flag==null){
            return null;
        }
//        if(flagemail!=null&&flagemail!=""&&Integer.parseInt(flagemail)==0&&expertsinfo.getEmail()!=null){
//            ExpertsVoinfo expertsVoinfo=new ExpertsVoinfo();
//            expertsVoinfo.setId(expertsinfo.getId());
//            List<ExpertsVoinfo> expertsVoinfoList=expertService.selectAll(expertsVoinfo,pagination);
//            if(expertsVoinfoList.get(0).getEmail()==expertsinfo.getEmail()){
//                ExpertsVoinfo expertsVoinfovery=new ExpertsVoinfo();
//                expertsVoinfovery.setEmail(expertsinfo.getEmail());
//                List<ExpertsVoinfo> expertsVoinfos=expertService.selectAll(expertsVoinfovery,pagination);
//                if(expertsVoinfos.size()==1){
//                    return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱已存在", null);
//                }
//            }
//                Expertsinfo expertsinfoup=new Expertsinfo();
//                expertsinfoup.setEmail(expertsinfo.getEmail());
//                expertsinfoup.setId(expertsinfo.getId());
//                boolean flag=expertService.updateByPrimaryKey(expertsinfoup);
//                if(flag==true){
//                    return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新邮箱成功", flag);
//                }
//            return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱更新失败", null);
//        }
//        expertsinfo.setEmail(null);
        boolean flagup=expertService.updateByPrimaryKey(expertsinfo);
        if(flagup==true){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新专家信息成功", flagup);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "更新专家信息失败", null);
    }
    /**
     * 根据专家id查询课程信息
     * @param request
     * @param response
     * @param pagination
     * @return
     */
    @RequestMapping("getCoursebyteacherid")
    public String getCoursebyteacherid(HttpServletRequest request, HttpServletResponse response,Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String teacherid = request.getParameter("teacherid");
        if (teacherid != null && teacherid != "") {
            List<Course> courseList = homeDataService.selectCoursebyteacher(Integer.parseInt(teacherid), pagination);
            PageInfo<Course> page = new PageInfo<Course>(courseList);
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "专家查询课程成功", courseList, page.getTotal());
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "专家查询课程失败", null);
    }
}
