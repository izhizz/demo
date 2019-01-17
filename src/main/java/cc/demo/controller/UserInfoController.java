package cc.demo.controller;

import cc.demo.persistence.entity.DataSource;
import cc.demo.utils.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userInfo")
@Api(value = "用户信息查询", description = "用户基本信息操作API", tags = "UserApi")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //    @ResponseBody
    @RequestMapping(value = "/selectAllUsers", method = RequestMethod.GET)
    @ApiOperation(value = "/selectAllUsers", notes = "查询所有的人员信息并分页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "跳转到的页数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页展示的记录数", required = true, paramType = "query", dataType = "Integer")
    })
    public ResultEntity<DataSource> selectAllUsers(Integer page, Integer size) {
        DataSource dataSource = new DataSource();
        dataSource.setId(1);
        Map aa = new HashMap<>();
        aa.put("d",dataSource);
        ResultEntity<DataSource> resultEntity = new ResultEntity(aa); //A:
        return  resultEntity;
    }
    @RequestMapping(value = "/selectAllUsers2", method = RequestMethod.GET)
    @ApiOperation(value = "/selectAllUsers2", notes = "查询所有的人员信息并分页展示")
//    @ApiOperation(value = "查询所有的人员信息并分页展示", notes = "查询所有的人员信息并分页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "跳转到的页数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页展示的记录数", required = true, paramType = "query", dataType = "Integer")
    })
    public DataSource selectAllUsers1(Integer page, Integer size) {
        DataSource dataSource = new DataSource();
        return dataSource;
    }

//    @ResponseBody
//    @ApiOperation(value = "/selectContacts", notes = "根据姓名查询用户信息 ")
//    @RequestMapping(value = "/selectContacts", method = RequestMethod.GET)
////    @ApiOperation(value = "查询通讯录人员信息", notes = "查询通讯录人员信息")
//    public ResultEntity selectContacts() {
//        return new ResultEntity();
//
//    }
}

//@ApiModel(value = "user对象", description = "用户对象user")
//public class DataSource implements Serializable {
//    @ApiModelProperty(value = "id")
//    private Integer id;
//
//    @ApiModelProperty(value = "姓名")
//    private Integer name;
//
//    public Integer getName() {
//        return name;
//    }
//
//    public void setName(Integer name) {
//        this.name = name;
//    }