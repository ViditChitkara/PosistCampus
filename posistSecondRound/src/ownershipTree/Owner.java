package ownershipTree;

import java.util.HashSet;

public class Owner {
	String name;
	int ownerId;
	HashSet<Integer> ownedNodes;
	static int ownerCount;
	static long nodeValueSum;
	
	Owner(String name) {
		this.name = name;
		this.ownerId = getOwnerCount();
		this.ownedNodes = new HashSet<>();
	}
	
	private static int getOwnerCount() {
		return ++ownerCount;
	}
	
	public Node createNode(Node parent, Node genesis, float val) {
		if (parent==null && genesis==null){
			setnodeValueSum(val);
			return new Node(this, parent, genesis, val);
		}
		else if (val<=getMaxValue(parent) && isValueLesserThanGenesis(genesis, val)) {
			setnodeValueSum(val);
			return new Node(this, parent, genesis, val);
		}
		
		System.out.println("Node cannot be created!!");
		return null;
		
	}
	
	public boolean authenticateOwner(Node n) {
		return this.ownedNodes.contains(n.data.key);
	}
	
	private boolean isValueLesserThanGenesis(Node genesis, float val) {
		return getnodeValueSum()+val<=genesis.data.value;
	}
	
	private static long getnodeValueSum() {
		return nodeValueSum;
	}
	
	private static void setnodeValueSum(float val) {
		nodeValueSum+=val;
	}
	
	private float getMaxValue(Node parent) {
		float parentVal = parent.data.value;
		float childrenSum=0;
		for(Node n: parent.childReferenceNodeIds) {
			childrenSum += n.data.value;
		}
		return parentVal - childrenSum;
	}
}
