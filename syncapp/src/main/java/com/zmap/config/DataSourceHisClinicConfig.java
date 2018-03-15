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

//@Configuration
//@MapperScan(basePackages = DataSourceHisClinicConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactoryHisClinic")
public class DataSourceHisClinicConfig {

	/*static final String PACKAGE = "com.zmap.his.clinic";
	// in muliple data sources situation, system must set which one is primary
	@Bean(name = "dataSourceHisClinic")
	@Qualifier("dataSourceHisClinic")
	@ConfigurationProperties(prefix = "spring.datasource.his.clinic")
	public DataSource dataSourceHisClinic() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "transactionManagerHisClinic")
	public DataSourceTransactionManager transactionManagerHisClinic(@Qualifier("dataSourceHisClinic") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
		
	}
	
	@Bean(name = "sqlSessionFactoryHisClinic")
	public SqlSessionFactory sqlSessionFactoryHisClinic(@Qualifier("dataSourceHisClinic") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
		return sqlSessionFactoryBean.getObject();
	}*/
}
