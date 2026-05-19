package egovframework.let.cop.bbs.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import egovframework.let.cop.bbs.domain.model.BoardMaster;
import egovframework.let.cop.bbs.domain.repository.BBSAttributeManageDAO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class BBSAttributeManageDAOTest {

	@Autowired
	BBSAttributeManageDAO bbsAttributeManageDAO;

	@Autowired
	EgovIdGnrService egovBBSMstrIdGnrService;

	@Test
	void deleteBBSMasterInf() {
		// given
		BoardMaster testData = testData();

		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setLastUpdusrId(testData.getUniqId());
		boardMaster.setBbsId(testData.getBbsId());

		int expected = 0;

		// when
		int result = bbsAttributeManageDAO.deleteBBSMasterInf(boardMaster);

		if (log.isDebugEnabled()) {
			log.debug("actual, expected={}, {}", result, expected);
		}

		// then
		assertThat(result).isGreaterThan(expected);
	}

	@Test
	void insertBBSMasterInf() {
		// given
		BoardMaster boardMaster = new BoardMaster();

		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		boardMaster.setBbsNm("test 이백행 게시판명 " + now);

		boardMaster.setPosblAtchFileSize("0");

		int expected = 0;

		// when
		int result = bbsAttributeManageDAO.insertBBSMasterInf(boardMaster);

		if (log.isDebugEnabled()) {
			log.debug("actual, expected={}, {}", result, expected);
		}

		// then
		assertThat(result).isGreaterThan(expected);
	}

	BoardMaster testData() {
		// given
		BoardMaster testData = new BoardMaster();

		// 게시판마스터
		try {
			testData.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		testData.setBbsNm("test 이백행 게시판명 " + now);

		testData.setPosblAtchFileSize("0");

		// 사용자정보
		testData.setUniqId("USRCNFRM_00000000000");
//		testData.setUniqId("USRCNFRM_00000000001");

		int expected = 0;

		// when
		int result = bbsAttributeManageDAO.insertBBSMasterInf(testData);

		if (log.isDebugEnabled()) {
			log.debug("actual, expected={}, {}", result, expected);
		}

		// then
//		assertThat(result).isGreaterThan(expected);

		return testData;
	}

}