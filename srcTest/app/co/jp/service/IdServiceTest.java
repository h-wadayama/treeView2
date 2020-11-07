package app.co.jp.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaranch.unittest.helper.sql.pool.JNDIUnitTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContextTest.xml")
public class IdServiceTest {

	@Autowired
	IdService idService;

	@Test
	public void test() throws IOException, SQLException {

		try {
			JNDIUnitTestHelper.init("srcTest/jndi_unit_test_helper.properties");

			int id = idService.getMaxId();

			System.out.print(id);

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



	}

}
