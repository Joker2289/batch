package kr.or.ddit.batch.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.batch.model.BatchVO;

@Repository("batchDao")
public class BatchDao implements IBatchDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int createBatch(BatchVO batchVO) {
		return template.insert("batch.createBatch", batchVO);
	}

	@Override
	public int updateBatch(BatchVO batchVO) {
		return template.update("batch.updateBatch", batchVO);
	}

	@Override
	public int getBatchBid() {
		return template.selectOne("batch.getBatchBid");
	}

}
