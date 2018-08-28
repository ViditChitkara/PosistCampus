package ownershipTree;

import java.security.SecureRandom;

public class NodeData {
	int ownerId;
	float value;
	String ownerName;
	int key;
	
	NodeData(Owner owner, float value) {
		this.ownerId = owner.ownerId;
		this.value = value;
		this.ownerName = owner.name;
		generateKey(owner);
	}
	
	public void generateKey(Owner o) {
		SecureRandom s = new SecureRandom();
		int k = s.nextInt();
		o.ownedNodes.add(k);
		this.key = k;
	}
}
