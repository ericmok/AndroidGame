package com.example.androidgame1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import com.example.tenth.system.BattleSystem.BattleNode;
import com.example.tenth.system.BattleSystem.BattleNodeBindable;
import com.example.tenth.system.FieldUnitNode;
import com.example.tenth.system.FieldUnitNode.FieldUnitNodeBindable;
import com.example.tenth.system.ForceIntegratorSystem.ForceIntegratorNode;
import com.example.tenth.system.ForceIntegratorSystem.ForceIntegratorNodeBindable;
import com.example.tenth.system.FormationSystem.FormationNode;
import com.example.tenth.system.FormationSystem.FormationNodeBindable;
import com.example.tenth.system.OrientationSystem.OrientationNode;
import com.example.tenth.system.OrientationSystem.OrientationNodeBindable;
import com.example.tenth.system.SeparationSystem.SeparationNode;
import com.example.tenth.system.SeparationSystem.SeparationNodeBindable;
import com.example.tenth.system.ShipDrawSystem.ShipDrawNode;
import com.example.tenth.system.ShipDrawSystem.ShipDrawNodeBindable;
import com.example.tenth.system.SquadPosition;
import com.example.tenth.system.SystemNode;

public class SmallShip implements SystemNode, 
									FieldUnitNodeBindable, FormationNodeBindable, SeparationNodeBindable, 
									ForceIntegratorNodeBindable, 
									BattleNodeBindable, 
									ShipDrawNodeBindable,
									OrientationNodeBindable {

	public static final HashSet<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
		this.add(SystemNode.NodeType.FIELD_MOVEMENT);
		this.add(SystemNode.NodeType.FORMATION);
		this.add(SystemNode.NodeType.SEPARATION);
		this.add(SystemNode.NodeType.BATTLE);
		this.add(SystemNode.NodeType.FORCE_INTEGRATOR);
		this.add(SystemNode.NodeType.SHIP_DRAW);
		this.add(SystemNode.NodeType.ORIENTATION);
	}};
	
	public HashSet<String> TAGS = new HashSet<String>(8) {{
		this.add(SmallShip.class.getSimpleName());
	}};
	public HashSet<String> getTags() { return TAGS; }
	
	public final int[] team = new int[] { 0 };
	
	public final Vector3 position = new Vector3();
	public final Vector3 velocity = new Vector3();
	
	public final Orientation orientation = new Orientation();
	public final float[] turningRate = new float[] { 0.006f };
	
	public OrientationNode orientationNode;
	
	public final float[] radius = new float[] { 0.12f };
	public final Boolean[] isAlive = new Boolean[] {true};
	
	public final Hashtable<String, Boolean> states = new Hashtable<String, Boolean>(16);
	public final ArrayList<String> events = new ArrayList<String>(16);
	
	public final Vector3 fieldForce = new Vector3();
	public final Vector3 separationForce = new Vector3();
	public final Vector3 formationForce = new Vector3();
	
	public final float[] fieldForceSensitivity = new float[] { 0.9f };
	
	public final float[] leadershipPropensity = new float[] { 1 };
	
	private FieldUnitNode fieldUnitNode;
	private FormationNode formationNode;
	private SeparationNode separationNode;
	private BattleNode battleNode;
	
	private ForceIntegratorNode forceIntegratorNode;

	private ShipDrawNode shipDrawNode;
	
	private SquadPosition squadPositionToFollow = new SquadPosition();
	private RewritableArray<SquadPosition> squadPositions = new RewritableArray<SquadPosition>(SquadPosition.class, 64);
	
	public Hashtable<String, TimedProgress> stateAnimations;
	
	public SmallShip() {
		fieldUnitNode = new FieldUnitNode(position, velocity,orientation, fieldForce, fieldForceSensitivity, leadershipPropensity, this, isAlive, states, events);
		formationNode = new FormationNode(position, velocity, orientation, formationForce, isAlive, squadPositionToFollow, squadPositions);
		separationNode = new SeparationNode(position, velocity, orientation, separationForce, leadershipPropensity, isAlive, states, this);
		
		forceIntegratorNode = new ForceIntegratorNode(position, velocity, orientation, separationForce, formationForce, fieldForce, this, isAlive, states);
		
		battleNode = new BattleNode(position, velocity, orientation, isAlive, states);
		
		orientationNode = new OrientationNode(velocity, orientation, turningRate);
		
		shipDrawNode = new ShipDrawNode(position, velocity, orientation, radius, isAlive, team, stateAnimations);
	}
	
	@Override
	public HashSet<NodeType> getNodes() {
		return NODE_TYPES;
	}

	@Override
	public BattleNode getBattleNode() {
		return battleNode;
	}

	@Override
	public SeparationNode getSeparationSystemNode() {
		return separationNode;
	}

	@Override
	public FormationNode getFormationNode() {
		return formationNode;
	}

	@Override
	public FieldUnitNode getFieldUnitNode() {
		return fieldUnitNode;
	}

	public ForceIntegratorNode getForceIntegratorNode() {
		return forceIntegratorNode;
	}

	@Override
	public ShipDrawNode getShipDrawNode() {
		return shipDrawNode;
	}

	@Override
	public OrientationNode getOrientationNode() {
		return orientationNode;
	}
}
