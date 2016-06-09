package miners;

import java.util.Vector;

import com.ibm.able.AbleBean;
import com.ibm.able.AbleException;
import com.ibm.able.agents.AbleRuleAgent;
import com.ibm.able.agents.AbleRuleAgentImpl;

import miners.utils.Price;

public class App {
	public static class Data {
		public int value;
	}

	public static void main(String[] args) {
		try {
			int n = 4;
			AbleBean consumer = new Consumer(0);
			AbleBean[] corporations = new AbleBean[n];
			for (int i = 0; i < n; ++i) {
//				AbleRuleAgentImpl tmp = new AbleRuleAgentImpl();
//				tmp.setBehaviorRuleSetFileName("rules/Simple.arl");

				corporations[i] = CreateCorporation(i);
			}

			for (int i = 0; i < n; ++i) {
				consumer.addAbleEventListener(corporations[i]);
			}

			/////
			Vector<Route> routes = new Vector<Route>();
			routes.add(new Route(1, 0));

			Vector<Mine> mines = new Vector<Mine>();
			mines.add(new Mine(1));
			//ASSERT ROUTE FOR EACH MINE EXISTS
			
			for (int i = 0; i < 5; ++i) {
				System.out.println("=== Phase Start ===");
				int coorp = 0;
				for (AbleBean a : corporations) {
					Vector<Ship> ships = new Vector<Ship>();
					ships.add(new Ship());
					a.setInputBuffer(0, new Price(coorp + 30.0f));
					a.setInputBuffer(1, routes);
					a.setInputBuffer(2, mines);
					a.setInputBuffer(3, ships);
					
					a.process();
					++coorp;
				}

				System.out.println("=== Orders ===");
				consumer.process();
			}
			////////////////

			consumer.quitAll();
			for (AbleBean a : corporations) {
				a.quitAll();
			}
			System.out.println("=== The End ===");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AbleBean CreateCorporation(int i) throws AbleException {
		AbleRuleAgent agent = new AbleRuleAgentImpl("Spaceship Agent");
		agent.setBehaviorRuleSetFileName("rules/spaceship.arl");
		agent.init();
		return agent;

//		return new Corporation(i);
	}
}
