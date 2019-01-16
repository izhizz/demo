package com.swagger.offline;

import cc.demo.persistence.entity.DataSource;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.SwaggerResultHandler;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import com.swagger.offline.model.User;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version V1.0
 * @Title:
 * @ClassName: SwaggerStaticDocTest.java
 * @Description:
 * @Copyright
 * @author: ff
 * @date:  2019/1/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:*.xml")
public  class SwaggerStaticDocTest  {


    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    private String snippetDir = "target/generated-snippets";
    private String outputDir = "target/asciidoc";
//    public JUnitRestDocumentation restDocumentation =
//            new JUnitRestDocumentation("target/generated-snippets");
    public ManualRestDocumentation restDocumentation2 =
            new ManualRestDocumentation("target/generated-snippets");

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation2)).build();
//        当前类的class 及 运行的方法
        restDocumentation2.beforeTest(SwaggerStaticDocTest.class,"TestApi");
    }
//
    @Test
    public void Test() throws Exception {
        // 得到swagger.json,写入outputDir目录中
        mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
                .andExpect(status().isOk())
                .andReturn();

        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .withExamples(snippetDir)
                .build()
                .intoFolder(outputDir);// 输出
    }

    @Test
    public void TestApi() throws Exception {
//        访问接口
        MockHttpServletRequestBuilder accept = get("/userInfo/selectContacts")
                .param("name", "FLY")//参数
                .accept(MediaType.APPLICATION_JSON);//返回类型
        mockMvc.perform(accept)
                .andExpect(status().isOk())//判断是否成功
//                .andDo(MockMvcResultHandlers.print()) //打印详细信息
                .andDo(MockMvcRestDocumentation
                        .document("selectContacts"));
//                .andDo(selectContacts);


        DataSource dataSource = new DataSource();
        dataSource.setId(1);
        dataSource.setName("2");
        mockMvc.perform(get("/userInfo/selectAllUsers").contentType(MediaType.APPLICATION_JSON)
//                .param("page","1").param("size","1") //参数请求
//                .content(JSON.toJSONString(dataSource))//设置RequestBody请求
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcRestDocumentation.document("selectAllUsers", preprocessResponse(prettyPrint())));
    }

}
