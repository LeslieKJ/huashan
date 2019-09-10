package LuoJKL.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import LuoJKL.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import LuoJKL.entity.User;
import LuoJKL.service.UserService;


@Controller
@RequestMapping("/User")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		//��ȡ��ǰ��¼���û�
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),CryptographyUtil.md5(user.getPassWord(),"java1234"));
		try{
			//���õ���MyRealm�е�doGetAuthenticationInfo����
			//��¼��֤
			subject.login(token);
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user",user);
			request.setAttribute("errorInfo","�û������������");
			return "login";
		}
		
	}

	
	
		
	
}
