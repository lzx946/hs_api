package top.tsama.baseapiserviceweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicedomain.model.HomeData;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String homeData(){
        return "index";
    }
}
