package practice.poemtree1.login;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import practice.poemtree1.InfoRepository;
import practice.poemtree1.PoemRepository;
import practice.poemtree1.analysis.InfoAdminister;
import practice.poemtree1.analysis.MemberInfo;
import practice.poemtree1.db.ContentDB;

//@RestController => Controller + @ResponseBody
//@ResponseEntity : 데이터와 상태 코드를 동시에 제어 가능
@Controller
public class LoginController {
    
    Login login;
    InfoAdminister infoadmin;
    ContentDB contentDB;

    @Autowired
    public LoginController(Login login, InfoAdminister infoAdmin, ContentDB contentDB){
        this.login = login;
        this.infoadmin = infoAdmin;
        this.contentDB = contentDB;
    }

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    /*
    @RequestBody Member member -> json format get...
    public @ResponseBody String check ~~
    @PostMapping("/check")
    public ResponseEntity check(Member member){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */
    @PostMapping("/check")
    @ResponseBody
    public String check(Member member){
        if (login.authCheck(member)){
            return "check";
        }else{
            return "faild";
        }
    }

    @GetMapping("/poemlist")
    public String poemlist(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        // Set Poem repository
        PoemRepository PR = PoemRepository.getInstance();
        PR.setPR(contentDB.findAll());
        
        InfoRepository IR = InfoRepository.getInstance();
        IR.setName(name);
        IR.setPid(infoadmin.findPIDByName(name));

        return "redirect:/page?num=0";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @PostMapping("/sign")
    @ResponseBody
    public String signIn(Member member){
        login.memberCreate(member);
        return "created";
    }

    @GetMapping("/memberinfo")
    public String memberInfo(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "memberinfo";
    }

    @PostMapping("/memberinfo")
    @ResponseBody
    public String memberpush(MemberInfo memberInfo){

        String pid = UUID.randomUUID().toString();
        memberInfo.setPid(pid);
        infoadmin.infoResisger(memberInfo);
        return "push";
    }

}
