package file;

import java.io.File;

//파일 정보를 저장
public class FileDTO {
	private String path;//파일 전체경로
	private String fileName;//
	private String type;
	public FileDTO(File file) {
		super();
		this.path = file.getAbsolutePath();
		this.fileName = file.getName();
		
		switch(fileName.substring(fileName.lastIndexOf(".")+1)) {
		case "png":
		case "bmp":
		case "jpg":
		case "gif":
			type="image";
			break;
		case "mp4":
			type="video";
		default:
			type="normal";
		}
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
