package id.jagokoding;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { DemoCircularDI.BeanA.class,
				DemoCircularDI.BeanB.class }) })
public class DemoCircularDI {

	public static void main(String[] strings) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoCircularDI.class);

	}

	@Component
	static class BeanA {

		private BeanB beanB;

		public BeanA(BeanB beanB) {
			this.beanB = beanB;
		}
		
		public void myMethod() {
			System.out.println("call method myMethod()");
		}
	}

	@Component
	static class BeanB {

		private BeanA beanA;

		public BeanB(BeanA beanA) {
			this.beanA = beanA;
		}
	}
}