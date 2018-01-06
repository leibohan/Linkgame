package LLK.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import LLK.entity.TimeOrder;

public class HandleDB {
	
	private Connection conn = null;
	private PreparedStatement stat = null;
	private ResultSet rs = null;
	
	public ArrayList<TimeOrder> selectInfo(int level) {
		ArrayList<TimeOrder> al = new ArrayList<TimeOrder>();
		String sql = "select * from timeorder where [LEVEL]=" + level + " order by [SCORE] desc";
				conn = DBUtil.getConn();
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			int cnt = 0;
			while (rs.next() && cnt++ < 5) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int time = rs.getInt(3);
				int score = rs.getInt(4);
				TimeOrder to = new TimeOrder(id, name, time, score);
				al.add(to);
				System.out.println(id + " " + name + " " + time + " " + score);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	public void insertInfo(String name, int time, int score, int level) {
		conn = DBUtil.getConn();
		String sql = "select count(*) from timeorder where [TIME]<=" + time + " and [LEVEL]=" + level;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			System.out.println(name + " " + time + " " + score);
			if (rs.next()) {
				int n = rs.getInt(1);
				System.out.println(n);
				if (n <= 4) {
					sql = "insert into timeorder(NAME, TIME, SCORE, LEVEL) values('" + name + "'," + time + "," + score + "," + level + ")";
					stat = conn.prepareStatement(sql);
					stat.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	HandleDB hdb = new HandleDB();
		//	hdb.insertInfo("ww", 998);
	}

}
