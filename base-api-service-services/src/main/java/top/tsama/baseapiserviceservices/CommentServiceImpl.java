package top.tsama.baseapiserviceservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tsama.baseapiserviceapi.CommentService;
import top.tsama.baseapiservicecommon.ActionUtil;
import top.tsama.baseapiservicecommon.DateUtils;
import top.tsama.baseapiservicedao.mapper.CommentMapper;
import top.tsama.baseapiservicedomain.model.Comment;
import top.tsama.baseapiservicedomain.model.CommentDetail;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDetail> selectByCid(Integer cid) {
        try {
            List<CommentDetail> list = commentMapper.selectByCid(cid);
            int sum = list.size();
            for (int i = 0; i < sum; i++) {
                list.get(i).setCreateTimeStr(DateUtils.getFormattedString(list.get(i).getCreatetime(), DateUtils.yyyyMMddHHmmss));
                list.get(i).setImgUrl(ActionUtil.ROOTURL + list.get(i).getImgUrl());
            }
            return list;
        } catch (Exception e) {
            log.error("根据课程id查询评论列表失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Comment record) {
        try {
            record.setState(0);
            record.setCreatetime(new Date());
            int flag = 0;
            flag = commentMapper.insert(record);
            return flag;
        } catch (Exception e) {
            log.error("根据课程id查询评论列表失败");
            e.printStackTrace();
        }
        return 0;
    }
}
