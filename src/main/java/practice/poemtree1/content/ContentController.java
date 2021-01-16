package practice.poemtree1.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import practice.poemtree1.InfoRepository;
import practice.poemtree1.PoemRepository;
import practice.poemtree1.analysis.InfoAdminister;
import practice.poemtree1.db.ContentDB;

@Controller
public class ContentController {
    
    InfoAdminister infoAdmin;
    ContentDB contentDB;

    @Autowired
    public ContentController(InfoAdminister infoAdmin,ContentDB contentDB){
        this.infoAdmin = infoAdmin;
        this.contentDB = contentDB;
    }

    @GetMapping("/len")
    @ResponseBody
    public Integer returnLength(){
        return PoemRepository.getInstance().getIen();
    }

    @GetMapping("/page")
    public String page(@RequestParam("num") String num, Model model){
        
        List<Content> PR = PoemRepository.getInstance().getPR();
        String name = InfoRepository.getInstance().getName();
        int n = Integer.parseInt(num);
        model.addAttribute("name", name);
        model.addAttribute("author1", PR.get(n).getPid() );
        model.addAttribute("title1", PR.get(n).getTitle() );
        return "poemlist";
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @GetMapping("/mylist")
    @ResponseBody
    public List<Content> mylist(){
        String pid = InfoRepository.getInstance().getPid();
        List<Content> contents = contentDB.findByPID(pid);
        return contents;
    }

    @PostMapping("/create")
    public String createContent(Content content){
        String pid = InfoRepository.getInstance().getPid();
        content.setPid(pid);
        contentDB.insertRow(content);

        return "redirect:write";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(){
        InfoRepository.getInstance().clear();
        PoemRepository.getInstance().clear();
        return "/";
    }

    @GetMapping("/loadPoem")
    @ResponseBody
    public Map<String, String> load(@RequestParam("pnum")String pnum){

        Map<String, String> data = new HashMap<String, String>();

        int ipnum = Integer.parseInt(pnum);
        Optional<Content> content = contentDB.findByPnum(ipnum);

        data.put("title", content.get().getTitle());
        data.put("poem", content.get().getPoem());

        return data;
        
    }


}
