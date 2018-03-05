package top.tsama.baseapiserviceweb.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiserviceapi.LoginRegisterService;
import top.tsama.baseapiserviceapi.StudentService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.DateUtils;
import top.tsama.baseapiservicecommon.Pagination;
import top.tsama.baseapiservicecommon.webUtil;
import top.tsama.baseapiservicedomain.model.StudentsVoinfo;
import top.tsama.baseapiservicedomain.model.Studentsinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@RestController
@RequestMapping("student")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private StudentService studentService;
    @Autowired
    private LoginRegisterService loginRegisterService;

    /**
     * 获取学员列表及详情
     *
     * @param request
     * @param response
     * @param pagination
     * @return
     */
    @RequestMapping("/getStudentlist")
    public  List<StudentsVoinfo> getStudentlist(HttpServletRequest request, HttpServletResponse response, Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String id = request.getParameter("id");
        StudentsVoinfo studentsVoinfo = new StudentsVoinfo();
        List<StudentsVoinfo> studentsVoinfos = new ArrayList<StudentsVoinfo>();
        if (id != null && !"".equals(id)) {
            studentsVoinfo.setId(Integer.parseInt(id));
        }
        try {
            List<StudentsVoinfo> studentsVoinfoList = studentService.selectAll(studentsVoinfo, pagination);
            PageInfo<StudentsVoinfo> page = new PageInfo<StudentsVoinfo>(studentsVoinfoList);
            if (studentsVoinfoList != null) {
                for (int i = 0; i < studentsVoinfoList.size(); i++) {
                    if (studentsVoinfoList.size() != 0) {
                        studentsVoinfoList.get(i).setUrl(ActionUtil.ROOTURL + studentsVoinfoList.get(i).getUrl());
                    }
                    if (studentsVoinfoList.size() != 0 && id == null) {
                        StudentsVoinfo studentsVoinfoshow = new StudentsVoinfo();
                        studentsVoinfoshow.setId(studentsVoinfoList.get(i).getId());
                        studentsVoinfoshow.setUrl(studentsVoinfoList.get(i).getUrl());
                        studentsVoinfoshow.setRealname(studentsVoinfoList.get(i).getRealname());
                        studentsVoinfoshow.setEnname(studentsVoinfoList.get(i).getEnname());
                        studentsVoinfoshow.setPosition(studentsVoinfoList.get(i).getPosition());
                        if (studentsVoinfoList.get(i).getOtherposition() != null) {
                            String[] othpositions = studentsVoinfoList.get(i).getOtherposition().split(",");
                            if (othpositions.length > 3) {
                                studentsVoinfoshow.setOtherposition(othpositions[0] + "," + othpositions[1] + "," + othpositions[2]);
                            } else {
                                studentsVoinfoshow.setOtherposition(studentsVoinfoList.get(i).getOtherposition());
                            }
                        }
                        studentsVoinfoshow.setCreatetime(studentsVoinfoList.get(i).getCreatetime());
                        studentsVoinfos.add(studentsVoinfoshow);
                    }
                }
                System.out.println(studentsVoinfos.size());
                if (id == null || "".equals(id)) {
                    return studentsVoinfos;
                }
                logger.info("StudentController查询学员列表成功");
                return studentsVoinfoList;
            }
        } catch (Exception e) {
            logger.error("StudentController查询学员列表失败" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新学员信息
     *
     * @param request
     * @param response
     * @param studentsinfo
     * @return
     */
    @RequestMapping("updateStudentinfo")
    public String updateStudentinfo(HttpServletRequest request, HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String flagemail = request.getParameter("flag");
        if (flagemail != null && flagemail != "" && Integer.parseInt(flagemail) == 0 && studentsinfo.getEmail() != null) {
            Studentsinfo stuvery = new Studentsinfo();
            stuvery.setEmail(studentsinfo.getEmail());
            Studentsinfo selectinfo = loginRegisterService.selectByAccountEmail(stuvery);
            if (!selectinfo.getEmail().equals(studentsinfo.getEmail())) {
                return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱已存在", null);
            }
            Studentsinfo studentsinfoup = new Studentsinfo();
            studentsinfoup.setEmail(studentsinfo.getEmail());
            studentsinfoup.setId(studentsinfo.getId());
            int flag = studentService.updateByPrimaryKey(studentsinfoup);
            if (flag == 1) {
                return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新邮箱成功", flag);
            }
            return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱更新失败", null);
        }
        studentsinfo.setEmail(null);
        int flagup = studentService.updateByPrimaryKey(studentsinfo);
        if (flagup == 1) {
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新学员信息成功", flagup);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "更新学员信息失败", null);
    }
}
