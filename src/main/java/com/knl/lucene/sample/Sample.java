package com.knl.lucene.sample;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Sample {
	public static void main(String[] args) throws IOException{
		// ==== 文件所在目录位置
		Directory dic = FSDirectory.open(new File("G:/lucene"));
		// ==== 创建文档结构
		Document doc = new Document();
		doc.add(new StringField("serialNumber", "13671803404", Store.YES));
		doc.add(new StringField("serialNUmber", "13671803405", Store.YES));
		// ==== 索引配置设置
		IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
		// ==== 创建索引
		IndexWriter writer = new IndexWriter(dic, indexConfig);
		writer.addDocument(doc);
		writer.commit();
		writer.close();
		System.out.println("索引建立成功!");
	}
}
