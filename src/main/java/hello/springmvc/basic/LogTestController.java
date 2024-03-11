package hello.springmvc.basic;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//레ㄱ스트 컨트롤러는 view로 반환안하고 http메시지 바디에 반환값을 바로 입력함
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());


    @RequestMapping("/log-test")
    public String Logtest(){
        String name= "Spring";


        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);

        return "ok";
    }
}
