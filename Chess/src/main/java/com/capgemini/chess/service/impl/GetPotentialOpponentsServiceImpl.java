package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.GetPotentialOpponentsService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class GetPotentialOpponentsServiceImpl implements GetPotentialOpponentsService {

	ChallengeDao challenges = null;

	@Autowired
	public GetPotentialOpponentsServiceImpl(ChallengeDao challenges) {
		this.challenges = challenges;
	}

	@Override
	public List<ChallengeTO> getPotentialOpponents(UserTO user) {

		int playerLevel = user.getStatistic().getLevel();
		int maxLevel = playerLevel + 2;
		int minLevel;

		if (playerLevel < 3) {
			minLevel = 1;
		} else
			minLevel = playerLevel - 2;

		return challenges.getPotentialChallenges(minLevel, maxLevel, user.getId());

	}

}
