package com.estsoft.db;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLProjectDBConnection implements DBConnection {
// mysql이 아닌 오라클같은 다른 디비써서 드라이버 로드 또는 연결 할때 그 경로나 내용을 오버라이드 해서 바꾸면 더 편하게 쓸수있음.
	//DBConnection을 상속받으니까 오버라이드만 해서 내용을 사용할 디비에 맞게 쓰면 된다.
	@Override
	public Connection getConnection() throws SQLException {
		return null;
	}

}
