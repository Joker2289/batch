package kr.or.ddit.yogult.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YogultDao implements IYogultDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	/**
	 * 
	 * Method : deleteDailyYm
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param ym
	 * @return
	 * Method 설명 : 일실적 일괄 삭제
	 */
	@Override
	public int deleteDailyYm(String ym) {
		return template.delete("yogult.deleteDailyYm", ym);
	}

	/**
	 * 
	 * Method : dailyBatchYm
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param ym
	 * @return
	 * Method 설명 : 일실적 입력
	 */
	@Override
	public int dailyBatchYm(String ym) {
		return template.insert("yogult.dailyBatchYm", ym);
	}

}
