package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.BoardDTO;
import dto.FileDTO;
import model.ModelAndView;
import service.BoardService;

public class UploadController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		//String root = "c:\\workspace\\practice\\BoardFiles\\";
		String root = request.getSession().getServletContext().getRealPath("/")+"/upload";  //상대경로
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024*50, new File(root));
		ServletFileUpload upload = new ServletFileUpload(factory);
		int bNo = 0;
		try {
			String writer = null;
			String title = null;
			String content = null;
			ArrayList<FileDTO> fList = new ArrayList<>();
			
			Iterator<FileItem> items = upload.parseRequest(request).iterator();
			while(items.hasNext()) {
				FileItem thisItem = items.next();
				if(thisItem.isFormField()) {
					switch(thisItem.getFieldName()) {
					case"writer":
						writer = thisItem.getString("utf-8");
						break;
					case"title":
						title = thisItem.getString("utf-8"); 
						break;
					case"content":
						content = thisItem.getString("utf-8");
						break;
					}
				}else {
					int index = thisItem.getName().lastIndexOf("\\");
					if(index == -1)
						index = thisItem.getName().lastIndexOf("/");
					String fileName = thisItem.getName().substring(index+1);
					File file = new File(root+"\\"+(String)request.getSession().getAttribute("id")+"\\"+fileName);
					if(!file.getParentFile().exists()) 
						file.getParentFile().mkdirs();
					thisItem.write(file);
					fList.add(new FileDTO((String)request.getSession().getAttribute("id"), 0, fileName));
				}
			}
			bNo = BoardService.getInstance().insertBoard(writer, title, content);
			for(FileDTO fdto : fList) {
				fdto.setbNo(bNo);
			}
			BoardService.getInstance().insertFile(fList);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return new ModelAndView("boardView.do?bNo="+bNo, true);
	}

}
