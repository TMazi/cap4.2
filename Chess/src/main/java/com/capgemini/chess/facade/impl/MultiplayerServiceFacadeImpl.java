package com.capgemini.chess.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.facade.MultiplayerServiceFacade;
import com.capgemini.chess.service.CreateNewChallengeService;
import com.capgemini.chess.service.DeleteChallengeService;
import com.capgemini.chess.service.GetPendingChallengesService;
import com.capgemini.chess.service.GetPlayerStatisticsService;
import com.capgemini.chess.service.GetPotentialOpponentsService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.StatisticTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class MultiplayerServiceFacadeImpl implements MultiplayerServiceFacade {

	private GetPotentialOpponentsService getPotentialOpponentsService = null;

	private GetPendingChallengesService getPendingChallengesService = null;

	private CreateNewChallengeService createNewChallengeService = null;

	private GetPlayerStatisticsService statisticsService = null;

	private DeleteChallengeService deleteService = null;

	@Autowired
	public MultiplayerServiceFacadeImpl(GetPotentialOpponentsService getPotentialOpponentsService,
			GetPendingChallengesService getPendingChallengesService,
			CreateNewChallengeService createNewChallengeService, GetPlayerStatisticsService statisticsService,
			DeleteChallengeService deleteService) {

		this.getPotentialOpponentsService = getPotentialOpponentsService;
		this.getPendingChallengesService = getPendingChallengesService;
		this.createNewChallengeService = createNewChallengeService;
		this.statisticsService = statisticsService;
		this.deleteService = deleteService;
	}

	@Override
	public List<ChallengeTO> getPotentialAndPendingChallenges(UserTO user) {

		List<ChallengeTO> result = new ArrayList<>();
		result.addAll(getPotentialOpponentsService.getPotentialOpponents(user));
		result.addAll(getPendingChallengesService.getPendingChallenges(user.getId()));
		return result;

	}

	@Override
	public ChallengeTO sendNewChallengeToPlayer(Long firstID, Long secondID) {
		return createNewChallengeService.sendNewChallenge(firstID, secondID);
	}

	@Override
	public StatisticTO getPlayersStatistics(Long playerID) {
		return statisticsService.getPlayersStatistics(playerID);

	}

	@Override
	public ChallengeTO getChallengeById(long challengeId) {
		return getPendingChallengesService.getChallengeById(challengeId);
	}

	@Override
	public ChallengeTO deleteChallenge(long challengeId) {
		return deleteService.deleteChallenge(challengeId);
	}

}
