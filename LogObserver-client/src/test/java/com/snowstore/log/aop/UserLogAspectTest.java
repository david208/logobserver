package com.snowstore.log.aop;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;



@SuppressWarnings("unused")
@RunWith(PowerMockRunner.class)
public class UserLogAspectTest {

	@InjectMocks
	UserLogAspect userLogAspect;

/*	@Mock
	private UserLogService userLogService;
	@Mock
	private UserDetailService userDetailService;*/

	@Test
	public void testUserLog() {
		userLogAspect.userLog();
	}

	/*@Test
	public void testLogUserOperate() throws Throwable {
		String name = "123";
		String[] o = { "1", "2" };
		ProceedingJoinPoint jp = mock(ProceedingJoinPoint.class);
		MethodSignature signature = mock(MethodSignature.class);
		User user = new User();
		Method method = PowerMockito.mock(Method.class);

		when(jp.getSignature()).thenReturn(signature);
		when(jp.proceed()).thenReturn(name);
		when(jp.getArgs()).thenReturn(o);
		when(signature.getParameterNames()).thenReturn(o);
		when(signature.getMethod()).thenReturn(method);
		when(userDetailService.getUser()).thenReturn(user);
		Object act = userLogAspect.logUserOperate(jp);
		Assert.assertEquals(name, act);
		verify(userDetailService, times(0)).getUser();
		// verify(userLogService, times(1)).saveUserLog(Mockito.anyLong(),
		// Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}

	@Test(expected = RuntimeException.class)
	public void testLogUserOperate_Error() throws Throwable {
		String name = "123";
		String[] o = { "1", "2" };
		ProceedingJoinPoint jp = mock(ProceedingJoinPoint.class);
		MethodSignature signature = mock(MethodSignature.class);
		User user = new User();
		Method method = PowerMockito.mock(Method.class);

		when(jp.getSignature()).thenReturn(signature);
		when(jp.proceed()).thenThrow(new RuntimeException());
		when(jp.getArgs()).thenReturn(o);
		when(signature.getParameterNames()).thenReturn(o);
		when(signature.getMethod()).thenReturn(method);
		when(userDetailService.getUser()).thenReturn(user);
		Object act = userLogAspect.logUserOperate(jp);
		Assert.assertEquals(name, act);
		verify(userDetailService, times(1)).getUser();
		// verify(userLogService, times(1)).saveUserLog(Mockito.anyLong(),
		// Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}*/

}
