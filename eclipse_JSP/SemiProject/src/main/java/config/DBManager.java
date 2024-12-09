package config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * DBManager 클래스는 데이터베이스 연결과 MyBatis SqlSessionFactory 관리를 담당합니다.
 * 싱글톤 패턴을 사용하여 애플리케이션 전역에서 하나의 인스턴스를 공유합니다.
 */
public class DBManager {
    // DBManager 클래스의 유일한 인스턴스
    private static DBManager instance = new DBManager();
    // MyBatis SqlSessionFactory 객체
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 기본 생성자 - MyBatis 설정 파일을 읽어 SqlSessionFactory를 초기화합니다.
     */
    private DBManager() {
        String resource = "config/mybatis-config.xml"; // MyBatis 설정 파일 경로
        InputStream inputStream;
        try {
            System.out.println("[DBManager] MyBatis 설정 파일 로드 시작 -> 경로: " + resource);
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("[DBManager] SqlSessionFactory 초기화 완료 -> DB 셋팅 완료");
        } catch (IOException e) {
            System.out.println("[DBManager] MyBatis 설정 파일 로드 실패");
            e.printStackTrace();
        }
    }

    /**
     * DBManager 인스턴스를 반환합니다. 싱글톤 패턴으로 하나의 인스턴스만 존재합니다.
     * 
     * @return DBManager 인스턴스
     */
    public static DBManager getInstance() {
        if (instance == null) {
            System.out.println("[DBManager] 새로운 DBManager 인스턴스 생성");
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * SqlSession 객체를 반환합니다.
     * SqlSession은 데이터베이스 작업을 수행하는 데 사용됩니다.
     * 
     * @return SqlSession 객체 (auto-commit 설정: true)
     */
    public SqlSession getSession() {
        System.out.println("[DBManager] SqlSession 생성 -> Auto-commit: true");
        return sqlSessionFactory.openSession(true); // true: auto-commit 활성화
    }
}
