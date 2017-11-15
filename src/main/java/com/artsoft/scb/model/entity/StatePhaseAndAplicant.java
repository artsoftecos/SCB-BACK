package com.artsoft.scb.model.entity;

import java.io.Serializable;

public class StatePhaseAndAplicant implements Serializable {
	
	private Phase phase;
	
	private String state;

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
