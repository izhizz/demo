package cc.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore()
@Controller
@RequestMapping("/test")
@Api(value = "test")
public class test {
//    @Autowired
//    DataSourceMapper dataSourceMapper;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String xxx(){
//        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "测试",httpMethod = "GET")
    public String yyy(){
//        List<DataSource> dataSources = dataSourceMapper.selectByExample(new DataSourceExample());
        return "login";
    }
}
