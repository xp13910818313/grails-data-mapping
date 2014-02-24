/* Copyright (C) 2010 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.datastore.gorm.neo4j

import groovy.transform.CompileStatic
import org.grails.datastore.gorm.neo4j.engine.CypherEngine
import org.grails.datastore.mapping.core.AbstractDatastore
import org.grails.datastore.mapping.core.Session
import org.grails.datastore.mapping.model.MappingContext
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext

/**
 * Datastore implementation for Neo4j backend
 * @author Stefan Armbruster <stefan@armbruster-it.de>
 * TODO: refactor constructors to be groovier
 */
@CompileStatic
class Neo4jDatastore extends AbstractDatastore implements InitializingBean {

    MappingContext mappingContext
    CypherEngine cypherEngine

    public Neo4jDatastore(MappingContext mappingContext, ApplicationContext applicationContext, CypherEngine cypherEngine) {
        this.mappingContext = mappingContext
        this.applicationContext = applicationContext as ConfigurableApplicationContext
        this.cypherEngine = cypherEngine
    }

    @Override
    protected Session createSession(Map<String, String> connectionDetails) {
        new Neo4jSession(this, mappingContext, applicationContext, false, cypherEngine)
    }

    @Override
    void afterPropertiesSet() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}