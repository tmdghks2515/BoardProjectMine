package dto;

public class FileDTO {
	String writer;
	int bNo;
	String fileName;
	public FileDTO(String writer, int bNo, String fileName) {
		super();
		this.writer = writer;
		this.bNo = bNo;
		this.fileName = fileName;
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
	public String getfileName() {
		return fileName;
	}
	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
	
}
