package demo.spring.jdbc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmployeeDAOTest {
	private EmbeddedDatabase db;

	@Before
	public void setUp() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		db = builder.setType(EmbeddedDatabaseType.H2).addScript("employee.sql").addScript("employee_data.sql").build();
	}

	@Test
	public void testDataAccess() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.setJdbcTemplate(jdbcTemplate);
		Employee employee = employeeDAO.getById(1);

		assertEquals("{ID=1,Name=somkiat pui,Role=developer}", employee.toString());
	}

	@Test
	public void testDataAccess2() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.setJdbcTemplate(jdbcTemplate);
		Employee employee = employeeDAO.getById(2);

		assertNotNull(employee);
	}

	@After
	public void tearDown() {
		db.shutdown();
	}
}
