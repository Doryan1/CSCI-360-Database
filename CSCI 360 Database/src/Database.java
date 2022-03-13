// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

// Temporary object. to be replaced with the real one later
class Professor{
	int id;
	String department;
	String firstName;
	String lastName;
	String birthDate;
	public Professor(int id, String department, String firstName, String lastName, String birthDate) {
		this.id = id;
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}
}

public class Database
{
	private final Connection connection;
	private final PreparedStatement insertPerson;
	private final PreparedStatement insertProfessor;
	private final PreparedStatement getProfessor;
	private final PreparedStatement updatePerson;
	private final PreparedStatement updateProfessor;
	private final PreparedStatement deleteProfessor;
	private final PreparedStatement deletePerson;
	public Database(String dbPath) throws SQLException
	{
		final var url = "jdbc:sqlite:" + dbPath;
		this.connection = DriverManager.getConnection(url);
		this.connection.setAutoCommit(false);
		this.insertPerson = this.connection.prepareStatement("INSERT INTO PERSON (first_name, last_name, birth_date) VALUES (?, ?, ?) RETURNING id");
		this.updatePerson = this.connection.prepareStatement("UPDATE PERSON SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?");
		this.deletePerson = this.connection.prepareStatement("DELETE FROM PERSON WHERE id = ? RETURNING *");
		this.insertProfessor = this.connection.prepareStatement("INSERT INTO EMPLOYEE (person_id, department) VALUES (?, ?)");
		this.getProfessor = this.connection.prepareStatement("SELECT first_name, last_name, birth_date, department FROM EMPLOYEE INNER JOIN PERSON ON EMPLOYEE.person_id = PERSON.id WHERE id = ?");
		this.updateProfessor = this.connection.prepareStatement("UPDATE EMPLOYEE SET department = ? WHERE person_id = ?");
		this.deleteProfessor = this.connection.prepareStatement("DELETE FROM EMPLOYEE WHERE person_id = ? RETURNING *");
	}

	public int addProfessor(String firstName, String lastName, String birthDate, String department) throws SQLException
	{
		this.connection.rollback();
		this.insertPerson.setString(1, firstName);
		this.insertPerson.setString(2, lastName);
		this.insertPerson.setString(3, birthDate);
		final var insertPersonResult = insertPerson.executeQuery();
		final var id = insertPersonResult.getInt("id");
		// Result needs to be closed or else database will complain about connection contention during commit.
		insertPersonResult.close();

		this.insertProfessor.setInt(1, id);
		this.insertProfessor.setString(2, department);
		this.insertProfessor.execute();
		this.connection.commit();
		return id;
	}

	public Professor getProfessor(int id) throws SQLException
	{
		this.connection.rollback();
		this.getProfessor.setInt(1, id);
		final var res = this.getProfessor.executeQuery();
		final var fn = res.getString("first_name");
		final var ln = res.getString("last_name");
		final var bd = res.getString("birth_date");
		final var dp = res.getString("department");
		return new Professor(id, fn, ln, bd, dp);
	}

	public void updateProfessor(Professor professor) throws SQLException
	{
		this.connection.rollback();
		this.updateProfessor.setInt(1, professor.id);
		this.updateProfessor.setString(2, professor.department);
		if(this.updateProfessor.executeUpdate() == 0)
		{
			this.connection.rollback();
			throw new SQLException("Professor record does not exist");
		}

		this.updateProfessor.setString(1, professor.firstName);
		this.updateProfessor.setString(2, professor.lastName);
		this.updateProfessor.setString(3, professor.birthDate);
		this.updateProfessor.setInt(4, professor.id);
		if(this.updatePerson.executeUpdate() == 0)
		{
			this.connection.rollback();
			throw new SQLException("Person record does not exist");
		}

		this.connection.commit();
	}

	public Professor deleteProfessor(int id) throws SQLException
	{
		this.connection.rollback();
		this.deleteProfessor.setInt(1, id);
		final var deleteProfessorRes = this.deleteProfessor.executeQuery();
		final var department = deleteProfessorRes.getString("department");
		// Result needs to be closed or else database will complain about connection contention during commit.
		deleteProfessorRes.close();

		this.deletePerson.setInt(1, id);
		final var deletePersonRes = this.deletePerson.executeQuery();
		final var firstName = deletePersonRes.getString("first_name");
		final var lastName = deletePersonRes.getString("last_name");
		final var birthDate = deletePersonRes.getString("birth_date");
		// Result needs to be closed or else database will complain about connection contention during commit.
		deletePersonRes.close();

		this.connection.commit();
		return new Professor(id, department, firstName, lastName, birthDate);
	}
}