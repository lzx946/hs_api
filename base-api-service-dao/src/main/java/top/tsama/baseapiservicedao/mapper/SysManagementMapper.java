package top.tsama.baseapiservicedao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import top.tsama.baseapiservicedomain.model.SysManagement;
@Component
public interface SysManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysManagement record);

    SysManagement selectByPrimaryKey(Integer id);

    List<SysManagement> selectAll();

    int updateByPrimaryKey(SysManagement record);
}