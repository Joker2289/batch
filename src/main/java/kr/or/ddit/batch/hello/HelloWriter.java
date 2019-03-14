package kr.or.ddit.batch.hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
								//process 로부터 넘겨받는 타입
public class HelloWriter implements ItemWriter<String>{

	private Logger logger = LoggerFactory.getLogger(HelloWriter.class);
	
	//chunk : 한 번에 기록할 사이즈
	//
	@Override
	public void write(List<? extends String> avengers) throws Exception {
		for(String avenger : avengers)
			logger.debug("writer : {}", avenger);
	}

}
