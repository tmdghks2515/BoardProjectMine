package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;

public class imgLoadController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String fileName = request.getParameter("fileName");
		String type = request.getParameter("type");
		String path = "C:/tomcat/webapps/MemberManager/upload/"+writer+"/"+fileName;
		response.setContentType("image/"+type);
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream sos = response.getOutputStream();
			byte[] buffer = new byte[1024*1024];
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				sos.write(buffer, 0, size);
				sos.flush();
			}
			sos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
