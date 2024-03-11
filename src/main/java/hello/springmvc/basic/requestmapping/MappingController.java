/*
package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//메서드의 설정이 없다면 get,post,put,delete이든 모든게 ok
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        logger.info("hellobasic");
        return "ok";
    }


    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        logger.info("mapping-get-v2");
        return "ok";
    }


    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        logger.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

//컨숨은 컨텐트 타입이 꼭있어야함
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        logger.info("mappingConsumes");
        return "ok";
    }
    //컨숨은 컨텐트 타입이 꼭있어야함(미디어타입쩜 하고 들어가면 json형식도 있고 html형식도 있음)
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume() {
        logger.info("mappingConsumes");
        return "ok";
    }

}
*/
