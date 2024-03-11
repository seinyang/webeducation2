package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RequestBodyJsonController {
//json을 읽어ㅏ오려면 오브젝트 매퍼 있어야함

    private ObjectMapper objectMapper;

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    //서블릿을쓰지않고도 http메시지바디에 있는값을 json형식으로 반환할수있다.
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        //
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }


    /**
     * @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type:
    application/json)
     *
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    //직접 만든 객체를 지정할수있음 리퀘스트 바디에
    //http메시지 컨버터가 json형식으로 바꿔ㅓ주는 역할을 하는것임
    //HelloData data = objectMapper.readValue(messageBody, HelloData.class);
    public String requestBodyJsonV3(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    //http에서 body데이터를 꺼내는 방법으로 쓸수도 있다/
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }


/*    @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
 HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type:
            application/json)
 @ResponseBody 적용
 메시지 바디 정보 직접 반환(view 조회X)
  HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용(Accept:
            application/json)*/
//http컨버터를 통해 json형식으로 반환되어버림,그러면 html응답도 json형식으로 반환됨
    //그니까 json이 객체가 됫다가 반환될때 다시 json이 되서 반환되는거임
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
    }


