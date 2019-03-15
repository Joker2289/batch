package kr.or.ddit.batch.dao;

import kr.or.ddit.batch.model.BatchVO;

public interface IBatchDao {
	
	/**
	 * 
	 * Method : getBatchBid
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 배치 아이디를 생성
	 */
	int getBatchBid();
	
	/**
	 * 
	 * Method : createBatch
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param batchVO
	 * @return
	 * Method 설명 : 배치 정보를 생성
	 */
	int createBatch(BatchVO batchVO);
	
	/**
	 * 
	 * Method : updateBatch
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param batchVO
	 * @return
	 * Method 설명 : 배치 정보 업데이트
	 */
	int updateBatch(BatchVO batchVO);
}
