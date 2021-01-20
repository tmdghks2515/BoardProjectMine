package controller_board;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.Controller;
import dto.FileDTO;
import model.ModelAndView;
import service.BoardService;

public class UploadController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = null;
		String content = null;
		String title = null;
		int bNo = 0;
		ArrayList<FileDTO> fList = null;
		String root = "c:\\UploadedFiles";
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(root));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			Iterator<FileItem> items = upload.parseRequest(request).iterator();
			while(items.hasNext()) {
				FileItem thisItem = items.next();
				if(thisItem.isFormField()) {
					switch(thisItem.getFieldName()) {
					case"writer":
						writer = thisItem.getString("utf-8");
						break;
					case"content":
						content = thisItem.getString("utf-8");
						break;
					case"title":
						title = thisItem.getString("utf-8");
						break;
					}
				}else {
					File file = new File(root+"\\"+request.getSession().getAttribute("id")+"\\"+thisItem.getName());
					if(!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					thisItem.write(file);
					if(fList == null) fList = new ArrayList<>();
					fList.add(new FileDTO("", 0, file.getAbsolutePath()));
				}
			}
			bNo = BoardService.getInstance().insertBoard(writer, title, content);
			for(FileDTO fdto : fList) {
				fdto.setWriter(writer);
				fdto.setbNo(bNo);
			}
			BoardService.getInstance().insertFile(fList);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ModelAndView("boardView.do?bNo="+bNo,true);
	}

}
