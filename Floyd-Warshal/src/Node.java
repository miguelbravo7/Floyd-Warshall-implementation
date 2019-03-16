import java.util.ArrayList;

public class Node {
	private int _node;
	private ArrayList<Integer> _joint = new ArrayList<Integer>();
	private ArrayList<Integer> _cost = new ArrayList<Integer>();
	
	public Node(int state) {
		this._node = state;
	}
	
	public int get_node() {
		return _node;
	}

	public ArrayList<Integer> get_joint() {
		return _joint;
	}

	public ArrayList<Integer> get_cost() {
		return _cost;
	}
	
	public String toString() {
		String salida = "";
		salida+=_node+"\n";
		for(int i=0 ; i<_joint.size();i++) {
			salida += "|-- " + _cost.get(i)+" -->"+_joint.get(i)+"\n";
		}
		return salida;
	}
	
	public void addNode(int node, int cost) {
		if(this._joint.contains(node)) {
			this._cost.set(this._joint.indexOf(node), cost);
		}else {
			this._joint.add(node);
			this._cost.add(cost);
		}
	}
	
	
}
