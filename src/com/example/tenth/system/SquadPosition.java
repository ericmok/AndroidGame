package com.example.tenth.system;

import com.example.androidgame1.Troop;
import com.example.androidgame1.Vector3;
import com.example.tenth.system.FormationSystem.FormationNode;


/**
 * To make the units assemble in an orderly fashion...
 *
 */
public class SquadPosition {
	
	/** The leader of this position */
	public FormationNode leader = null;
		
	/** Location of the position */
	public Vector3 position = new Vector3();
	
	/** The follower of this position. ie. Choose closest troop to move into the position */
	public FormationNode follower = null;
	
	public SquadPosition() {		
	}
}
