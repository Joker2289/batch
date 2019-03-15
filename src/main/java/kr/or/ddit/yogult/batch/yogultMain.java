package kr.or.ddit.yogult.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class yogultMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:kr/or/ddit/config/spring/context-batch.xml");

		// jobLauncher, job을 받아와서 (DL)
		JobLauncher launcher = context.getBean("jobLauncher", JobLauncher.class);
		Job yogultJob = context.getBean("yogultJob", Job.class);

		
		Map<String, JobParameter> map = new HashMap<String, JobParameter>();
		map.put("ym", 
				new JobParameter(new SimpleDateFormat("yyyyMM").format(new Date())));
		
			try {
				launcher.run(yogultJob, new JobParameters(map));
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		context.close();
	}

}
