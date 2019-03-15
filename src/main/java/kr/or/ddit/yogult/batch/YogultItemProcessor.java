package kr.or.ddit.yogult.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogult.model.CycleVO;
import kr.or.ddit.yogult.model.DailyVO;

public class YogultItemProcessor implements ItemProcessor<CycleVO, List<DailyVO>> {

	private Logger logger = LoggerFactory.getLogger(YogultItemProcessor.class);

	// spring EL
	// jobParameter(spel)를 주입 받기 위해서는 해당 bean의 스코프가 "step"
	@Value("#{jobParameters[ym]}")
	private String ym;

	@Override
	public List<DailyVO> process(CycleVO cycleVO) throws ParseException {

		logger.debug("process ym : {}", ym);
		// ex) 1번 고객이 100번 제품을 2(월요일)요일날 3개월 먹는다.
		// String ym = "201903";
		// 해당 년월의 시작일자 : 1일
		// 해당 년월의 종료일자 : 28일, 29일, 30일, 31일

		// 1 100 2 3

		// 1 100 20190304(월) 3
		// 1 100 20190311(월) 3
		// 1 100 20190318(월) 3
		// 1 100 20190325(월) 3
		// 위에 해당하는 4건의 data가 생성되어야 함

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance();
		Date startDate = sdf.parse(ym + "01");
		cal.setTime(startDate);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date endDate = sdf.parse(ym + lastDay);

		// 해당 월의 시작일자부터 종료일자까지 루프를 돌아 애음주기에 대한 날짜를 추출
		List<DailyVO> dailyVoList = new ArrayList<>();
		while (cal.getTimeInMillis() <= endDate.getTime()) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			// 애음주기 요일과 cal 날짜요일이 같으면 일실적으로 생성할 대상일자가 된다.
			if (dayOfWeek == cycleVO.getDay()) {
				DailyVO dailVo = new DailyVO();
				dailVo.setCid(cycleVO.getCid());
				dailVo.setPid(cycleVO.getPid());
				dailVo.setDt(sdf.format(cal.getTime()));
				dailVo.setCnt(cycleVO.getCnt());

				dailyVoList.add(dailVo);
			}
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}

		return dailyVoList;
	}

}
