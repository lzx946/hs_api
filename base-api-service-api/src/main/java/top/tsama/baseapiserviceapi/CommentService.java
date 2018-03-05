package top.tsama.baseapiserviceapi;

import top.tsama.baseapiservicedomain.model.Comment;
import top.tsama.baseapiservicedomain.model.CommentDetail;

import java.util.List;

public interface CommentService {
    /**
     * 根据课程ID查询评论列表
     * @param cid
     * @return
     */
    List<CommentDetail> selectByCid(Integer cid);

    /**
     * 插入一条评论
     * @param record
     * @return
     */
    int insert(Comment record);
}
