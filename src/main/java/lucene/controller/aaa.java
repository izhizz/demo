package lucene.controller;

import lucene.service.LuceneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("aaa/")
public class aaa {
    @Resource
    LuceneService luceneService;
    @RequestMapping("b")
    public void aaa() throws IOException {
//        luceneService.createIndex();
    }

}
