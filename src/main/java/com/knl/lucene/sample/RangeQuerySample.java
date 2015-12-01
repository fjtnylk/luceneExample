package com.knl.lucene.sample;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 * 范围查询
 * 
 * @author yanglikai
 *
 */
public class RangeQuerySample {
	public static void main(String[] args) throws IOException{
		// ==== 索引所在目录
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("G:/lucene")));
		// ==== 索引查询器
		IndexSearcher searcher = new IndexSearcher(reader);
		// ==== 查询条件
//		Term beginTerm = new Term("bookId", "000001");
//		Term endTerm = new Term("bookId", "000003");
		TermRangeQuery rangeQuery = TermRangeQuery.newStringRange("bookId", "000001", "000003", true, true);
		// ==== 查询
		TopDocs topDocs = searcher.search(rangeQuery, 10);
		// ==== 处理查询结果
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for(int i = 0; i < scoreDocs.length; i++){
			Document doc = searcher.doc(scoreDocs[i].doc);
			System.out.println("bookId:" + doc.get("bookId"));
			System.out.println("bookName:" + doc.get("bookName"));
		}
		
	}
}
