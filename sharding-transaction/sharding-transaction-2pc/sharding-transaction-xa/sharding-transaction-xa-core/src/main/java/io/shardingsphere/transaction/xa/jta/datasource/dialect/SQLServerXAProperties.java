/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.transaction.xa.jta.datasource.dialect;

import com.google.common.base.Optional;
import io.shardingsphere.core.config.DataSourceParameter;
import io.shardingsphere.core.metadata.datasource.dialect.SQLServerDataSourceMetaData;
import io.shardingsphere.transaction.xa.jta.datasource.XAProperties;

import java.util.Properties;

/**
 * XA properties for SQLServer.
 *
 * @author zhaojun
 */
public final class SQLServerXAProperties implements XAProperties {
    
    @Override
    public Properties build(final DataSourceParameter dataSourceParameter) {
        Properties result = new Properties();
        SQLServerDataSourceMetaData sqlServerMetaData = new SQLServerDataSourceMetaData(dataSourceParameter.getUrl());
        result.setProperty("user", dataSourceParameter.getUsername());
        result.setProperty("password", Optional.fromNullable(dataSourceParameter.getPassword()).or(""));
        result.setProperty("serverName", sqlServerMetaData.getHostName());
        result.setProperty("portNumber", String.valueOf(sqlServerMetaData.getPort()));
        result.setProperty("databaseName", sqlServerMetaData.getSchemeName());
        return result;
    }
}