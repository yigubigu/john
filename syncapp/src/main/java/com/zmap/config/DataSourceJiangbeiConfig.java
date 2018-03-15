package com.zmap.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = DataSourceJiangbeiConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactoryJiangbei")
public class DataSourceJiangbeiConfig {
	static final String PACKAGE = "com.zmap.his";
	// in muliple data sources situation, system must set which one is primary
	@Primary
	@Bean(name = "dataSourceJiangbei")
	@Qualifier("dataSourceJiangbei")
	@ConfigurationProperties(prefix = "spring.datasource.his.jiangbei")
	public DataSource dataSourceJiangbei() {
		return DataSourceBuilder.create().build();
	}
	@Primary
	@Bean(name = "transactionManagerJiangbei")
	public DataSourceTransactionManager transactionManagerHisClinic(@Qualifier("dataSourceJiangbei") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
		
	}
	@Primary
	@Bean(name = "sqlSessionFactoryJiangbei")
	public SqlSessionFactory sqlSessionFactoryHisClinic(@Qualifier("dataSourceJiangbei") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
		return sqlSessionFactoryBean.getObject();
	}
}
