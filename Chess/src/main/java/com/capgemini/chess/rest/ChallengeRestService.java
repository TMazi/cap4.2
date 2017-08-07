package com.capgemini.chess.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.facade.MultiplayerServiceFacade;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.UserTO;

@RestController
public class ChallengeRestService {

	@Autowired
	MultiplayerServiceFacade service;

	@RequestMapping(value = "/challenge", method = RequestMethod.POST)
	public List<ChallengeTO> generatePotentialChallenges(@RequestBody UserTO user) {
		return service.getPotentialAndPendingChallenges(user);
	}

	@RequestMapping(value = "/challenge", method = RequestMethod.PUT)
	public ChallengeTO sendNewChallenge(@RequestParam("id1") Long firstid, @RequestParam("id2") Long secondid) {
		ChallengeTO challenge = service.sendNewChallengeToPlayer(firstid, secondid);
		return challenge;
	}

	@RequestMapping(value = "/challenge", method = RequestMethod.GET)
	public ChallengeTO getChallengeById(@RequestParam("id") long challengeId) {
		return service.getChallengeById(challengeId);
	}

	@RequestMapping(value = "/challenge", method = RequestMethod.DELETE)
	public ChallengeTO removeChallenge(@RequestParam("id") long challengeId) {
		return service.deleteChallenge(challengeId);
	}

}
