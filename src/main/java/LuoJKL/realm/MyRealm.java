package LuoJKL.realm;

import javax.annotation.Resource;

import LuoJKL.entity.User;
import LuoJKL.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * �Զ���Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	
	/**
	 * Ϊ��ǰ�ĵ�¼���û���ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * ��֤��ǰ��¼���û�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String) token.getPrincipal();
		User user=userService.getByUserName(userName);
		if(user!=null){
		    // �ѵ�ǰ�û���Ϣ�浽session�У���shiro�ṩ�ķ��������ַ�����ȱ�����˷��ڴ�
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(), "xxx");
			return authcInfo;
		}else{
			return null;			
		}
	}

}
