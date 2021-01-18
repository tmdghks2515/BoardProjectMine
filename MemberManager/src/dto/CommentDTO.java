package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import service.MemberService;

public class CommentDTO {
	private int cNo;
	private int bNo;
	private String content;
	private String cDate;
	private String writer;
	private int cLike;
	private int cHate;
	public CommentDTO(int cNo, int bNo, String content, String cDate, String writer, int cLike, int cHate) {
		super();
		this.cNo = cNo;
		this.bNo = bNo;
		this.content = content;
		this.cDate = cDate;
		this.writer = writer;
		this.cLike = cLike;
		this.cHate = cHate;
	}
	public CommentDTO(int bNo, String content, String writer) {
		super();
		this.bNo = bNo;
		this.content = content;
		this.writer = writer;
	}
	public int getcNo() {
		return cNo;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getcLike() {
		return cLike;
	}
	public void setcLike(int cLike) {
		this.cLike = cLike;
	}
	public int getcHate() {
		return cHate;
	}
	public void setcHate(int cHate) {
		this.cHate = cHate;
	}
	public String getDateDiff() {
		String result = "";
		Date today = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date cmt_date = format1.parse(cDate);
			long diff = today.getTime() - cmt_date.getTime();
			long sec = diff/1000;
			if(sec < 60)
				result=sec+"초 전";
			else if(sec < 60*60)
				result=sec/60+"분 전";
			else if(sec < 60*60*24)
				result=sec/(60*60)+"시간 전";
			else if(sec < 60*60*24*30)
				result=sec/(60*60*24)+"일 전";
			else if(sec < 60*60*24*30*365)
				result=sec/(60*60*24*30)+"달 전";
			else
				result=sec/(60*60*24*30*365)+"년 전";

		}catch(Exception e){
			result = "오류";
		}
		return result;
	}
	public String getName() {
		return MemberService.getInstance().select(writer).getName();
	}
	public String getHiddenId() {
		return writer.substring(0, 2)+"***";
	}
	
}
