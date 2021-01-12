package vo;

public class PagingVO {
	private int boardTotal;//전체 게시글 갯수
	private final int boardPerPage = 7;//한 페이지당  출력할 게시글 개수
	private final int pagePerGroup = 5; //게시판 하단에 나타낼 페이지 번호 개수
	private int currentPage;//현재 페이지번호
	
	public PagingVO(int boardTotal, int currentPage) {
		super();
		this.boardTotal = boardTotal;
		this.currentPage = currentPage;
	}

	public int getBoardPerPage() {
		return boardPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageTotal() {
		int pageTotal = boardTotal / boardPerPage;
		if(boardTotal % boardPerPage != 0)
			pageTotal++;
		return pageTotal;
	}
	
	public int getGroupTotal(){
		int groupTotal = getPageTotal() / pagePerGroup;
		if(getPageTotal() % pagePerGroup != 0)
			groupTotal++;
		return groupTotal;
	}

	public int getCurrentGroup() {
		int CurrentGroup = currentPage / pagePerGroup;
		if(currentPage % pagePerGroup != 0)
			CurrentGroup++;
		return CurrentGroup;
	}
	
	public int getFirstPageOfGroup() {
		return 1 + (getCurrentGroup()-1)*pagePerGroup;
	}
	
	public int getLastPageOfGroup() {
		int LastPageOfGroup = getCurrentGroup()*pagePerGroup;
		if(getGroupTotal() == getCurrentGroup())
			LastPageOfGroup = getPageTotal();
		return LastPageOfGroup;
	}
	
	public boolean isFirstGroup() {
		return getCurrentGroup() == 1;
	}
	
	public boolean isLastGroup() {
		return getCurrentGroup() == getGroupTotal();
	}
	
}
