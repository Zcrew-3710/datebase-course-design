package com.zufang.renthouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 只配置一次 Mapper 扫描（覆盖所有mapper包即可，不要重复写）
@MapperScan("com.zufang.renthouse.mapper")
public class ZufangApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZufangApplication.class, args);
    }
}