package cc.demo.controller;

import cc.demo.persistence.dao.DataSourceMapper;
import cc.demo.persistence.entity.DataSource;
import cc.demo.persistence.entity.DataSourceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class test {
    @Autowired
    DataSourceMapper dataSourceMapper;

    @RequestMapping("/login")
    public String xxx(){
        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }

    @RequestMapping("/register")
    public String yyy(){
        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }
}
