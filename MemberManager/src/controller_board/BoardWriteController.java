package controller_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dto.BoardDTO;
import dto.FileDTO;
import model.ModelAndView;
import service.BoardService;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BoardWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<FileDTO> fList = new ArrayList<>();
		String root = "c:\\fileupload\\";
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024*50, new File(root));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			Iterator<FileItem> items = upload.parseRequest(request).iterator();
			while(items.hasNext()) {
				FileItem thisItem = items.next();
				if(thisItem.isFormField()) {
					switch(thisItem.getFieldName()) {
					case "writer":
						String writer = thisItem.getString();
						break;
					}
				}else {
					int index = thisItem.getName().lastIndexOf("\\");
					if(index == -1)
						index = thisItem.getName().lastIndexOf("/");
					String fileName = thisItem.getName().substring(index+1);
					File file = new File(root+(String)request.getSession().getAttribute("id")
							+"\\"+fileName);
					if(!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return null;
	}


}
