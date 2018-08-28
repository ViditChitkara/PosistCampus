package ownershipTree;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

class Node {
	Date timestamp;
	int nodeNumber;
	String nodeId;
	Node referenceNodeId, genesisReferenceNodeId;
	ArrayList<Node> childReferenceNodeIds;
	NodeData data;
	static int nodeCount;
	
	Node(Owner owner, Node parent, Node genesis, float nodeDataValue) {
		this.nodeNumber = getNodeCount();
		this.timestamp = new Date();
		this.referenceNodeId = parent;
		this.genesisReferenceNodeId = genesis;
		this.nodeId = this.generateKey()+"";
		this.data = new NodeData(owner, nodeDataValue);
	}
	
	private int generateKey() {
		SecureRandom s = new SecureRandom();
		return s.nextInt();
	}
	
	Node createChildNode(Owner owner, Node genesis, float val) {
		Node newNode = new Node(owner, this, genesis, val);
		this.childReferenceNodeIds.add(newNode);
		return newNode;
	}
	
	public void transferOwnership(Owner from, Owner to) {
		this.data.ownerId = to.ownerId;
		this.data.ownerName = to.name;
		from.ownedNodes.remove(this.data.key);
	}
	
	private static int getNodeCount() {
		return ++nodeCount;
	}
	
	public static int getMaxChain(Node n) {
		if (n.childReferenceNodeIds.size()==0){
			return 1;
		}
		
		int maxHeight = 1;
		for (Node node: n.childReferenceNodeIds) {
			int h = Node.getMaxChain(node);
			if (h>maxHeight) {
				maxHeight = h;
			}
		}
		
		return maxHeight;
	}
	
	
}
