package kr.or.ddit.batch.hello;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;

public class HelloTask {
	
	private Logger logger = LoggerFactory.getLogger(HelloTask.class);
	
	//Look up(DL) -> injection 주입(DI)로 변경
	@Resource(name="jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name="helloJob")
	private Job helloJob;
	
	@Scheduled(cron="*/3 * * * * *")
	public void helloTask() {
		logger.debug("helloTask");
		
		//실행
		try {
			//Map<String, JobParameter> map = new HashMap<String, JobParameter>();
			//map.put("st", new JobParameter(System.currentTimeMillis()));
			
			jobLauncher.run(helloJob, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
	
}
