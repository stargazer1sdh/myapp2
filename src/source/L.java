package chdistill.source;
import uk.gov.gchq.gaffer.parquetstore.utils.SortDataTest;
public class L {
	int lparam = 3;
	static String staticParam;
	static {
		new SortDataTest().generatePreAggregatedData();
		new SortDataTest().param1= 1;
	}
	void f() {
		lparam = 6;
		staticParam = null;
		this.lparam2 = 3;
	    SortDataTest dt = new SortDataTest();
		int a=1+dt.param2;
		dt.generatePreAggregatedData();
		new SortDataTest().param3 = 3;
		int b=1+new SortDataTest().param4.param5;
	}
}