package zxj.solrj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 增，删，改
 * @author Administrator
 *
 */
public class CommitSolr {

	private String serverUrl = "http://183.230.27.135:28000/solr/ott";
	/**
	 * 从solrDoc.txt中获取文档然后更新到serverUrl
	 * 增加与修改其实是一回事，只要id不存在，则增加，如果id存在，则是修改
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void upadteIndex() throws SolrServerException, IOException{
		HttpSolrClient client = new  HttpSolrClient(serverUrl);
		SolrInputDocument doc = new SolrInputDocument();
		BufferedReader  br = new BufferedReader(new InputStreamReader(new FileInputStream("solrDoc.txt"), "utf-8"));
		String line =null;
		while((line = br.readLine()) != null) {
			System.out.println(line);
			String name = line.substring(18, line.indexOf(","));
			String linearea = line.substring(line.indexOf("[")+1,line.indexOf("]"));
			String[] areas=	linearea.split(",");
			ArrayList<Integer> area = new ArrayList<Integer>();
			for(String s:areas) {
				area.add(Integer.parseInt(s.trim()));
			}
			ArrayList<Integer> column = new ArrayList<Integer>();
			if(line.indexOf("column=[") != -1) {
				String	linecolumn = 	line.substring(line.indexOf("column=["));
				String columns = 	linecolumn.substring(8,linecolumn.indexOf("]"));
				String[] columnStr=	columns.split(",");
				for(String s:columnStr) {
					column.add(Integer.parseInt(s.trim()));
				}
			}
			String	linespell = 	line.substring(line.indexOf("spell="));
			String spell = 	linespell.substring(6,linespell.indexOf(","));
			String	lineyear = 	line.substring(line.indexOf("year="));
			Integer year = Integer.parseInt(	lineyear.substring(5,lineyear.indexOf(",")).trim());
			ArrayList<Integer> type = new ArrayList<Integer>();
			if(line.indexOf("type=[") != -1) {
				String	linetype = 	line.substring(line.indexOf("type=["));
				String types = 	linetype.substring(6,linetype.indexOf("]"));
				String[] typeStr = 	types.split(",");
				for(String s:typeStr) {
					type.add(Integer.parseInt(s.trim()));
				}
			}
			String	lineid = 	line.substring(line.indexOf("id="));
			Integer id = Integer.parseInt(	lineid.substring(3,lineid.indexOf(",")).trim());
			String	lineutime = 	line.substring(line.indexOf("utime="));
			Long utime = Long.parseLong(	lineutime.substring(6,lineutime.indexOf(",")).trim());
			System.out.println(name+":"+area+":"+column+":"+spell+":"+year+":"+type+":"+id+":"+utime);
			doc.setField("name", name);
			doc.setField("area", area);
			doc.setField("column", column);
			doc.setField("spell", spell);
			doc.setField("year", year);
			doc.setField("type", type);
			doc.setField("id", id);
			doc.setField("utime", utime);
			client.add(doc);
			//一定要记得提交，否则不起作用
			client.commit();
		}
		br.close();
		client.close();
	}

	/**
	 * 删除索引
	 * @throws Exception
	 */
	@Test
	public void deleteIndex()throws Exception{
		HttpSolrClient client = new  HttpSolrClient(serverUrl);

		//1.删除一个
//		client.deleteById("1782");

		//2.删除多个
//		List<String> ids = new ArrayList<String>();
//		ids.add("1");
//		ids.add("2");
//		client.deleteById(ids);
//
//		//3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
//		client.deleteByQuery("id:zxj1");
//
//		//4.删除全部，删除不可恢复
//		client.deleteByQuery("*:*");

		//一定要记得提交，否则不起作用
		client.commit();
		client.close();
	}
}
