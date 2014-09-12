package com.example.tenth.system;

import com.example.androidgame1.TimedProgress;

public class LivingComponent {
	
	public enum Transition {
		SPAWNING,
		DYING
	};
	
	public enum State {
		PRISTINE,
		ALIVE,
		DEAD
	};
	
	public Transition transition = null;

	public float millisecondTimeOfTransition;
	
	public TimedProgress transitionProgress = new TimedProgress();
	
	public State state = State.PRISTINE;
	
}
