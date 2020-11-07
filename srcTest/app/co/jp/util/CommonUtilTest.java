package app.co.jp.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import app.co.jp.model.DocModel;

public class CommonUtilTest {

	@Test
	public void test() {

		List<DocModel> docModelList = new ArrayList<DocModel>();
		DocModel docModel1 = new DocModel();
		DocModel docModel2 = new DocModel();
		DocModel docModel3 = new DocModel();
		DocModel docModel4 = new DocModel();
		DocModel docModel5 = new DocModel();

		docModel1.setId("15");
		docModel2.setId("5");
		docModel3.setId("21");
		docModel4.setId("20");
		docModel5.setId("10");
		docModelList.add(docModel1);
		docModelList.add(docModel2);
		docModelList.add(docModel3);
		docModelList.add(docModel4);
		docModelList.add(docModel5);

		CommonUtil commonUtil = new CommonUtil();
		int id = commonUtil.getMaxId(docModelList);

		System.out.print(id);


	}



}
