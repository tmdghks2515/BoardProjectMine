package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class StartBatch
 *
 */
@WebListener
public class StartBatch implements ServletContextListener {
	CronTriggerMaker js;
    /**
     * Default constructor. 
     */
    public StartBatch() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
		/*
		 * System.out.println("MemberManager End"); 
		 * js.shutdownScheduler();
		 */
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
		/*
		 * js = new CronTriggerMaker("0/5 * * * * ?", SearchNoAnswerList.class);
		 * js.createTrigger();
		 */
    }
	
}
