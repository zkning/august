package com.august.thymelef.web;

import com.alibaba.fastjson.JSON;
import com.august.thymelef.domain.MenuDO;
import com.august.thymelef.dto.R;
import com.august.thymelef.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping({"/index"})
    String index(Model model) {
        List<MenuDO> fl = new ArrayList<>();
        MenuDO f = new MenuDO();
        fl.add(f);
        f.setName("一级");
        f.setUrl("http://www.baidu.com");
        f.setIcon("layui-icon layui-icon-component");

        // 二级
        List<MenuDO> sl = new ArrayList<>();
        f.setChild(sl);
        MenuDO sm = new MenuDO();
        sl.add(sm);
        sm.setName("二级");
        sm.setUrl("http://www.baidu.com");
        sm.setIcon("");

        // 三级
        List<MenuDO> tl = new ArrayList<>();
        sm.setChild(tl);
        MenuDO tm = new MenuDO();
        tl.add(tm);
        tm.setName("三级");
        tm.setUrl("http://www.baidu.com");
        tm.setIcon("");

        model.addAttribute("menus", fl);
        model.addAttribute("name", "张三");
        log.info(JSON.toJSONString(model));
        return "user/index";
    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
    }

    @GetMapping("/login")
    String login(Model model) {
//        model.addAttribute("username", bootdoConfig.getUsername());
//        model.addAttribute("password", bootdoConfig.getPassword());
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password, String verify, HttpServletRequest request) {

//        try {
        //从session中获取随机数
//            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
//            if (StringUtils.isBlank(verify)) {
//                return R.error("请输入验证码");
//            }
//            if (random.equals(verify)) {
//            } else {
//                return R.error("请输入正确的验证码");
//            }
//        } catch (Exception e) {
//            logger.error("验证码校验失败", e);
//            return R.error("验证码校验失败");
//        }
//        password = MD5Utils.encrypt(username, password);
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(token);
        return R.ok();
    }

    @GetMapping("/logout")
    String logout() {
//        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "user/main";
    }

    @GetMapping("/info")
    String info() {
        return "set/user/info";
    }

    @GetMapping("/password")
    String password() {
        return "set/user/password";
    }

    @GetMapping("/search")
    String search() {
        return "template/search";
    }

    @GetMapping("/message")
    String message() {
        return "app/message/index";
    }

    @GetMapping("/ex")
    void ex() {
       throw new RuntimeException("ex...");
    }
}
