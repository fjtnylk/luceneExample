package com.knl.lucene.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class QueryAddress {

	private Analyzer analyzer4Web = null;
	private String searchIndex4WebPath = "E:/易销_work/电信/地址库/地址库/zzaddress/zzaddress/index";

	public Map searchAddress4Web(String addr, int queryNum) throws Exception {
		long start = System.currentTimeMillis();

		IndexReader reader = null;
		if (addr == null || addr.length() == 0)
			return null;

		HashMap resultMap = new HashMap();
		ArrayList reList = new ArrayList();
		if (analyzer4Web == null) {
			analyzer4Web = new StandardAnalyzer(Version.LUCENE_44,
					CharArraySet.EMPTY_SET);
		}

		String field = "addrFull";
		Query query = null;
		String[] keys = addr.split("\\s+");
		QueryParser parser = null;
		if (keys != null && keys.length > 1) {// 多关键词查询
			String[] fields = new String[keys.length];
			for (int i = 0; i < fields.length; i++) {
				fields[i] = field;
				System.out.println(fields[i]);
			}

			parser = new MultiFieldQueryParser(Version.LUCENE_44, fields,
					analyzer4Web);
			// 默认是操作符是OR,要指定为AND
			parser.setDefaultOperator(QueryParserBase.AND_OPERATOR);
		} else { // 单关键字
			parser = new QueryParser(Version.LUCENE_44, field, analyzer4Web);
		}
		// addr_full
		query = parser.parse(addr);
		IndexSearcher is = null;
		int count = 0;

		try {
			reader = DirectoryReader.open(FSDirectory.open(new File(
					searchIndex4WebPath)));
			is = new IndexSearcher(reader);

			TopDocs docs = is.search(query, queryNum);
			if (docs == null)
				return null;

			// count = docs.totalHits;
			ScoreDoc[] scoreHits = docs.scoreDocs;
			count = scoreHits.length;
			// System.out.println("共命中"+count+"条记录");
			// 取前queryNum条数据
			int length = count;
			if (length > queryNum)
				length = queryNum;

			for (int i = 0; i < length; i++) {

				Document doc = is.doc(scoreHits[i].doc);
				System.out.println("addrId:" + doc.get("addrId") + "\taddrFull:" + doc.get("addrFull") + "\toptical:" + doc.get("optical"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭文件读入
			reader.close();
			System.out.println("执行时间:" + (System.currentTimeMillis() - start)
					+ "毫秒");
		}
		return resultMap;
	}

	public static void main(String[] args) throws Exception {
		// new QueryAddress().searchAddress4Web("4弄", 10);
		new QueryAddress().searchAddress4Web("金园一路1359弄", 15);
	}
}
