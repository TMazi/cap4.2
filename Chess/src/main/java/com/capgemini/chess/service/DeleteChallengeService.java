package com.capgemini.chess.service;

import com.capgemini.chess.service.to.ChallengeTO;

public interface DeleteChallengeService {
	
	ChallengeTO deleteChallenge(long challengeid);

}
