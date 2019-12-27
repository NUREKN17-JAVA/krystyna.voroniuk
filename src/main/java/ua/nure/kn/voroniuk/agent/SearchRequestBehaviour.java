package ua.nure.kn.voroniuk.agent;

import jade.core.behaviours.Behaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class SearchRequestBehaviour extends Behaviour {
	
	private AID[] aids;
	private String firstName;
	private String lastName;

	public SearchRequestBehaviour(AID[] aids, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.aids = aids;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public void action() {
		// TODO Auto-generated method stub
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.setContent(firstName + ", " + lastName);
		if (aids != null) {
			for (int i = 0; i < aids.length; i++) {
				message.addReceiver(aids[i]);
			}
			myAgent.send(message);
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
