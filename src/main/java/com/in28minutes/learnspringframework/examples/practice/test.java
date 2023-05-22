package com.in28minutes.learnspringframework.examples.practice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class test {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
                test.class)) {

            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }
    }
}

/*
1 객체를 일일이 생성하고 관리하기 귀찮으니까 이를 스프링 컨테이너에게 위임함
2 스프링 컨테이너는 객체들을 생성하고 의존성을 주입해 주는 역할을 함

그럼 스프링 컨테이너가 객체들을 어떻게 생성하는가?
1 일단 클래스는 평소 하던 것처럼 작성함
2 느슨한 결합을 위해 인터페이스를 구현하도록 함
  (클래스 간의 결합이 아니라 인터페이스를 사이에 둔 결합이 되어야 함
   : 서로가 서로를 몰라도 됨, 그냥 인터페이스만 알면 됨)
3 스프링 컨테이너가 관리할 객체를 알 수 있도록 @Component 어노테이션을 클래스 앞에 작성
  그러면 스프링 컨테이너는 이 어노테이션이 붙은 클래스의 객체를 생성한 후 컨테이너에 저장
  
객체 간의 의존성 주입
1 의존성이란? 객체 사이의 의존 관계
  객체들이 직접적으로 연결되어 버리면 강한 결합이 되어서 좋지 않음
  그래서 말그대로 의존성을 '주입'하는 것임
2 의존성을 주입하는 방법은 세 가지가 있음
  - 필드 주입: 필드의 앞에 @Autowired를 붙임
  - setter 주입: setter 메서드 생성과 @Autowired
  - 생성자 주입: 가장 권장되는 방법으로 여러 의존성을 한 번에 초기화 가능

설정 파일
1 스프링 컨테이너를 생성하는 데 있어 가장 기본이 되는 파일
2 빈을 수동으로 생성하는 경우에는 Config 파일을 따로 두고 일일이 @Bean을 붙여 가면서 컨테이너를 구성하는데
3 빈을 컨테이너가 생성하도록 하면, 그냥 애플리케이션 파일을 설정 파일로 쓸 수 있음
4 그러므로 애플리케이션 파일에서 하는 일은 컨테이너를 생성하고, 빈들을 요청하는 것밖에 없음
5 대신 이 앞에 @Configuration과 @ComponentScan을 붙여주어야 함

즉, AnnotationConfigApplicationContext(애플리케이션 파일.class)가 실행이 되는 순간,
ComponentScan에 적혀 있는 패키지 내의 Component들을 찾아서 알아서 객체를 생성하고
의존성을 주입해 주는 것임

그러면 main 내에서는 더 이상 new를 남발하는 것 없이 객체만 깔끔하게 가져오면 됨

의존성 주입의 우선순위
예를 들어 어떤 클래스에 Car 객체 의존성을 주입해야 하는데, Car를 상속한 클래스가 여러 개다!
이때 우선순위를 정해 주지 않으면 오류가 발생할 수 있음
1 @Primary: 가장 우선이 됨
2 @Qualifier: 이름 지정(?), Primary보다 우선
 */
