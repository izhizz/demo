package demo.controller;

import demo.persistence.dao.DataSourceMapper;
import demo.persistence.entity.DataSource;
import demo.persistence.entity.DataSourceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class test {
    @Autowired
    DataSourceMapper dataSourceMapper;

    @RequestMapping("/login")
    public String xxx(){
        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }
}
