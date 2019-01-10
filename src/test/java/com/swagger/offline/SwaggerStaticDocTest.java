package com.swagger.offline;

import cc.demo.persistence.entity.DataSource;
import com.alibaba.fastjson.JSON;
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
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
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
 * @Copyright 2016-2018  - Powered By 研发中心
 * @author: 王延飞
 * @date: 2018-01-22 16:06
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
        restDocumentation2.beforeTest(SwaggerStaticDocTest.class,"TestApi");
//        restDocumentation.beforeOperation();
//        ManualRestDocumentation
    }




//
    @Test
    public void Test() throws Exception {
        // 得到swagger.json,写入outputDir目录中
        System.out.println("Bbbbbb");
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
        System.out.println("aaaaaa");
        MockHttpServletRequestBuilder accept = get("/userInfo/selectContacts")
                .param("name", "FLY")
                .accept(MediaType.APPLICATION_JSON);
        OperationResponsePreprocessor operationResponsePreprocessor = preprocessResponse(prettyPrint());
        mockMvc.perform(accept)
                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation
                        .document("selectContacts"));
//                .andDo(selectContacts);


        DataSource dataSource = new DataSource();
        mockMvc.perform(get("/userInfo/selectAllUsers").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(dataSource))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcRestDocumentation.document("selectAllUsers", preprocessResponse(prettyPrint())));
    }

}
