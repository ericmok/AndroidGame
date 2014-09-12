package com.example.tenth.system;

import java.util.ArrayList;

import com.example.androidgame1.Game;
import com.example.androidgame1.RewritableArray;
import com.example.androidgame1.Vector3;

/**
 * For getting collision pairs
 *
 */
public class CollisionSystem {
	private Game game;
	
	public CollisionSystem(Game game) {
		this.game = game;
	}
	
	public void update(ArrayList<SystemNode> nodes, RewritableArray<CollisionNode> output, float collisionRadius, long elapsedTime) {
		
		output.resetWriteIndex();
		
		for (int i = 0; i < nodes.size(); i++) {
			CollisionNode cn = ((CollisionNodeBindable)nodes.get(i)).getCollisionNode();
			
		}
	}
	
	public static class CollisionPair {
		public SystemNode node0 = null;
		public SystemNode node1 = null;
	}
	
	public static class CollisionNode {
		public Vector3 position;
		public Vector3 velocity; // For a priori collisions
		public float[] radius;
		
		public CollisionNode(Vector3 position, Vector3 velocity, float[] radius) {
			this.position = position;
			this.velocity = velocity;
			this.radius = radius;
		}
	}
	
	public interface CollisionNodeBindable {
		public CollisionNode getCollisionNode();
	}
	
}
