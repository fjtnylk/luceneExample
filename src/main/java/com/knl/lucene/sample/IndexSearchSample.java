package com.knl.lucene.sample;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class IndexSearchSample {
	public static void main(String[] args) throws Exception{
		// ==== 索引所在目录
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("G:/lucene")));
		// ==== 索引查询器
		IndexSearcher search = new IndexSearcher(reader);
		// ==== 查询条件
		Query query = new TermQuery(new Term("bookId", "000001"));
		// ==== 查询结果
		TopDocs topDocs = search.search(query, 10);
		// ==== 处理查询结果
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for(int i = 0; i < scoreDocs.length; i++){
			Document doc = search.doc(scoreDocs[i].doc);
			System.out.println(doc.get("bookId"));
			System.out.println(doc.get("bookName"));
		}
	}
}
