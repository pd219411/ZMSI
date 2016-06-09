package test;

import com.ibm.able.Able;
import com.ibm.able.AbleException;
import com.ibm.able.rules.AbleRuleSet;
import com.ibm.able.rules.AbleRuleSetImpl;

public class Test01 {
	public static void main(String[] args) {
		try {
			String rules = "rules/Citizen.arl";
			AbleRuleSet ruleSet = new AbleRuleSetImpl();
			ruleSet.parseFromARL(rules);
			ruleSet.init();

			Customer[][] data = new Customer[][] {{new Customer(1)}, {new Customer(111)}};

			Customer[] input = null;
			Object[] output = null;

			Able.startTraceLogging(Able.TRC_LOW, Able.MSG_NONE, null);

			for (int i = 0; i < data.length; i++) {
				output = (Object[]) ruleSet.process(input = data[i]);
				Able.TraceLog.text(Able.TRC_LOW, "Input  : " + displayBuffer(input));
				Able.TraceLog.text(Able.TRC_LOW, "Output : " + displayBuffer(output));
			}
		} catch (AbleException ae) {
			if (ae.getExceptions() == null)
				System.err.println(ae.getLocalizedMessage());
			else
				for (Object o : ae.getExceptions()) {
					Exception e = (Exception) o;
					System.err.println(e.getLocalizedMessage());
				}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static String displayBuffer(Object[] buf) {
		StringBuffer buffer = new StringBuffer("");
		if (buf == null)
			buffer.append(buf);
		else
			for (int i = 0; i < buf.length; i++)
				buffer.append(buf[i] + " ");
		return buffer.toString();
	}
}