package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload.do")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		
		String root = "c:\\fileupload\\";
		File userRoot = new File(root);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(userRoot);//업로드 될 폴더
		factory.setSizeThreshold(1024*1024); //1mb -> 버퍼메모리
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> list = upload.parseRequest(request);
			ArrayList<FileDTO> fli = new ArrayList<>();
			String user = "default";
			for(int i=0;i<list.size();i++) {
				FileItem item = list.get(i);
				if(item.isFormField()) {
					//받은내용중 파일이 아닌경우
					if(item.getFieldName().equals("writer"))
						user = item.getString(encoding);
					System.out.println(item.getFieldName() + " : " + item.getString(encoding));
					
				}else {
					//파일인 경우
					System.out.println("매개변수 명 : "+item.getFieldName());
					System.out.println("파일명 : "+item.getName());
					System.out.println("파일크기 : "+item.getSize());
					System.out.println("파일타입 : "+item.getContentType());
					if(item.getSize() > 0) {
						int idx = item.getName().lastIndexOf("\\");
						if(idx==-1)
							idx = item.getName().lastIndexOf("/");
						String fileName = item.getName().substring(idx+1);
						//파일경로 완성해주는 부분
						File uploadFile = new File(userRoot.getAbsolutePath()+"\\"+user+"\\"+fileName);
						if(!uploadFile.getParentFile().exists())//해당 파일이 들어갈 폴더가 만들어져있는지
							uploadFile.getParentFile().mkdirs();
						System.out.println("셋팅된 전체경로: "+uploadFile);
						item.write(uploadFile); //파일쓰기
						fli.add(new FileDTO(uploadFile));
					} 
				}
			}
			request.setAttribute("fli", fli);
			request.setAttribute("user", user);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("file_result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
