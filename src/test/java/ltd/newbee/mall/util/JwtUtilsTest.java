package ltd.newbee.mall.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class JwtUtilsTest {
    @Test
    public void generateJwtTest(){
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",12);
        claims.put("name","hhhh");

        String token = JwtUtils.generateJwt(claims);
        
        log.info("Test toekn ={}",token);

        Claims claims1 = JwtUtils.parseJWT(token);
        Integer id = (Integer)claims1.get("id");

        log.info("after test the id={}",id);

    }
}
