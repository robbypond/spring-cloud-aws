/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticspring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 *
 *
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceFactoryBeanAwsTest {

	@Autowired
	private DataSource dataSource;

	@Test
	@IfProfileValue(name = "test-groups", value = "aws-test")
	public void testExistingDataSourceInstance() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		String value = Long.toString(System.currentTimeMillis());
		jdbcTemplate.update("insert into data(data) values(?)", value);
	}
}
