package com.snowstore.log.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.snowstore.log.annotation.UserLog;
import com.snowstore.log.service.UserDetailDelegate;
import com.snowstore.log.service.UserLogObservable;
import com.snowstore.log.vo.UserInfo;

@Aspect
public class UserLogAspect {

	
	private UserLogObservable userLogObservable;

	
	private UserDetailDelegate<UserDetails> userDetailDelegate;

	public UserDetailDelegate<UserDetails> getUserDetailDelegate() {
		return userDetailDelegate;
	}

	public void setUserDetailDelegate(UserDetailDelegate<UserDetails> userDetailDelegate) {
		this.userDetailDelegate = userDetailDelegate;
	}

	public UserLogObservable getUserLogObservable() {
		return userLogObservable;
	}

	public void setUserLogObservable(UserLogObservable userLogObservable) {
		this.userLogObservable = userLogObservable;
	}

	/**
	 * 
	 * @description 切点
	 * @author sm
	 */
	@Pointcut("@annotation(com.snowstore.log.annotation.UserLog)")
	public void userLog() {

	}

	/**
	 * 
	 * @description 用户操作日志注解，方法设置后，围绕方法会记录结果
	 * @param jp
	 *            切面内容
	 * @author sm
	 * @throws Throwable
	 */
	@Around("userLog()")
	public Object logUserOperate(ProceedingJoinPoint jp) throws Throwable {
		Object result = "";
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			result = e;
			throw e;
		} finally {
			String remark = getRemark(jp);
			String args = getArgs(jp);

			if (null != remark && !remark.isEmpty()) {
				UserInfo userInfo = userDetailDelegate.getUserInfo();
				if (null != userInfo)
					userLogObservable.notifyObserver(userInfo, remark, String.valueOf(result), args, new Date());
			}

		}
		return result;

	}

	/**
	 * 
	 * @description 获取备注
	 * @param jp
	 *            切面内容
	 * @return 备注
	 * @author sm
	 */
	public String getRemark(JoinPoint jp) {
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();
		UserLog userLog = method.getAnnotation(UserLog.class);
		if (userLog != null) {
			return userLog.remark();
		}
		return "";
	}

	/**
	 * 
	 * @description 获取参数
	 * @param jp
	 *            切面内容
	 * @return 参数列表
	 * @author sm
	 */
	public String getArgs(JoinPoint jp) {
		Object[] argValues = jp.getArgs();
		MethodSignature signature = (MethodSignature) jp.getSignature();
		String[] argNames = signature.getParameterNames();
		StringBuilder args = new StringBuilder();
		for (int i = 0; i < argValues.length; i++) {
			if (null == argValues[i] || argValues[i] instanceof Model || argValues[i] instanceof ModelMap || argValues[i] instanceof ServletRequest || argValues[i] instanceof ServletResponse)
				continue;
			if (argValues[i] instanceof Object[])
				args.append(argNames[i] + ":" + Arrays.toString((Object[]) argValues[i]).toString());
			else
				args.append(argNames[i] + ":" + (argValues[i]).toString());
			if (i < argValues.length - 1)
				args.append(",");
		}
		return args.toString();

	}
}
