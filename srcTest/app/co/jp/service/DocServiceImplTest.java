package app.co.jp.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaranch.unittest.helper.sql.pool.JNDIUnitTestHelper;

import app.co.jp.model.DocModel;
import app.co.jp.model.JsonModel;
import app.co.jp.model.JsonModel.SubJsonModel;
import app.co.jp.service.IF.DocServiceIF;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContextTest.xml")
public class DocServiceImplTest {

	@Autowired
	DocServiceIF docService;

	@Test
	public void case1() throws IOException, SQLException {

		List<DocModel> docModelList = new ArrayList<DocModel>();

		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			docModelList = docService.getTree();


		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	@Test
	public void case2() throws IOException, SQLException {

		JsonModel jsonModel = new JsonModel();
		SubJsonModel subJsonModel = jsonModel.new SubJsonModel();

		JsonModel jsonModel_1 = new JsonModel();
		SubJsonModel subJsonModel_1 = jsonModel.new SubJsonModel();

		JsonModel jsonModel_2 = new JsonModel();
		SubJsonModel subJsonModel_2 = jsonModel.new SubJsonModel();

		jsonModel.setId("1");
		jsonModel.setIcon("jstree-folder");
		jsonModel.setText("１");
		subJsonModel.setOpened(true);
		subJsonModel.setDisabled(true);
		subJsonModel.setSelected(true);
		jsonModel.setState(subJsonModel);

		jsonModel_1.setId("2");
		jsonModel_1.setParentId("1");
		jsonModel_1.setIcon("jstree-folder");
		jsonModel_1.setText("１－１");
		subJsonModel_1.setOpened(false);
		subJsonModel_1.setDisabled(false);
		subJsonModel_1.setSelected(false);
		jsonModel_1.setState(subJsonModel_1);

		jsonModel_2.setId("3");
		jsonModel_2.setParentId("2");
		jsonModel_2.setIcon("jstree-folder");
		jsonModel_2.setText("１－１－１");
		subJsonModel_2.setOpened(false);
		subJsonModel_2.setDisabled(false);
		subJsonModel_2.setSelected(false);
		jsonModel_2.setState(subJsonModel_2);

		jsonModel.getChildren().add(jsonModel_1);
		jsonModel.getChildren().get(0).getChildren().add(jsonModel_2);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(jsonModel);

		System.out.print(jsonData);

	}

	@Test
	public void case3() throws IOException, SQLException {

		DocModel docModel = new DocModel();
		DocModel docModel1 = new DocModel();
		DocModel docModel2 = new DocModel();
		DocModel docModel3 = new DocModel();
		DocModel docModel4 = new DocModel();
		DocModel docModel5 = new DocModel();
		DocModel docModel6 = new DocModel();

		docModel.setId("1");
		docModel.setText("１");
		docModel.setParentId("");

		docModel1.setId("2");
		docModel1.setText("１－１");
		docModel1.setParentId("1");
		docModel.getChildren().add(docModel1);
		//docModelList.add(docModel1);

		docModel2.setId("3");
		docModel2.setText("１－２");
		docModel2.setParentId("1");
		docModel.getChildren().add(docModel2);
		//docModelList.add(docModel2);

		docModel3.setId("4");
		docModel3.setText("１－１－１");
		docModel3.setParentId("2");
		docModel.getChildren().get(0).getChildren().add(docModel3);
		//docModelList.get(0).getChildren().add(docModel3);

		docModel4.setId("5");
		docModel4.setText("１－２－１");
		docModel4.setParentId("3");
		docModel.getChildren().get(1).getChildren().add(docModel4);
		//docModelList.get(1).getChildren().add(docModel4);

		docModel5.setId("6");
		docModel5.setText("１－１－１－１");
		docModel5.setParentId("4");
		docModel.getChildren().get(0).getChildren().get(0).getChildren().add(docModel5);
		//docModelList.get(0).getChildren().get(0).getChildren().add(docModel5);


		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			List<DocModel> docModelList2 = new ArrayList<DocModel>();
			List<DocModel> docModelList = new ArrayList<DocModel>();
			docModelList = docService.setChildDocModel("",docModel,docModelList);

			System.out.print("OK");

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.print("NG");
		}

	}

	@Test
	public void case4() throws IOException, SQLException {

		DocModel docModel = new DocModel();
		DocModel docModel1 = new DocModel();
		DocModel docModel2 = new DocModel();

		docModel.setId("1");
		docModel.setText("１");
		docModel.setParentId("");

		docModel1.setId("2");
		docModel1.setText("１－１");
		docModel1.setIcon("jstree-file");
		docModel1.setParentId("1");
		docModel.getChildren().add(docModel1);

		docModel2.setId("2");
		docModel2.setText("１－２");
		docModel2.setIcon("jstree-folder");
		docModel2.setParentId("1");
		docModel.getChildren().add(docModel2);

		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			List<DocModel> docModelList2 = new ArrayList<DocModel>();
			List<DocModel> docModelList = new ArrayList<DocModel>();
			docModelList = docService.setChildDocModel("",docModel,docModelList);

			System.out.print("OK");

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.print("NG");
		}

	}

	@Test
	public void case5() throws IOException, SQLException {
		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			DocModel docModel = new DocModel();
			docModel = docService.getDocument("19");

			System.out.print("OK");

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.print("NG");
		}
	}

	@Test
	public void case6() throws IOException, SQLException {


		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			int maxFileId = docService.getMaxFileId();

			System.out.print(maxFileId);

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}

