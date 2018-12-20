package cc.lucene.utils;

import cc.lucene.persistence.dao.TextMapper;
import cc.lucene.persistence.entity.Text;
import cc.lucene.persistence.entity.TextExample;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/simple/mysql/")
public class MysqlLucene {
    @Autowired
    TextMapper textMapper;

    //  创建索引
    @RequestMapping("/create")
    public void mysqlAlay() throws IOException {
        List<Text> texts = allText();
        Analyzer analyzer = new StandardAnalyzer();
        Path path = null;
        path = Paths.get("E:\\index\\");
        Directory index = FSDirectory.open(path);
//        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        config.set
        IndexWriter writer = new IndexWriter(index, config);
        for (Text text : texts) {
            Document doc = new Document();
            doc.add(new TextField("title", text.getTitle(), Field.Store.YES));
            doc.add(new TextField("context", text.getContext(), Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();
    }

    //    根据索引查询信息
    @RequestMapping("/select")
    public void select(HttpServletRequest request) throws IOException, ParseException {
        String xxx = request.getParameter("title");
        String yyy = request.getParameter("context");
        List<Text> texts = allText();
        Analyzer analyzer = new StandardAnalyzer();
//        Query query = new QueryParser("context", analyzer).parse(xxx);
//        多条件语法解析器
        QueryParser queryParser = new QueryParser("*", analyzer);
        Query query = queryParser.parse("title:" + xxx + "OR context:" + yyy);
        Directory index = FSDirectory.open(Paths.get("E:\\index\\"));
//        Directory index = new RAMDirectory();
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 10;

        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;
        for (int i = 0; i < hits.length; ++i) {
            Document doc = searcher.doc(hits[i].doc);
            ScoreDoc scoreDoc = hits[i];
            int docId = scoreDoc.doc;
            Document d = searcher.doc(docId);
            List<IndexableField> fields = d.getFields();
            System.out.print((i + 1));
            System.out.print("\t" + scoreDoc.score);
            for (IndexableField f : fields) {
                System.out.print("\t" + d.get(f.name()));
            }
            System.out.println();
        }
    }

    private List<Text> allText() {
        return textMapper.selectByExampleWithBLOBs(new TextExample());
    }

}
