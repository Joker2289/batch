package kr.or.ddit.batch.hello;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class HelloReader implements ItemReader<String>{

	private Logger logger = LoggerFactory.getLogger(HelloReader.class);
	
	private List<String> avengers;
	private int index = 0;
	
	
	public HelloReader() {
		avengers = new ArrayList<String>();
		avengers.add("Thor");
		avengers.add("Ironman");
		avengers.add("Hulk");
		avengers.add("Captain America");
		avengers.add("Hawk Eye");
		avengers.add("Black Panther");
		avengers.add("Black Widow");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		if(index < avengers.size()) {
			String avenger = avengers.get(index);
			index++;
			
			logger.debug("read : {}", avenger);
			
			return avenger;
		} else {
			index = 0;
			return null;	//Spring batch에게 더이상 읽을 데이터가 없다고 알림
		}
		
	}
}
