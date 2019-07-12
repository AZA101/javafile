package com.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class RegistServlet
 */
/* @WebServlet("/RegistServlet") */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * public RegistServlet() {
	 * 
	 * }
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		/* 创建文件接收工厂 */
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		/* 创建文件解析对象 */
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		/*设置解析requset请求的编码方式*/
		//servletFileUpload.setHeaderEncoding("UTF-8");
		/*设置上传单个文件的大小限制*/
		servletFileUpload.setFileSizeMax(1024*10);
		/* 解析requst */
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				/* 获取上传的文件名称 */
				String name = fileItem.getName();				
				/* 获取前端文件名字 */
				String filename = fileItem.getFieldName();
				/*文本里面的内容*/
				String fileString=fileItem.getString();
				/*普通文本里面的内容进行编码转换，普通文本就是input框这些里面的内容*/
				fileString =new String(fileString.getBytes("ISO-8859-1"),"UTF-8");	
				System.out.println("文本里面的内容:"+fileString);   
				/* 判断是否是普通文件 */
				boolean isfile = fileItem.isFormField();
			
			   if(name !=null && !"".equals(name)){
				   /*制定上传的路径*/
				   String upload=request.getSession().getServletContext().getRealPath("/WEB-INF/jmupload");				
					/*设置唯一编码*/
					String  uuid=UUID.randomUUID().toString();
					/*对上传的文件名字进行剪切，截取后缀名字*/
					String subName=name.substring(name.lastIndexOf("."));
					
				   /*创建文件对象*/
				    File file=new File(upload, uuid+subName);
				   /*把文件写入硬盘中*/
					fileItem.write(file);	
					System.out.println("原始文件名称:"+name);
				
			   }
				
			}
			response.getWriter().println("文件上传成功");		
		} catch (Exception e) {
			if(e instanceof FileSizeLimitExceededException){
				response.getWriter().print("单个文件太大,超出10kB限制");
			}
			//e.printStackTrace();
		}

	}

}
