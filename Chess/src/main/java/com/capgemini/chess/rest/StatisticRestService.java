package com.capgemini.chess.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.facade.MultiplayerServiceFacade;
import com.capgemini.chess.service.to.StatisticTO;

@RestController
public class StatisticRestService {

	@Autowired
	MultiplayerServiceFacade service;

	@RequestMapping(value = "/statistic", method = RequestMethod.GET)
	public StatisticTO getPlayerStatistics(@RequestParam("id") Long playerId) {
		StatisticTO statistic = service.getPlayersStatistics(playerId);
		return statistic;
	}

}
