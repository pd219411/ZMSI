package miners;

public class Corporation { 
}

//import com.ibm.able.Able;
//import com.ibm.able.AbleDefaultAgent;
//import com.ibm.able.AbleEvent;
//import com.ibm.able.AbleException;
//
//import miners.Consumer.DemandMessage;
//
//public class Corporation extends AbleDefaultAgent {
//	private static final long serialVersionUID = 1L;
//
//	private int identifier;
//	
//	public Corporation(int identifier) throws AbleException {
//		super("Corporation " + identifier);
//		this.identifier = identifier;
//		reset();
//		init();
//	}
//
//	@Override
//	public void init() throws AbleException {
//		super.init();
//	}
//
//	@Override
//	public void reset() throws AbleException {
//		setAbleEventProcessingEnabled(Able.ProcessingEnabled_PostingEnabled);
//	}
//
//	@Override
//	public void process() throws AbleException {
////		System.out.println(Thread.currentThread().getId() + " " + getName());
////		notifyAbleEventListeners(new AbleEvent(this, new Message(171273)));
//	}
//
//	@Override
//	public void processAbleEvent(AbleEvent evt) throws AbleException {
//		System.out.println(Thread.currentThread().getId() + " " + getName() + " processAbleEvent()");
//		if (evt.getArgObject() instanceof Consumer.DemandMessage) {
//			System.out.println(Thread.currentThread().getId() + " " + getName() + " got demand");
//			DemandMessage demand = (DemandMessage) evt.getArgObject();
//			demand.response_units = 1.0f;
//			demand.response_price_per_unit = 100.0f + 10.0f * identifier;
//			
////			((AbleEventListener)evt.getSource()).handleAbleEvent(new AbleEvent(this, new Consumer.DemandMessage(222)));
//		}
//	}
//}
