package com.knl.lucene.sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexWriterSample {
	public static void main(String[] args) throws Exception{
		// ==== 创建索引目录
		Directory dic = FSDirectory.open(new File("G:/lucene"));
		// ==== 创建文档结构
		List<Document> docs = new ArrayList<Document>();
		Document doc00001 = new Document();
		doc00001.add(new StringField("bookId", "000001", Store.YES));
		doc00001.add(new StringField("bookName", "钢铁是怎样炼成的", Store.YES));
		
		Document doc00002 = new Document();
		doc00002.add(new StringField("bookId", "000002", Store.YES));
		doc00002.add(new StringField("bookName", "钢的世界", Store.YES));
		
		Document doc00003 = new Document();
		doc00003.add(new StringField("bookId", "000003", Store.YES));
		doc00003.add(new StringField("bookName", "钢铁战士", Store.YES));
		
		Document doc00004 = new Document();
		doc00004.add(new StringField("bookId", "000004", Store.YES));
		doc00004.add(new StringField("bookName", "英雄儿女", Store.YES));
		
		docs.add(doc00001);
		docs.add(doc00002);
		docs.add(doc00003);
		docs.add(doc00004);
		// ==== 创建索引配置
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
		// ==== 创建索引
		IndexWriter writer = new IndexWriter(dic, config);
		writer.addDocuments(docs);
		writer.commit();
		writer.close();
		System.out.println("索引创建成功！");
	}
}
