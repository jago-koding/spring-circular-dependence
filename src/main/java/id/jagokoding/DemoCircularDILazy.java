package id.jagokoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import id.jagokoding.DemoCircularDILazy.BeanA;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { DemoCircularDILazy.BeanA.class,
				DemoCircularDILazy.BeanB.class }) })
public class DemoCircularDILazy {

	public static void main(String[] strings) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoCircularDILazy.class);
		BeanA beanA = context.getBean(BeanA.class);
		beanA.myMethod();
	}

	@Component
	static class BeanA {

		private BeanB beanB;

		public BeanA(@Lazy BeanB beanB) {
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