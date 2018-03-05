package top.tsama.baseapiserviceweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiserviceapi.CommentService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicedomain.model.Comment;
import top.tsama.baseapiservicedomain.model.CommentDetail;
import top.tsama.baseapiservicedomain.model.HomeDataDetail;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment")
    public List<CommentDetail> comment(HttpServletResponse response,Integer cid){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(cid==null){
            return null;
        }
        List<CommentDetail> list =commentService.selectByCid(cid);
        return list;
    }

    @RequestMapping(value = "/commentSubmit")
    public int commentSubmit(HttpServletResponse response,Comment comment){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(comment==null){
            return 0;
        }
        int flag =commentService.insert(comment);
        return flag;
    }


}
