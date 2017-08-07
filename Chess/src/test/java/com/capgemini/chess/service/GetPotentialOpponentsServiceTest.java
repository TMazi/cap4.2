package com.capgemini.chess.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.impl.GetPotentialOpponentsServiceImpl;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.StatisticTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class GetPotentialOpponentsServiceTest {

	@Mock
	ChallengeDao challengedDao;

	@Test
	public void shouldReturnListOfOpponents() {

		// given
		GetPotentialOpponentsService service = new GetPotentialOpponentsServiceImpl(challengedDao);
		List<ChallengeTO> opponents = createSomeChallenges();
		when(challengedDao.getPotentialChallenges(1, 4, 1L)).thenReturn(opponents);
		UserTO user = new UserTO();
		user.setId(1);
		user.setStatistic(new StatisticTO());
		user.getStatistic().setLevel(2);

		// when
		List<ChallengeTO> result = service.getPotentialOpponents(user);

		// then
		assertEquals(4, result.size());

	}

	private List<ChallengeTO> createSomeChallenges() {
		List<ChallengeTO> challenges = new ArrayList<>();
		ChallengeTO first = new ChallengeTO();
		first.setId(2L);
		ChallengeTO second = new ChallengeTO();
		second.setId(3L);
		ChallengeTO third = new ChallengeTO();
		third.setId(4L);
		ChallengeTO fourth = new ChallengeTO();
		fourth.setId(5L);
		challenges.add(first);
		challenges.add(second);
		challenges.add(third);
		challenges.add(fourth);

		return challenges;
	}

}
