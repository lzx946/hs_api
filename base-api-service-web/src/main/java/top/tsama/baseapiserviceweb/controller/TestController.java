package top.tsama.baseapiserviceweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tsama.baseapiservicedomain.model.News;
import top.tsama.baseapiservicedomain.model.TbTest;

import java.util.List;

@RestController
public class TestController {

    Logger log = LoggerFactory.getLogger(getClass());

   /* @Autowired*/
   /* private TestServiceImpl testServiceImpl;*/

   /* @RequestMapping(value = "/")
    public List<News> home() {
        log.info(testServiceImpl.selectAll().toString());
        return testServiceImpl.selectAll();
    }*/
}
