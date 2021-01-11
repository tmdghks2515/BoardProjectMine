package dto;
public class BoardDTO {
	private int bNo;
	private String bDate;
	private int bCount;
	private String writer;
	private String content;
	private int bLike;
	private int bHate;
	private String title;
	public BoardDTO(int bNo, String bDate, int bCount, String writer, String content, int bLike, int bHate,
			String title) {
		super();
		this.bNo = bNo;
		this.bDate = bDate;
		this.bCount = bCount;
		this.writer = writer;
		this.content = content;
		this.bLike = bLike;
		this.bHate = bHate;
		this.title = title;
	}
	public BoardDTO(String writer, String content, String title) {
		super();
		this.writer = writer;
		this.content = content;
		this.title = title;
	}
	
	public BoardDTO(int bNo, String writer, String content, String title) {
		super();
		this.bNo = bNo;
		this.writer = writer;
		this.content = content;
		this.title = title;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public int getbCount() {
		return bCount;
	}
	public void setbCount(int bCount) {
		this.bCount = bCount;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getbLike() {
		return bLike;
	}
	public void setbLike(int bLike) {
		this.bLike = bLike;
	}
	public int getbHate() {
		return bHate;
	}
	public void setbHate(int bHate) {
		this.bHate = bHate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "BoardDTO [bNo=" + bNo + ", bDate=" + bDate + ", bCount=" + bCount + ", writer=" + writer + ", content="
				+ content + ", bLike=" + bLike + ", bHate=" + bHate + ", title=" + title + "]";
	}
	
}
