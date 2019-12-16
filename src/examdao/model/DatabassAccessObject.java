package examdao.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author JK164-39
 *���������ݿ����Ӷ���ʵ�ֹ��ܣ�
 *�������ݿ⣬���ӡ�ɾ�����޸ġ���ѯ���ݡ�
 */
public class DatabassAccessObject {
	private Connection con;
 
	/**
	 * ���캯�����������ݿ�
	 * @throws Exception
	 */
	public DatabassAccessObject() throws Exception {
		String dburl = "jdbc:mysql://localhost:3306/examination?serverTimezone=UTC&characterEncoding=utf8&useSSL=false";
		String dbusername = "root";
		String dbpassword = "0000";
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.con = DriverManager.getConnection(dburl, dbusername, dbpassword);
	}
 
	/**
	 * ���ݿ��ѯ
	 * @param sql  �����SQL��ѯ���
	 * @param args ����������滻ռλ�����β�
	 * @return		����RestultSet���͵Ľ����
	 * @throws Exception
	 */
	public ResultSet query(String sql, Object... args) throws Exception {
		PreparedStatement ps = con.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
		return ps.executeQuery();
	}
 
	/**
	 * �����ݿ����һ������
	 * @param sql	�����SQL�������
	 * @param args	����������滻ռλ�����β�
	 * @return		����ֵ�ǲ�������
	 * @throws Exception
	 */
	public boolean insert(String sql, Object... args) throws Exception {
		PreparedStatement ps = con.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
		if (ps.executeUpdate() != 1) {
			return false;
		}
		return true;
	}
 
	/**
	 * �޸����ݿ��е�����
	 * @param sql	�����SQL�������
	 * @param args	����������滻ռλ�����β�
	 * @return		����ֵ�ǲ�������
	 * @throws Exception
	 */
	public boolean modify(String sql, Object... args) throws Exception {
		PreparedStatement ps = con.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
		if (ps.executeUpdate() != 1) {
			return false;
		}
		return true;
	}
 
	// �����������ж����ݿ������
	protected void finalize() throws Exception {
		if (!con.isClosed() || con != null) {
			con.close();
		}
	}
}
