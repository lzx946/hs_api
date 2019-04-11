package top.tsama.baseapiserviceweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.tsama.baseapiserviceapi.NewsService;

import javax.annotation.Resource;

/**
 * Created by wangdaren on 2018/7/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {
    @Resource
    private NewsService newsService;
    @Test
    public void getnews(){
        System.out.println(newsService.selectAll(null).toString());
    }
}
