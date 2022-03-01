package io.springbatch.springbatchlecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchLectureApplication {

    //M
    // 1. CLI로 파라미터 전달 실행
    // java -jar spring-batch-lecture-0.0.1-SNAPSHOT.jar name=user seq(long)=2L date(date)=2021/01/01 age(double)=16.5
    // 2. 스프링 부트 실행시 - Program arguements에 "name=user seq(long)=2L date(date)=2021/01/01 age(double)=16.5"
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchLectureApplication.class, args);
    }

}
