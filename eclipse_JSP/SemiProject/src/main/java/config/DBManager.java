package config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * DBManager 클래스
 * - 데이터베이스 연결 관리를 위한 싱글톤 클래스
 * - MyBatis를 사용하여 SQLSessionFactory를 초기화하고, 데이터베이스 세션(SqlSession)을 제공
 */
public class DBManager {
    // DBManager 싱글톤 인스턴스
    private static DBManager instance = new DBManager();
    private SqlSessionFactory sqlSessionFactory; // MyBatis SqlSessionFactory 객체

    /**
     * private 생성자
     * - 클래스 외부에서 인스턴스 생성을 방지
     * - MyBatis 설정 파일을 로드하고 SqlSessionFactory를 초기화
     */
    private DBManager() {
        String resource = "config/mybatis-config.xml"; // MyBatis 설정 파일 경로
        InputStream inputStream;
        try {
            // 설정 파일을 읽어들여 InputStream 생성
            inputStream = Resources.getResourceAsStream(resource);
            // SqlSessionFactory 객체를 초기화
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("DB 셋팅 완료");
        } catch (IOException e) {
            // 설정 파일 로드 오류 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }

    /**
     * 싱글톤 인스턴스 반환 메서드
     * - DBManager의 유일한 인스턴스를 반환
     * 
     * @return DBManager 인스턴스
     */
    public static DBManager getInstance() {
        // 만약 instance가 null이면 새로운 DBManager 인스턴스를 생성 (필요 시)
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    /**
     * SqlSession 반환 메서드
     * - 데이터베이스와 통신하기 위한 SqlSession 객체를 반환
     * 
     * @return SqlSession 객체 (auto-commit 설정: true)
     */
    public SqlSession getSession() {
        // true: 자동 커밋 모드로 SqlSession 객체 생성
        return sqlSessionFactory.openSession(true);
    }
}
