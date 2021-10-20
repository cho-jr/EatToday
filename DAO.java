package eatToday;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	UserVO uVo = null;
	MenuVO mVo = null;
	
	public DAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/java17_cjr";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("DB 연동 실패");
		}
	}
	
	
	// close
	public void dbClose() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	public void pstmtClose() {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	public void rsClose() {
		if(rs!=null) {
			try {
				rs.close();
				pstmtClose();
			} catch (SQLException e) {}
		}
	}

	// 메소드들~~~ ^~^
	// 전체 메뉴 조회 (목록 조회)
	public Vector getList(UserVO uVo, String list, String sortBy) {
		Vector vData = new Vector();
		String addSql= "";
		String addSql2 = "";
		if(list.contains("_1")) {
			addSql = " where user_1 = true";
			addSql2 = ", "+uVo.getId()+".user_1";
		} else if(list.contains("_2")) {
			addSql = " where user_2 = true";
			addSql2 = ", "+uVo.getId()+".user_2";
		}
		
		if(sortBy.equals("prefer") || sortBy.equals("choose")) {
			sortBy = uVo.getId()+"." + sortBy;
		} else {
			sortBy = "allmenu." + sortBy;
		}
		
		try {
			sql = "select allmenu.classi, allmenu.shop, allmenu.menu, "+uVo.getId()+".prefer, allmenu.locate, "+uVo.getId()+".choose"+addSql2+" from allmenu join "+uVo.getId()
					+ " on allmenu.idx = "+uVo.getId()+".menu" + addSql +" order by " + sortBy + " desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				Vector vo = new Vector();
				vo.add(i);
				vo.add(rs.getString("classi"));
				vo.add(rs.getString("shop"));
				vo.add(rs.getString("menu"));
				vo.add(rs.getInt("prefer"));
				vo.add(rs.getString("locate"));
				vo.add(rs.getInt("choose"));
				
				vData.add(vo);
				i++;
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		
		return vData;
	}
	
	// 전체 메뉴 조회 (리스트 설정)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getListSet(UserVO uVo, String list, String sortBy) {
		Vector vData = new Vector();
		String addSql2 = "";
		if(list.contains("_1")) {
			addSql2 = ", "+uVo.getId()+".user_1";
		} else if(list.contains("_2")) {
			addSql2 = ", "+uVo.getId()+".user_2";
		}
		
		if(sortBy.equals("prefer") || sortBy.equals("choose")) {
			sortBy = uVo.getId()+"." + sortBy;
		} else {
			sortBy = "allmenu." + sortBy;
		}
		
		try {
			sql = "select allmenu.classi, allmenu.shop, allmenu.menu, "+uVo.getId()+".prefer, allmenu.locate, "+uVo.getId()+".choose"+addSql2
					+" from allmenu join "+uVo.getId()
					+ " on allmenu.idx = "+uVo.getId()+".menu order by " + sortBy + " desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				Vector vo = new Vector();
				vo.add(i);
				vo.add(rs.getString("classi"));
				vo.add(rs.getString("shop"));
				vo.add(rs.getString("menu"));
				vo.add(rs.getInt("prefer"));
				vo.add(rs.getString("locate"));
				vo.add(rs.getInt("choose"));
				if(list.contains("_1")) {
					vo.add(rs.getBoolean("user_1"));
				} else if(list.contains("_2")) {
					vo.add(rs.getBoolean("user_2"));
				}
				
				vData.add(vo);
				i++;
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}
	
	// 전체 메뉴 조회해서 mVo ArrayList로 넘김
	public ArrayList<MenuVO> getListMVO(UserVO uVo, String list, String sortBy) {
		ArrayList<MenuVO> vData = new ArrayList<MenuVO>();
		try {
			String addSql= "";
			if(list.contains("_1")) {
				addSql = " and "+uVo.getId()+".user_1 = true";
			} else if(list.contains("_2")) {
				addSql = " and "+uVo.getId()+".user_2 = true";
			} 
						
			if(sortBy.equals("prefer") || sortBy.equals("choose")) {
				sortBy = uVo.getId()+"." + sortBy;
			} else {
				sortBy = "allmenu." + sortBy;
			}
					
			sql = "select allmenu.shop, allmenu.menu, allmenu.locate from allmenu, "+uVo.getId()+" where allmenu.idx = "+uVo.getId()+".menu"+addSql+" order by " + sortBy + " desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MenuVO vo = new MenuVO();

				vo.setShop(rs.getString("shop"));
				vo.setMenu(rs.getString("menu"));
				vo.setLocate(rs.getString("locate"));

				vData.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}

	// 조건 검색 (리스트 설정 조회)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getSearchSet(UserVO uVo, String list, String column, String strSearch) {
		Vector vData = new Vector();
		
		String addSql = "";
		if(list.contains("_1")) {
			addSql = ", "+uVo.getId()+".user_1";
		} else if(list.contains("_2")) {
			addSql = ", "+uVo.getId()+".user_2";
		}
		
		if(column.equals("prefer") || column.equals("choose")) {
			column = uVo.getId()+"." + column;
		} else {
			column = "allmenu." + column;
		}
		
		try {
			sql = "select allmenu.classi, allmenu.shop, allmenu.menu, "+uVo.getId()+".prefer, allmenu.locate, "+uVo.getId()+".choose"+addSql
					+ " from allmenu, "+uVo.getId()+" where allmenu.idx = "+uVo.getId()+".menu and "+column+" like ? order by "+column+" desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+strSearch+"%");
			rs = pstmt.executeQuery();
			
			int idx = 1;
			while(rs.next()) {
				Vector vo = new Vector();
				vo.add(idx);
				vo.add(rs.getString("classi"));
				vo.add(rs.getString("shop"));
				vo.add(rs.getString("menu"));
				vo.add(rs.getInt("prefer"));
				vo.add(rs.getString("locate"));
				vo.add(rs.getInt("choose"));
				if(list.contains("_1")) {
					vo.add(rs.getBoolean("user_1"));
				} else if(list.contains("_2")) {
					vo.add(rs.getBoolean("user_2"));
				}
				
				vData.add(vo);
				idx++;
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
			System.out.println(sql);
		} finally {
			rsClose();
		}
		return vData;
	}
	// 조건 검색 (목록 조회)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getSearch(UserVO uVo, String list, String column, String strSearch) {
		Vector vData = new Vector();
		try {
			if(column.equals("prefer") || column.equals("choose")) {
				column = uVo.getId()+"." + column;
			} else {
				column = "allmenu." + column;
			}
			
			String addSql = "";
			if(list.contains("_1")) {
				addSql = "and "+uVo.getId()+".user_1 = true ";
			} else if(list.contains("_2")) {
				addSql = "and "+uVo.getId()+".user_2 = true ";
			}
			
			sql = "select allmenu.classi, allmenu.shop, allmenu.menu, "+uVo.getId()+".prefer, allmenu.locate, "+uVo.getId()+".choose "
					+ "from allmenu, "+uVo.getId()+" where allmenu.idx = "+uVo.getId()+".menu "+addSql+"and "
					+column+" like ? order by "+column+" desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+strSearch+"%");
			rs = pstmt.executeQuery();
			
			int idx = 1;
			while(rs.next()) {
				Vector vo = new Vector();
				vo.add(idx);
				vo.add(rs.getString("classi"));
				vo.add(rs.getString("shop"));
				vo.add(rs.getString("menu"));
				vo.add(rs.getInt("prefer"));
				vo.add(rs.getString("locate"));
				vo.add(rs.getInt("choose"));
				
				vData.add(vo);
				idx++;
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
			System.out.println(sql);
		} finally {
			rsClose();
		}
		return vData;
	}

	// 로그인시 아이디와 비번 확인
	public UserVO LoginChk(String id, char[] pw) {
		try {
			String strPw = "";
			strPw = String.valueOf(pw);
			sql = "select * from eatTUser where id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, strPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				uVo = new UserVO();
				uVo.setId(rs.getString("id"));
				uVo.setName(rs.getString("name"));
				uVo.setBirthDay(rs.getString("birthDay"));
				return uVo;
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return null;
	}


	// 신규 회원 등록
	public void Join(UserVO uVo) {
		try {
			sql = "insert into eatTUser values(default, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVo.getId());
			pstmt.setString(2, uVo.getPassword());
			pstmt.setString(3, uVo.getName());
			pstmt.setString(4, uVo.getBirthDay());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 아이디 중복 확인  ( 같은 아이디 없으면 String "",  있으면 String id 반환)
	public Object IdChk(String id) {
		try {
			sql = "select id from eatTUser where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			UserVO vo = new UserVO();
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				return vo.getId().toString();
			} 
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
		return "";
	}

	// 아이디 찾고 String 으로 아이디 보내줌
	public String FindId(String name, String birthDay) {
		String id = "";
		try {
			sql = "select id from eatTUser where name = ? and birthDay = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthDay);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("id");
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
		return id;
	}

	// 비밀번호 찾고 String 으로 비번 보내줌			// 랜덤으로 임시비번 생성하고 db 변경하면 재밌을듯
	public String FindPw(String id, String name, String birthDay) {
		String pw = "";
		try {
			sql = "select password from eatTUser where id = ? and name = ? and birthDay = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, birthDay);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("password");
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
		return pw;
	}

	// 최다 선택 메뉴 돌려줌 String 
	public String getBestMenu(UserVO uVo) {
		String strbestMenu = "";
		int intbestMenu = 0;
		try {
			sql = "select menu from "+uVo.getId()+" order by choose desc limit 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				intbestMenu = rs.getInt("menu");
			}
			
			sql = "select menu from allmenu where idx = " + intbestMenu;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				strbestMenu = rs.getString("menu");
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
		return strbestMenu;
	}

	// 회원 가입시 테이블 생성
	public void newTable(UserVO uVo) {
		try {
			sql = "create table "+uVo.getId()+"("
					+ "	idx int auto_increment not null, "
					+ "    menu int not null, "
					+ "    prefer int default 5, "
					+ "    choose int default 0, "
					+ "    user_1 boolean default true, "
					+ "    user_2 boolean default true, "
					+ "    foreign key (menu) references allmenu(idx), "
					+ "    primary key (idx, menu),"
					+ "    unique key "+uVo.getId()+" (menu)"
					+ ");";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			
			// 메뉴 테이블 메뉴 수 받아오기
			sql = "select count(*) as num from allmenu";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("num");
			}
			
			for(int i = 1; i <= num; i++) {
				sql = "insert into "+uVo.getId()+"(menu) values("+i+")";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
	}

	public void resetUser(UserVO uVo) {
		try {
			// 계정 삭제
			sql = "delete from eatTUser where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVo.getId());
			pstmt.executeUpdate();
			// 테이블 삭제
			sql = "drop table " + uVo.getId();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
		
	}

	// 비밀번호 변경 
	public void changePassword(UserVO uVo, String pw) {
		try {
			sql = "update eatTUser set password = ? where id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, uVo.getId());
			pstmt.setString(3, uVo.getPassword());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
		
	}

	// 메뉴 데이터 추가	
	public void addMenuData(UserVO uVo, String classi, String shop, String menu, int prefer, String locate) {
		try {
			sql = "insert into AllMenu values(default, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classi);
			pstmt.setString(2, shop);
			pstmt.setString(3, menu);
			pstmt.setString(4, locate);
			pstmt.executeUpdate();
			
			sql = "select count(*) as num from allmenu";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("num");
			}
		
			sql = "insert into "+uVo.getId()+"(menu, prefer) values("+num+", "+prefer+")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
	}

	// 메뉴 선택 회수 증가			
	public void chooseMenu(UserVO uVo, MenuVO mVo) {
		try {
			
			int idx = findMenuIndex(mVo);
			
			// 메뉴의 인덱스 번호 받아와야 함
			sql = "update "+uVo.getId()+" set choose = choose + 1 where menu = "+ idx;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
	}

	// 메뉴 인덱스 번호 찾기
	public int findMenuIndex(MenuVO mVo) {
		int idx = 0;
		sql = "select idx from allmenu where menu = ? and shop = ? and locate = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getMenu());
			pstmt.setString(2, mVo.getShop());
			pstmt.setString(3, mVo.getLocate());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				idx = rs.getInt("idx");
			}
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
		return idx;
	}

	// 로그인 시 개인 테이블 메뉴 갱신
	public void updateMenu(UserVO uVo) {
		// 메뉴 테이블 메뉴 수 받아오기
		sql = "select count(*) as num from allmenu";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("num");
			}
			
			for(int i = 1; i <= num; i++) {
				sql = "insert ignore into "+uVo.getId()+"(menu) values("+i+")";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
		}  catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			rsClose();
		}
	}

	// user 테이블  user_1, _2  true로 변경
	public void addUserTable(UserVO uVo, int idx, String col, int prefer) {
		try {
			sql = "update "+uVo.getId()+" set "+col+" = true where menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
		
	}

	//  사용자 정의 테이블 데이터 삭제 //user 테이블 _1, _2 = false 로 변경
	public void deleteUserTable(UserVO uVo, String col, int idx) {
		try {
			sql = "update "+uVo.getId()+" set "+col+" = false where menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
	}

	// 수정 user 테입블의 prefer 변경
	public void updateUserTable(UserVO uVo, int idx, int prefer) {
		try {
			sql = "update "+uVo.getId()+" set prefer = ? where menu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prefer);
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 에러 : " + e.getMessage() + sql);
		} finally {
			pstmtClose();
		}
	}
}
