package top.tsama.baseapiserviceweb.controller;

import net.sf.json.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiserviceapi.SysDictonaryService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.webUtil;
import top.tsama.baseapiservicedomain.model.SysDictionary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangdaren on 2018/3/26.
 */
@RestController
@RequestMapping("sysdictionary")
public class SysDictionaryController {
    @Autowired
    private SysDictonaryService sysDictonaryService;

    @RequestMapping("getDictionary")
    public String getDictionary(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        SysDictionary sysDictionary=new SysDictionary();
        List<SysDictionary> sysDictionaryList=sysDictonaryService.selectAll(sysDictionary);
        if(sysDictionaryList!=null){
            return webUtil.result(webUtil.FLAG_SUCCESS,webUtil.ERROR_CODE_SUCCESS,"查询成功",sysDictionaryList);
        }
        return webUtil.result(webUtil.FLAG__FAILED,webUtil.ERROR_CODE_ILLEGAL,"查询失败",null);
    }
}
