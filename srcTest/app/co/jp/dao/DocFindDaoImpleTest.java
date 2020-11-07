package app.co.jp.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaranch.unittest.helper.sql.pool.JNDIUnitTestHelper;

import app.co.jp.dao.IF.DocFindDaoIF;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContextTest.xml")
public class DocFindDaoImpleTest {

	@Autowired
	DocFindDaoIF docFindDao;

	@Test
	public void case1() throws IOException, SQLException{
		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			String newId = docFindDao.getNewId();
			System.out.print(newId);

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Test
	public void case2() throws IOException, SQLException{

		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			String id = docFindDao.getParentId("6");
			System.out.print(id);

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}



}
