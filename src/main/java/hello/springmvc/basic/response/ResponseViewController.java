package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }
//@ResponseBody 뷰로 안가고 리턴한 http응답 메시지로 스트링 자체로 나가버림!,명시적으로 나와있는 이방법 추첮ㄴ

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","helllo sein");

        return "response/hello";
    }
//이렇게 void로 바꾸고 리턴ㅇ,ㄹ 굳이 안해줘도 매핑한거랑 html 파일로 가줌,void는 잘쓰지않음
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data","helllo sein void");

    }
}
