package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.Comment;
import top.tsama.baseapiservicedomain.model.CommentDetail;

@Component
public interface  CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    /**
     * 根据课程ID查询评论列表
     * @param cid
     * @return
     */
    List<CommentDetail> selectByCid(Integer cid);
}