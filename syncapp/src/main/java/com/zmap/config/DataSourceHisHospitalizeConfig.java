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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//@Configuration
//@MapperScan(basePackages = DataSourceHisHospitalizeConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactoryHisHospitalize")
public class DataSourceHisHospitalizeConfig {
	/*static final String PACKAGE = "com.zmap.his.hospitalize";
	
	@Bean(name = "dataSourceHisHospitalize")
	@Qualifier("dataSourceHisHospitalize")
	@ConfigurationProperties(prefix = "spring.datasource.his.hospitalize")
	public DataSource dataSourceHisHospitalize() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "transactionManagerHisHospitalize")
	public DataSourceTransactionManager transactionManagerHisHospitalize(@Qualifier("dataSourceHisHospitalize") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
		
	}
	
	@Bean(name = "sqlSessionFactoryHisHospitalize")
	public SqlSessionFactory sqlSessionFactoryHisHospitalize(@Qualifier("dataSourceHisHospitalize") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
		return sqlSessionFactoryBean.getObject();
	}*/
}
