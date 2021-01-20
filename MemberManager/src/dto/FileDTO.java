package dto;

public class FileDTO {
	String writer;
	int bNo;
	String fileName;
	String type;
	String path;
	public FileDTO(String writer, int bNo, String path) {
		super();
		this.writer = writer;
		this.bNo = bNo;
		this.path = path;
		int index = path.lastIndexOf("\\");
		if(index==-1)
			index = path.lastIndexOf("/");
		fileName = path.substring(index+1);
		switch(fileName.substring(fileName.lastIndexOf(".")+1)) {
		case"jpg":
		case"png":
		case"bmp":
		case"gif":
			type="image";
			break;
		default:
			type="normal";
		}
		
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getfileName() {
		return fileName;
	}
	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
	
}
