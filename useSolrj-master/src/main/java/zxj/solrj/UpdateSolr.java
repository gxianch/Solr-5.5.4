package zxj.solrj;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 更新Solr,并下载数据到solrDoc.txt
 * @author Administrator
 *
 */
public class UpdateSolr {

	private String serverUrl = "http://183.230.27.135:28000/solr/ott";
	@Test
	public void search()throws Exception{
		HttpSolrClient client = new  HttpSolrClient(serverUrl);
		
		//创建查询对象
		SolrQuery query = new SolrQuery();
		//q 查询字符串，如果查询所有*:*
		query.set("q", "*:*");
		//fq 过滤条件，过滤是基于查询结果中的过滤
//		query.set("fq", "product_catalog_name:幽默杂货");
		//sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
//		query.set("sort", "product_price desc");
		//start row 分页信息，与mysql的limit的两个参数一致效果
//		query.setStart(0);
//		query.setRows(50);
		//fl 查询哪些结果出来，不写的话，就查询全部，所以我这里就不写了
//		query.set("fl", "");
		//df 默认搜索的域
//		query.set("df", "product_keywords");
		
		
		//执行搜索
		QueryResponse queryResponse = client.query(query);
		//搜索结果
		SolrDocumentList results = queryResponse.getResults();
	//	results.forEach(r -> System.out.println(r));
		//查询出来的数量
		long numFound = results.getNumFound();
		System.out.println("总查询出:" + numFound + "条记录");
		query.setStart(0);
		query.setRows((int) numFound);
		queryResponse = client.query(query);
		 results = queryResponse.getResults();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("solrDoc.txt"), "utf-8"));
		for(SolrDocument solrDocument:results) {
			out.println(solrDocument);
			SolrInputDocument inputDocument = ClientUtils.toSolrInputDocument(solrDocument);
			client.add(inputDocument);
			client.commit();
		}
		out.flush();
		out.close();
		client.close();
	}
}
