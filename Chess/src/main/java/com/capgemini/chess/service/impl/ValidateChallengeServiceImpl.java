package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.exception.ChallengeAlreadyExistsException;
import com.capgemini.chess.service.ValidateChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

@Service
public class ValidateChallengeServiceImpl implements ValidateChallengeService {
	
	ChallengeDao challengeDao = null;
	
	@Autowired
	public ValidateChallengeServiceImpl(ChallengeDao challengeDao) {
		this.challengeDao = challengeDao;
	}
	@Override
	public void validateChallenge(long firstPlayer, long secondPlayer) {
		ChallengeTO challenge = challengeDao.findChallenge(firstPlayer, secondPlayer);
		if(challenge != null)
				throw new ChallengeAlreadyExistsException();
		
	}

}
