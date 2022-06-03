import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String MEMBER_INSERT = "insert into member(member_id, name, phone_number) values (?, ?, ?)";
	private static String MEMBER_UPDATE = "update member set phone_number = ? where member_id = ?";
	private static String MEMBER_DELETE = "delete member where member_id = ?";
	private static String MEMBER_GET    = "select * from member where member_id = ?";
	private static String MEMBER_LIST   = "select * from member order by member_id desc";
	
	public void insertMember(String memberId, String name, String phoneNumber) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, phoneNumber);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void updateMember(String phoneNumber, String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			stmt.setString(1, phoneNumber);
			stmt.setString(2, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void deleteMember(String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public boolean getMember(String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_GET);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
			    return true;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return false;
	}
	
	public List<Member> getMemberList() {
		List<Member> boardList = new ArrayList<Member>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
			    member.setMemberId(rs.getString("MEMBER_ID"));
			    member.setName(rs.getString("NAME"));
				member.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				boardList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}
}
