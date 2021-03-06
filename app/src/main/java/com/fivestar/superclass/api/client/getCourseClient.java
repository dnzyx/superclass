package com.fivestar.superclass.api.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.fivestar.superclass.api.listener.getCourseListener;
import com.fivestar.superclass.api.model.course;
import com.fivestar.superclass.api.util.SDKInitializer;
import com.fivestar.superclass.api.util.inf;

public class getCourseClient {
	private String result;
	private HttpURLConnection con;
	private OutputStream out;
	public getCourseListener listener;
	
	public void setListener(getCourseListener l){
		this.listener = l;
	}
	public void start(String year,String teacherid,String code){
		ArrayList<course> list = new ArrayList<course>();
		String data = null;
		try {
			data = catchData(year,teacherid,code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			listener.onGetCourseError(inf.CONNECT_EXCEPTION, "连接服务器失败");
			return;
		}
		if(data==null){
			listener.onGetCourseError(inf.DATA_NULL, "加载数据失败");
			return;
		}
		Document doc = Jsoup.parse(data);
		Elements p=doc.select("p");
		if(p.isEmpty()==false&&p.attr("value").endsWith("0")==false){
			listener.onGetCourseError(Integer.parseInt(p.attr("value")),p.text());
			return;
		}
		Elements e=doc.select("div");
		if(e.isEmpty()==false){
			listener.onGetCourseError(inf.KEY_ERROR,"您可能是盗版软件的受害者!");
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				throw new KeyErrorException();
			} catch (KeyErrorException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			return;
		}
		Elements node1s = doc.select("script");
		if(node1s.isEmpty()==false)
		{
			listener.onGetCourseError(inf.CODE_ERROR, "验证码失败或失效");
			return;
		}
		Elements nodes = doc.select("td[valign=top]");
		int i = 0;
		for(Element n:nodes){
			course course = new course();
			course.setContent(n.text());
			course.setDate(Integer.parseInt(n.attr("date")));
			course.setNumber(Integer.parseInt(n.attr("number")));
			course.setYear(year);
			course.setTeacher_id(teacherid);
			list.add(course);
			i++;
		}
		if(list.isEmpty()){
			listener.onGetCourseError(inf.DATA_EMPTY, "该教师无课");
			return;
		}
		listener.onGetCourse(list);
		
	}
	private String catchData(String year,String teacherid,String code) throws Exception {
		String path="http://"+ inf.host+"/CourseServlet";
		final String params="year="+year+"&teacherid="+teacherid+"&type=1&code="+code+"&"+ SDKInitializer.getKey();
		URL url = new URL(path);
		con = (HttpURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");

		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					out = con.getOutputStream();
					out.write(params.getBytes());
					out.flush();
					InputStream in = con.getInputStream();
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int len = 0;
					while((len=in.read(buf))!=-1){
						bout.write(buf, 0, len);
					}
					byte[] buf1 = bout.toByteArray();
					result = new String(buf1,"utf-8");
					bout.close();
					in.close();
					out.close();
					con.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return;
				}
				
			}
		}).start();
		Thread.sleep(inf.timeout);
		return result;
	}
}
