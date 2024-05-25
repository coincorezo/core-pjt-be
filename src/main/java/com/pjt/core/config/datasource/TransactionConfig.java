package com.pjt.core.config.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class TransactionConfig {

	private static final String AOP_TX_EXPRESSION = "execution(* com.pjt.core..*Service.*(..))";

	private final PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor transactionAdvice() {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		Properties transactionProperties = new Properties();

		// read
		DefaultTransactionAttribute readAttribute = new DefaultTransactionAttribute();
		readAttribute.setReadOnly(true);
		transactionProperties.setProperty("select*", readAttribute.toString());
		transactionProperties.setProperty("get*", readAttribute.toString());
		transactionProperties.setProperty("find*", readAttribute.toString());

		// write
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));

		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		transactionProperties.setProperty("insert*", writeAttribute.toString());
		transactionProperties.setProperty("update*", writeAttribute.toString());
		transactionProperties.setProperty("delete*", writeAttribute.toString());
		transactionProperties.setProperty("save*", writeAttribute.toString());

		transactionInterceptor.setTransactionManager(transactionManager);
		transactionInterceptor.setTransactionAttributes(transactionProperties);

		return transactionInterceptor;
	}

	@Bean
	public Advisor transactionAdvisor() {
		AspectJExpressionPointcut advisor = new AspectJExpressionPointcut();
		advisor.setExpression(AOP_TX_EXPRESSION);

		return new DefaultPointcutAdvisor(advisor, transactionAdvice());
	}

}
