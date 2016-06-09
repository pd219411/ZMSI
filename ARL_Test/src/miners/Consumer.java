package miners;

import java.util.concurrent.ThreadLocalRandom;

import com.ibm.able.Able;
import com.ibm.able.AbleDefaultAgent;
import com.ibm.able.AbleEvent;
import com.ibm.able.AbleEventListener;
import com.ibm.able.AbleException;

import miners.utils.Order;
import miners.utils.Price;

public class Consumer extends AbleDefaultAgent {
	private static final long serialVersionUID = 0L;
	
	public Consumer(int identifier) throws AbleException {
		super("Consumer " + identifier);
		reset();
		init();
	}

	@Override
	public void init() throws AbleException {
		super.init();
	}

	@Override
	public void reset() throws AbleException {
		setAbleEventProcessingEnabled(Able.ProcessingEnabled_PostingEnabled);
	}

	@Override
	public void processTimerEvent() throws AbleException {
		process();
	}

	@Override
	public void process() throws AbleException {
		System.out.println(Thread.currentThread().getId() + " " + getName() + " process()");
		
//		float demand_units = 100.0f;
		float min_price = Float.MAX_VALUE;
		AbleEventListener min_price_corporation = null;

		for (Object o : getAbleEventListeners()) {
			double independent_factors = ThreadLocalRandom.current().nextDouble(1.0f);
			if (independent_factors < 0.5f) {
				System.out.println("Independent factors reject " + o.toString());
				continue;
			}
//			System.out.println(Thread.currentThread().getId() + " " + getName() + " demand>");
//			DemandMessage demand = new DemandMessage(20);
//			notifyAbleEventListeners(new AbleEvent(this, demand, 0, false));
			AbleEventListener corporation = (AbleEventListener)o;
			Price querry = new Price(0.0f);
			corporation.handleAbleEvent(new AbleEvent(this, querry, 0, false));
//			System.out.println(Thread.currentThread().getId() + " " + getName() + " <demand " + querry.price);
			if (min_price >= querry.price) {
				min_price = querry.price;
				min_price_corporation = corporation;
			}
		}

		System.out.println(Thread.currentThread().getId() + " min_price_coorporation " + min_price_corporation + " " + min_price + "$");
		if (min_price_corporation != null) {
			SendOrder(min_price_corporation);
		}
		
//		for (int i = 0; i < 5; i++) {
//			System.out.println(Thread.currentThread().getId() + " " + getName() + " demand>");
//			DemandMessage demand = new DemandMessage(20);
//			notifyAbleEventListeners(new AbleEvent(this, demand, 0, false));
//			System.out.println(Thread.currentThread().getId() + " " + getName() + " <demand " + demand.response_price_per_unit);
//		}
		
	}

	private void SendOrder(AbleEventListener corporation) throws AbleException {
		Order order = new Order();
		//order.units = 1.0f;
		corporation.handleAbleEvent(new AbleEvent(this, order, 0, false));
	}
	
	@Override
	public void processAbleEvent(AbleEvent evt) throws AbleException {
		System.exit(1);
		System.out.println(Thread.currentThread().getId() + " " + getName() + " processAbleEvent()");
		try {
			((AbleEventListener)evt.getSource()).handleAbleEvent(new AbleEvent(this, new String("ASD")));
			System.out.println(Thread.currentThread().getId() + " " + getName() + " after message");
//			DemandMessage msg = (DemandMessage) evt.getArgObject();
//			System.out.println(Thread.currentThread().getId() + ": Value " + msg.units);
//			if (msg.units == 1) {
//				System.out.println("GREAT");
//			} else {
//				notifyAbleEventListeners(new AbleEvent(this, msg));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
