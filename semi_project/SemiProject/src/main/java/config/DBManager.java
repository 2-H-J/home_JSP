package config;

// 필요한 import 구문들
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * DBManager 클래스는 데이터베이스 연결 및 MyBatis 세션 관리를 위한 클래스입니다.
 * 싱글톤 패턴을 사용하여 한 번만 인스턴스화되며, DB 연결을 관리합니다.
 */
public class DBManager {

    // 클래스의 유일한 인스턴스를 저장하는 정적 변수
    private static DBManager instance = new DBManager();

    // MyBatis의 SqlSessionFactory를 저장하는 변수
    private SqlSessionFactory sqlSessionFactory;

    /**
     * private 생성자: 외부에서 DBManager 클래스를 직접 인스턴스화하지 못하도록 제한합니다.
     * MyBatis 설정 파일을 로드하고 SqlSessionFactory를 초기화합니다.
     */
    private DBManager() {
        // MyBatis 설정 파일의 경로
        String resource = "config/mybatis-config.xml";
        InputStream inputStream;
        
        try {
            // 설정 파일을 읽기 위한 InputStream을 생성합니다.
            inputStream = Resources.getResourceAsStream(resource);
            
            // 읽어온 설정 파일을 바탕으로 SqlSessionFactory를 빌드합니다.
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            
            // 설정 파일이 정상적으로 로드되었음을 알리는 메시지를 출력합니다.
            System.out.println("DB 셋팅 완료");
        } catch (IOException e) {
            // 설정 파일을 읽는 과정에서 발생한 예외를 처리합니다.
            e.printStackTrace();
        }
    }

    /**
     * 싱글톤 인스턴스를 반환하는 정적 메서드입니다.
     * 외부에서 이 메서드를 호출하여 DBManager 인스턴스를 얻을 수 있습니다.
     * 
     * @return DBManager의 유일한 인스턴스
     */
    public static DBManager getInstance() {
        // 인스턴스가 null인 경우 새로 생성합니다. 하지만 이 경우 이미 초기화되었기 때문에 실행되지 않습니다.
        if (instance == null)
            instance = new DBManager();

        // 유일한 인스턴스를 반환합니다.
        return instance;
    }
    
    // Connection 하나 리턴
    public SqlSession getSession() {
    	// true - auto commit, false - commit
    	return sqlSessionFactory.openSession(false);
    }
}
