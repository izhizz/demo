package lucene.service.impl;


import lucene.service.LuceneService;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;

import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class LuceneServiceImpl implements LuceneService {
    @Autowired

//    @Override
//    public void createIndex() throws IOException {
//        //创建索引
//        Path path = Paths.get("E:\\programme\\test");
//        //指定索引库的存放位置Directory对象
//        Directory directory = FSDirectory.open(path);
//        /*
//        索引库还可以存放到内存中
//        Directory directory = new RAMDirectory();
//        指定一个标准分析器，对文档内容进行分析
//        */
//
//        Analyzer analyzer = new StandardAnalyzer();
//
//        //创建indexwriterCofig对象
//        //第一个参数： Lucene的版本信息，可以选择对应的lucene版本也可以使用LATEST
//        //第二根参数：分析器对象
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//
//        //创建一个indexwriter对象
//        IndexWriter indexWriter = new IndexWriter(directory, config);
//
//        //原始文档的路径
//        File file = new File("E:\\programme\\searchsource.txt");
//        File[] fileList = file.listFiles();
//        for (File file2 : fileList) {
//            //创建document对象
//            Document document = new Document();
//
//            //创建field对象，将field添加到document对象中
//
//            //文件名称
//            String fileName = file2.getName();
//            //创建文件名域
//            //第一个参数：域的名称
//            //第二个参数：域的内容
//            //第三个参数：是否存储
//            Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
//
//            //文件的大小
////            long fileSize = FileUtils.sizeOf(file2);
//            //文件大小域
////            Field fileSizeField = new LongRange("fileSize", fileSize, Field.Store.YES);
//
//            //文件路径
//            String filePath = file2.getPath();
//            //文件路径域（不分析、不索引、只存储）
//            Field filePathField = new StoredField("filePath", filePath);
//
//            //文件内容
//            String fileContent = FileUtils.readFileToString(file2);
//            //String fileContent = FileUtils.readFileToString(file2, "utf-8");
//            //文件内容域
//            Field fileContentField = new TextField("fileContent", fileContent, Field.Store.YES);
//
//            document.add(fileNameField);
////            document.add(fileSizeField);
//            document.add(filePathField);
//            document.add(fileContentField);
//            //使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
//            indexWriter.addDocument(document);
//        }
//        //关闭IndexWriter对象。
//        indexWriter.close();
//    }

//    @Override
    public static Directory createSimpleIndex(Analyzer analyzer, List<String> products) throws IOException {
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        for (String name : products) {
            addDoc(writer, name);
        }
        writer.close();
        return index;
    }
    private static void addDoc(IndexWriter w, String name) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("name", name, Field.Store.YES));
        w.addDocument(doc);
    }

    private static void showSearchResults(IndexSearcher searcher, ScoreDoc[] hits, Query query, Analyzer analyzer)
            throws Exception {
        System.out.println("找到 " + hits.length + " 个命中.");
        System.out.println("序号\t匹配度得分\t结果");
        for (int i = 0; i < hits.length; ++i) {
            ScoreDoc scoreDoc= hits[i];
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

    public static void main(String[] args) throws Exception {
        // 1. 准备中文分词器
        Analyzer analyzer = new StandardAnalyzer();

        // 2. 索引
        List<String> productNames = new ArrayList<>();
        productNames.add("飞利浦led灯泡e27螺口暖白球泡灯家用照明超亮节能灯泡转色温灯泡");
        productNames.add("飞利浦led灯泡e14螺口蜡烛灯泡3W尖泡拉尾节能灯泡暖黄光源Lamp");
        productNames.add("雷士照明 LED灯泡 e27大螺口节能灯3W球泡灯 Lamp led节能灯泡");
        productNames.add("飞利浦 led灯泡 e27螺口家用3w暖白球泡灯节能灯5W灯泡LED单灯7w");
        productNames.add("飞利浦led小球泡e14螺口4.5w透明款led节能灯泡照明光源lamp单灯");
        productNames.add("飞利浦蒲公英护眼台灯工作学习阅读节能灯具30508带光源");
        productNames.add("欧普照明led灯泡蜡烛节能灯泡e14螺口球泡灯超亮照明单灯光源");
        productNames.add("欧普照明led灯泡节能灯泡超亮光源e14e27螺旋螺口小球泡暖黄家用");
        productNames.add("聚欧普照明led灯泡节能灯泡e27螺口球泡家用led照明单灯超亮光源");
        Directory index = createSimpleIndex(analyzer, productNames);
        System.out.println(index.toString());
        // 3. 查询器
        String keyword = "护眼";
//        String keyword = "护眼带光源";
        Query query = new QueryParser("name", analyzer).parse(keyword);

        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 2;
        System.out.printf("当前一共有%d条数据%n",productNames.size());
        System.out.printf("查询关键字是：\"%s\"%n",keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;

        // 5. 显示查询结果
        showSearchResults(searcher, hits, query, analyzer);
        // 6. 关闭查询
        reader.close();
    }
}
