package bean;

public class CauseBBean {
	public String repName; //gchq/Gaffer
	public int causeAId; //causeA��
	public String  sha ; //B��commit��
	public int filePairId; //B��filePair��
//	public String  fullfile ;     //store-implementation/parquet-store/src/test/java/uk/gov/gchq/gaffer/parquetstore/utils/SortDataTest.java
	public int startPos;
	public int endPos;
//	public String className;    //uk.gov.gchq.gaffer.federatedstore.FederatedStoreSchemaTest
//	//fieldName��methodName��ֻ��һ������
//	public String fieldName;   //STRING
//	public String methodName;  //generatePreAggregatedData
	public int id;
	
	
	public CauseBBean(String repName, int causeAId, String sha, int filePairId, int startPos, int endPos) {
		super();
		this.repName = repName;
		this.causeAId = causeAId;
		this.sha = sha;
		this.filePairId = filePairId;
		this.startPos = startPos;
		this.endPos = endPos;
	}


	public CauseBBean() {
	}
}
