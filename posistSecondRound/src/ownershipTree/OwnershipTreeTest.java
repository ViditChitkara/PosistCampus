package ownershipTree;

import java.security.SecureRandom;
import java.util.Date;

public class OwnershipTreeTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Owner o = new Owner("Vidit");
		Owner o1 = new Owner("Vidit1");
		Node genesis = o1.createNode(null, null, 30);
		genesis.transferOwnership(o1, o);
		System.out.println(o.authenticateOwner(genesis));
	}

}
