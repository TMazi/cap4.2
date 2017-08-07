package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;

public interface GetPendingChallengesService {

	List<ChallengeTO> getPendingChallenges(long userId);

	ChallengeTO getChallengeById(long challengeId);

}
