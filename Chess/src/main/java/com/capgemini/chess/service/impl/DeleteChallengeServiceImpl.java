package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.DeleteChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

@Service
public class DeleteChallengeServiceImpl implements DeleteChallengeService {

	private ChallengeDao challengeDao = null;

	@Autowired
	DeleteChallengeServiceImpl(ChallengeDao challengeDao) {
		this.challengeDao = challengeDao;
	}

	@Override
	public ChallengeTO deleteChallenge(long challengeId) {
		return challengeDao.deleteChallenge(challengeId);
	}

}
