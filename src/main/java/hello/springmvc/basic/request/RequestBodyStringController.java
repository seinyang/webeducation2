package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream,
                StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }


    @PostMapping("/request-body-string-v3")
    //바디를 반환해줌 httpentity란 헤더와 바디를 조회
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        //변환된 바디를 꺼내는것
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        //바디의 메시지도 반환해줌
        return new HttpEntity<>("ok");
    }

    @ResponseBody//응답 해주는거는 리스폰스 바디 해당메시지의 바디의 값을 반환
    @PostMapping("/request-body-string-v4")

    //메시지 바디ㄴ를 직접 조회하거나 기능은 요청파라미터를 조회하는 리퀘스트 파람,모델 어트리부트
    //응답오는건 리퀘스트 바디정보를 조회해준다.http메시지 바디정보를 긁어오는건? 리퀘스트 바디
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
