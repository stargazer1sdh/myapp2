package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bean.CauseABean;
import bean.CauseABean1;
import bean.CauseBBean;
import bean.CauseBBean1;
import bean.FilePair;

public class DBUtils {
	private static Connection conn = null;
	static {
		while (!setCon()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static boolean setCon() {
		boolean flag = false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
			if (null != conn)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static void shut() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean hasInsertedCommit(String sha) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM conflict.commit where sha=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sha);
			rs = pstmt.executeQuery();
		    return rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return hasInsertedCommit(sha);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
	}

	public static void main(String[] args){
//		CauseABean1 a = getCauseA1(1);
		List<CauseBBean1> allCauseB1sNullVaild = getAllCauseB1sNullVaild();
		System.out.println();
	}

	public static List<CauseBBean1> getAllCauseB1s() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,causeAId,filePairId,startPos,endPos,id FROM conflict.causeB1";
		List<CauseBBean1> list = new ArrayList<CauseBBean1>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i=1;
				CauseBBean1 causeb = new CauseBBean1();
				causeb.repName = rs.getString(i++);
				causeb.sha = rs.getString(i++);
				causeb.causeAId = rs.getInt(i++) ;
				causeb.filePairId = rs.getInt(i++);
				causeb.startPos = rs.getInt(i++);
				causeb.endPos = rs.getInt(i++);
				causeb.id = rs.getInt(i++);
				list.add(causeb);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return list;
	}
	public static List<CauseBBean1> getAllCauseB1sNullVaild() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,causeAId,filePairId,startPos,endPos,id FROM conflict.causeB1 where valid is ? or valid=?";
		List<CauseBBean1> list = new ArrayList<CauseBBean1>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNull(1, Types.VARCHAR);
			pstmt.setString(2, "");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i=1;
				CauseBBean1 causeb = new CauseBBean1();
				causeb.repName = rs.getString(i++);
				causeb.sha = rs.getString(i++);
				causeb.causeAId = rs.getInt(i++) ;
				causeb.filePairId = rs.getInt(i++);
				causeb.startPos = rs.getInt(i++);
				causeb.endPos = rs.getInt(i++);
				causeb.id = rs.getInt(i++);
				list.add(causeb);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return list;
	}
	public static List<CauseBBean> getAllCauseBs() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,causeAId,filePairId,startPos,endPos,id FROM conflict.causeB";
		List<CauseBBean> list = new ArrayList<CauseBBean>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i=1;
				CauseBBean causeb = new CauseBBean();
				causeb.repName = rs.getString(i++);
				causeb.sha = rs.getString(i++);
				causeb.causeAId = rs.getInt(i++) ;
				causeb.filePairId = rs.getInt(i++);
				causeb.startPos = rs.getInt(i++);
				causeb.endPos = rs.getInt(i++);
				causeb.id = rs.getInt(i++);
				list.add(causeb);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return list;
	}
	public static List<CauseBBean> getAllCauseBNullVaild() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,causeAId,filePairId,startPos,endPos,id FROM conflict.causeB  where valid is ? or valid=?";
		List<CauseBBean> list = new ArrayList<CauseBBean>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNull(1, Types.VARCHAR);
			pstmt.setString(2, "");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i=1;
				CauseBBean causeb = new CauseBBean();
				causeb.repName = rs.getString(i++);
				causeb.sha = rs.getString(i++);
				causeb.causeAId = rs.getInt(i++) ;
				causeb.filePairId = rs.getInt(i++);
				causeb.startPos = rs.getInt(i++);
				causeb.endPos = rs.getInt(i++);
				causeb.id = rs.getInt(i++);
				list.add(causeb);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return list;
	}

	public static FilePair getFilePair(int filePairId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT commitsha, dir, prevfpath, filename FROM conflict.filePair where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filePairId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int i=1;
				FilePair fp = new FilePair(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++));
				fp.id = filePairId;
				return fp;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return null;
	}

	public static CauseABean getCauseA(int causeAId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,fullfile,changeStr,changedRoot,className,fieldName,methodName FROM conflict.causeA where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, causeAId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int i=1;
				CauseABean causeA = new CauseABean();
				causeA.repName = rs.getString(i++);
				causeA.sha = rs.getString(i++);
				causeA.fullfile = rs.getString(i++);
				causeA.changeStr = rs.getString(i++);
				causeA.changedRoot = rs.getString(i++);
				causeA.className = rs.getString(i++);
				causeA.fieldName = rs.getString(i++);
				causeA.methodName = rs.getString(i++);
				causeA.id = causeAId;
				return causeA;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return null;
	}
	public static CauseABean1 getCauseA1(int causeAId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT repName,sha,fullfile,className,fieldName,type0,type1 FROM conflict.causeA1 where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, causeAId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int i=1;
				CauseABean1 causeA = new CauseABean1();
				causeA.repName = rs.getString(i++);
				causeA.sha = rs.getString(i++);
				causeA.fullfile = rs.getString(i++);
				causeA.className = rs.getString(i++);
				causeA.fieldName = rs.getString(i++);
				causeA.type0 = rs.getString(i++);
				causeA.type1 = rs.getString(i++);
				causeA.id = causeAId;
				return causeA;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
		return null;
	}

	public static int updateCauseBValid(int id, boolean hasValid, boolean hasError) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE conflict.causeb SET valid = ? WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			if(hasError)
				pstmt.setString(i++, "E");
			else
				pstmt.setString(i++, hasValid?"y":"n");
			pstmt.setInt(i++, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
	}
	public static int updateCauseB1Valid(int id, boolean hasValid, boolean hasError) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE conflict.causeb1 SET valid = ? WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			if(hasError)
				pstmt.setString(i++, "E");
			else
				pstmt.setString(i++, hasValid?"y":"n");
			pstmt.setInt(i++, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				pstmt = null;
			}
		}
	}

}

