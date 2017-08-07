package com.capgemini.chess.dao.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.data.Challenges;
import com.capgemini.chess.data.Users;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.mapper.ChallengeMapper;
import com.capgemini.chess.mapper.UserMapper;
import com.capgemini.chess.service.to.ChallengeTO;

@Repository
public class ChallengeDaoImpl implements ChallengeDao {

	@Override
	public List<Long> getIDsOfPlayersInChallenge(Long playerID) {

		return Challenges.getChallenges().stream()
				.filter(c -> playerID.equals(c.getChallengingPlayerId()) || playerID.equals(c.getOpponentPlayerId()))
				.map(c -> c.getChallengingPlayerId() != playerID ? c.getChallengingPlayerId() : c.getOpponentPlayerId())
				.collect(Collectors.toList());
	}

	@Override
	public List<Long> getIDsOfPlayersChallengingThisPlayer(Long playerID) {

		return Challenges.getChallenges().stream().filter(c -> playerID.equals(c.getOpponentPlayerId()))
				.map(c -> c.getChallengingPlayerId()).collect(Collectors.toList());
	}

	@Override
	public ChallengeTO setChallenge(ChallengeTO challenge) {
		ChallengeEntity newChallenge = ChallengeMapper.challengeMapper(challenge);
		newChallenge.setId(getNextId());
		Challenges.getChallenges().add(newChallenge);
		ChallengeTO result = Challenges.getChallenges().stream().filter(u -> newChallenge.getId() == u.getId())
				.findFirst().map(u -> ChallengeMapper.challengeMapper(u)).get();
		result.setChallengingPlayerStats(
				Users.getUsers().stream().filter(u -> u.getId() == result.getChallengingPlayerId()).findFirst()
						.map(u -> UserMapper.statisticsMapper(u)).get());
		result.setOpponentPlayerStats(Users.getUsers().stream().filter(u -> u.getId() == result.getOpponentPlayerId())
				.findFirst().map(u -> UserMapper.statisticsMapper(u)).get());
		return result;
	}

	@Override
	public ChallengeTO findChallenge(long firstPlayer, long secondPlayer) {
		return Challenges.getChallenges().stream()
				.filter(c -> (c.getChallengingPlayerId() == firstPlayer && c.getOpponentPlayerId() == secondPlayer)
						|| (c.getOpponentPlayerId() == firstPlayer && c.getChallengingPlayerId() == secondPlayer))
				.findFirst().map(c -> ChallengeMapper.challengeMapper(c)).orElse(null);
	}

	private long getNextId() {

		return Challenges.getChallenges().stream().map(chall -> chall.getId()).max(Comparator.naturalOrder()).orElse(0L)
				+ 1L;
	}

	@Override
	public List<ChallengeTO> getPotentialChallenges(int minLevel, int maxLevel, Long playerID) {
		List<Long> impossiblePlayers = getIDsOfPlayersInChallenge(playerID);
		impossiblePlayers.add(playerID);
		List<ChallengeTO> challenges = Users.getUsers().stream()
				.filter(user -> user.getStatistics().getLevel() <= maxLevel
						&& user.getStatistics().getLevel() >= minLevel)
				.filter(user -> !impossiblePlayers.contains(user.getId())).limit(5)
				.map(user -> UserMapper.userMapper(user)).map(opp -> ChallengeMapper.challengeMapper(opp))
				.collect(Collectors.toList());
		challenges.stream().forEach(chall -> {
			chall.setChallengingPlayerId(playerID);
			chall.setId(getNextId());
		});

		return challenges;
	}

	@Override
	public ChallengeTO findChallenge(long challengeId) {
		return Challenges.getChallenges().stream().filter(c -> c.getId() == challengeId).findFirst()
				.map(c -> ChallengeMapper.challengeMapper(c)).get();
	}

	@Override
	public ChallengeTO deleteChallenge(long challengeId) {
		ChallengeTO result = findChallenge(challengeId);
		Challenges.getChallenges().removeIf(c -> c.getId() == challengeId);
		return result;
	}

}