package com.knl.lucene.sample;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 * 前缀搜索
 * 
 * @author yanglikai
 *
 */
public class PrefixQuerySample {
	public static void main(String[] args) throws IOException{
		// ==== 索引文件目录
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("G:/lucene")));
		// ==== 索引查询器
		IndexSearcher searcher = new IndexSearcher(reader);
		// ==== 查询条件
		Query prefixQuery = new PrefixQuery(new Term("bookName", "钢"));
		// ==== 查询处理
		TopDocs topDocs = searcher.search(prefixQuery, 10);
		// ==== 处理查询结果
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for(ScoreDoc scoreDoc : scoreDocs){
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("bookId"));
			System.out.println(doc.get("bookName"));
		}
	}
}
