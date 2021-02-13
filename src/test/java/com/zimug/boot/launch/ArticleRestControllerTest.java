package com.zimug.boot.launch;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.controller.ArticleMockController;
import com.zimug.bootlaunch.controller.ArticleRestController;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@Transactional
@Slf4j
@SpringBootTest
public class ArticleRestControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleMockController()).build();
    }

    @Test
    public void saveArticle() throws Exception {
        String urlTemplate = "http://localhost:8888/rest/mock_articles";
        String article = "{\t\n"
                + "    \"author\": \"zimug\",\n"
                + "\t\"content\": \"你好啊\",\n"
                + "\t\"title\": \"dfdfdf\" \n"
                + "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, urlTemplate)
                                                              .contentType("application/json")
                                                              .content(article);
        //perform 执行一个RequestBuilder请求，返回ResultActions对象。
        //andExpect 添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确
        //andDo 添加ResultHandler结果处理器，比如调试时打印结果到控制台。
        MvcResult result = mockMvc.perform(requestBuilder)
                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                  .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zimug"))
                                  //.andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
                                  .andDo(print())
                                  .andReturn();

        log.info(result.getResponse().getContentAsString());

        // controller:
        /*@PostMapping("")
        public AjaxResponse saveArticle(@RequestBody ArticleVO article) {
            log.info("saveArticle：{}", article);
            return AjaxResponse.success(article);
        }*/

    }
}