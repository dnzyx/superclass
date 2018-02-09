package com.fivestar.superclass.api.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fivestar.superclass.api.exception.KeyErrorException;
import com.fivestar.superclass.api.listener.getTeacherListener;
import com.fivestar.superclass.api.model.teacher;
import com.fivestar.superclass.api.util.SDKInitializer;
import com.fivestar.superclass.api.util.inf;

public class getTeacherClient {
	private HttpURLConnection con;
	private OutputStream out;
	public getTeacherListener listener;
	private String result=null;
	public void setListener(getTeacherListener l){
		this.listener = l;
	}
	public void star(){
		ArrayList<teacher> list = new ArrayList<teacher>();
		String data = null;
		try {
			data= catchData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			listener.OnGetTeacherError(inf.CONNECT_EXCEPTION, "网络异常");
			return;
		}
		if(data==null){
			listener.OnGetTeacherError(inf.DATA_NULL, "加载数据失败");
			return;
		}
		//data="<select name=Sel_JS style='width:220'><option></option><option value=0000916>Albert WOLFE</option><option value=0001968>Alexander SAWYER</option></select>";
		Document doc = Jsoup.parse(data);
		Elements p=doc.select("p");
		if(p.isEmpty()==false&&p.attr("value").endsWith("0")==false){
			listener.OnGetTeacherError(Integer.parseInt(p.attr("value")),p.text());
			return;
		}
		Elements e=doc.select("div");
		if(e.isEmpty()==false){
			listener.OnGetTeacherError(inf.KEY_ERROR,"您可能是盗版软件的受害者!");
			try {
				throw new KeyErrorException();
			} catch (KeyErrorException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			return;
		}
		Elements nodes = doc.select("option");
		for(Element n:nodes){
			if(n.text().length()==0){
				continue;
			}
			teacher teach = new teacher();
			teach.setId(n.attr("value"));
			teach.setName(n.text());
			list.add(teach);
		}
		if(list.isEmpty()){
			listener.OnGetTeacherError(inf.DATA_EMPTY, "数据解析结果为空");
		}
		else{
			listener.onGetTeacher(list);
		}
	}
	private String catchData() throws Exception{

		String path="http://"+ inf.host+"/TeacherServlet";
		String referer="http://qg.peizheng.net.cn/ZNPK/TeacherKBFB.aspx";
		final String params="xnxq=20170&js="+"&"+ SDKInitializer.getKey();

		URL url = new URL(path);
		con = (HttpURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		con.setRequestProperty("Referer", referer);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					out = con.getOutputStream();
					out.write(params.getBytes());
					out.flush();
					InputStream in = con.getInputStream();
					
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int len = 0;
					while((len=in.read(buf))!= (-1)){
						bout.write(buf, 0, len);
					}
					byte[] buf1 = bout.toByteArray();
					
					result = new String(buf1,"utf-8");
					in.close();
					bout.close();
					out.close();
					con.disconnect();
				} catch (Exception e) {
					return ;
				}
		
			}
		}).start();
		Thread.sleep(inf.timeout);
		return result;
	}
}
