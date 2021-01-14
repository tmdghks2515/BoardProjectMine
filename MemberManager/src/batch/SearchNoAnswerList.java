package batch;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dto.QnADTO;
import service.QnAService;

public class SearchNoAnswerList implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ArrayList<QnADTO> li = QnAService.getInstance().searchUnanswered();
		for(int i=0; i<li.size(); i++) {
			System.out.println(li.get(i));
		}
	}
	
}
