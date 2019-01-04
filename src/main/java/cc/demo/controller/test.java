package cc.demo.controller;

import cc.demo.persistence.dao.DataSourceMapper;
import cc.demo.persistence.entity.DataSource;
import cc.demo.persistence.entity.DataSourceExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
@Api(value = "test")
public class test {
//    @Autowired
//    DataSourceMapper dataSourceMapper;

    @RequestMapping("/login")
    public String xxx(){
//        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }

    @RequestMapping("/register")
    @ResponseBody
    @ApiOperation(value = "测试",httpMethod = "GET")
    public String yyy(){
//        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }
}
