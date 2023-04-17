package com.huh.BaekJoonSupporter.line;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class LineServiceTest {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineService lineService;


    @Test
    @DisplayName("라인 생성 테스트")
    void lineTest1() {

        for (int i = 0; i < 5; i++) {
            lineService.create("token" + i, null);
        }

        Assertions.assertThat(lineRepository.findAll().size()).isEqualTo(5);
    }
}