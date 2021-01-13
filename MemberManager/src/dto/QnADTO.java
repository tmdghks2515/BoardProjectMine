package dto;

public class QnADTO {
	private int qNo;
	private String title;
	private String content;
	private String wDate;
	private String writer;
	private int status;
	private String response;
	public QnADTO(int qNo, String title, String content, String wDate, String writer, int status, String response) {
		super();
		this.qNo = qNo;
		this.title = title;
		this.content = content;
		this.wDate = wDate;
		this.writer = writer;
		this.status = status;
		this.response = response;
	}
	
	public QnADTO(String title, String content, String wDate, int status, String response) {
		super();
		this.title = title;
		this.content = content;
		this.wDate = wDate;
		this.status = status;
		this.response = response;
	}

	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "QnADTO [qNo=" + qNo + ", title=" + title + ", content=" + content + ", wDate=" + wDate + ", writer="
				+ writer + ", status=" + status + ", response=" + response + "]";
	}
	
	
}
