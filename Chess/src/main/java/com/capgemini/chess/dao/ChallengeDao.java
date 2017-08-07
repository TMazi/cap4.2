package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;

public interface ChallengeDao {

	List<Long> getIDsOfPlayersInChallenge(Long playerID);

	List<Long> getIDsOfPlayersChallengingThisPlayer(Long playerID);

	ChallengeTO setChallenge(ChallengeTO challenge);

	ChallengeTO findChallenge(long challengeId);

	ChallengeTO findChallenge(long firstPlayer, long secondPlayer);
	
	ChallengeTO deleteChallenge(long challengeId);

	List<ChallengeTO> getPotentialChallenges(int minLevel, int maxLevel, Long playerID);

}
