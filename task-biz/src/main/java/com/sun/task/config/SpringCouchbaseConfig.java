/**  
 * @(#)MulberrySpringCouchbaseConfig.java     1.0 2013/11/17      
 *      
 * Copyright (c) 2010 SHANGHAI BINGKUN DIGITAL TECHNOLOGY CO.,LTD       
 * All rights reserved      
 *      
 * This software is the confidential and proprietary information of         
 * SHANGHAI BINGKUN.("Confidential Information").  You shall not        
 * disclose such Confidential Information and shall use it only in      
 * accordance with the terms of the license agreement you entered into      
 * with SHANGHAI BINGKUN.       
 */
package com.sun.task.config;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.CouchbaseConnectionFactoryBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CustomConversions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Configuration
@EnableCaching
public class SpringCouchbaseConfig extends AbstractCouchbaseConfiguration {
	
	  @Bean(name="couchbaseClient")
	  public CouchbaseClient couchbaseClient() throws Exception {
	    //return super.couchbaseClient();
		CouchbaseConnectionFactoryBuilder ccfb = new CouchbaseConnectionFactoryBuilder();
		//ccfb.setProtocol(Protocol.BINARY);
        ccfb.setOpTimeout(10000); // wait up to 10 seconds for an operation to
        // succeed
        ccfb.setOpQueueMaxBlockTime(5000); // wait up to 5 seconds when trying
        // to enqueue an operation
        ccfb.setMaxReconnectDelay(1500);

	    return new CouchbaseClient(ccfb.buildCouchbaseConnection(bootstrapUris(bootstrapHosts()), getBucketName(), getBucketPassword()));
	  }

	  @Bean
	  public CouchbaseCacheManager cacheManager() throws Exception {
	    HashMap<String, CouchbaseClient> instances = new HashMap<String, CouchbaseClient>();
	    instances.put("persistent", couchbaseClient());
	    return new CouchbaseCacheManager(instances);
	  }

	@Override
	protected List<String> bootstrapHosts() {
		String couchBaseUrl = "192.168.102.209";
		return Arrays.asList(couchBaseUrl);
	}

	@Override
	protected String getBucketName() {
		return "pekon";
	}

	@Override
	protected String getBucketPassword() {
		String password = "Webon1234";
		return password;
	}


}