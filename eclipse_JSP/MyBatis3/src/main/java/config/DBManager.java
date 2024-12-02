package config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBManager {
    // 1. 싱글톤 인스턴스를 위한 정적 변수 선언 (DBManager 클래스의 단일 인스턴스 보장)
    // DBManager 클래스의 단 하나의 인스턴스를 유지하기 위한 정적 필드입니다.
    // 이 필드를 통해 애플리케이션 전역에서 동일한 DBManager 인스턴스를 공유합니다.
    private static DBManager instance = new DBManager();
    
    // 2. SqlSessionFactory 변수 선언 - SqlSession을 생성하기 위한 공장 역할
    // SqlSessionFactory는 MyBatis에서 데이터베이스와의 세션을 생성하기 위한 역할을 합니다.
    // SqlSession 객체는 데이터베이스와의 상호작용을 담당하며, SqlSessionFactory를 통해 생성됩니다.
    private SqlSessionFactory sqlSessionFactory;

    // 3. private 생성자 - 외부에서의 인스턴스 생성을 막기 위함
    // DBManager 클래스의 생성자를 private으로 설정하여 외부에서 직접 객체를 생성하지 못하도록 합니다.
    // 이는 싱글톤 패턴을 구현하기 위한 핵심 요소로, 단 하나의 인스턴스만 생성되도록 보장합니다.
    private DBManager() {
        // MyBatis 설정 파일 경로 지정
        String resource = "config/mybatis-config.xml";
        InputStream inputStream;
        try {
            // 4. MyBatis 설정 파일을 읽어들임 (InputStream 객체 생성)
            // Resources.getResourceAsStream() 메서드를 사용하여 MyBatis 설정 파일을 InputStream으로 가져옵니다.
            // 이 설정 파일에는 데이터베이스 연결 정보 및 MyBatis 환경 설정이 정의되어 있습니다.
            inputStream = Resources.getResourceAsStream(resource);
            
            // 5. SqlSessionFactoryBuilder를 사용하여 SqlSessionFactory 생성
            // SqlSessionFactoryBuilder는 설정 파일을 기반으로 SqlSessionFactory 객체를 빌드하는 역할을 합니다.
            // SqlSessionFactory는 SqlSession을 생성하는 공장 역할을 하며, 데이터베이스와의 연결을 설정합니다.
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            
            // 6. 설정 완료 메시지 출력
            // 데이터베이스 설정이 완료되었음을 알리기 위해 콘솔에 메시지를 출력합니다.
            System.out.println("DB 셋팅 완료");
        } catch (IOException e) {
            // 예외 처리 - 설정 파일을 읽는 도중 문제가 발생한 경우
            // 설정 파일을 읽는 과정에서 문제가 발생하면 예외 정보를 출력합니다.
            // 이는 설정 파일 경로가 잘못되었거나 파일을 읽는 중 문제가 발생했을 때 발생할 수 있습니다.
            e.printStackTrace();
        }
    }

    // 7. 싱글톤 인스턴스를 반환하는 메서드
    // 외부에서 이 클래스를 호출할 때 항상 동일한 인스턴스를 반환하도록 보장
    // 이 메서드는 DBManager 인스턴스를 전역적으로 접근할 수 있는 정적 메서드입니다.
    // 인스턴스가 null인 경우에만 새로운 인스턴스를 생성하여 반환합니다.
    public static DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    // 8. SqlSession 객체를 반환하는 메서드
    // SqlSession은 MyBatis에서 데이터베이스와의 상호작용을 위해 사용됨
    // SqlSession 객체를 반환하여 쿼리 실행, 데이터 삽입/수정/삭제 등의 작업을 수행합니다.
    // true - 자동 커밋 설정, 즉 데이터베이스 작업이 성공적으로 완료되면 자동으로 커밋됨
    // 자동 커밋 설정을 통해 쿼리 실행 후 즉시 데이터베이스에 반영되도록 설정합니다.
    public SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }
}
