package com.capgemini.chess.dataaccess.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlayerStatisticsEntity {

	@Id
	private long id;
	private int played;
	private int won;
	private int lost;
	private int drawn;
	private long points;
	private int level;
	
	public PlayerStatisticsEntity(long id, int played, int won, int lost, int drawn, int points, int level) {
		this.id = id;
		this.played = played;
		this.won = won;
		this.lost = lost;
		this.drawn = drawn;
		this.points = points;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getDrawn() {
		return drawn;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
