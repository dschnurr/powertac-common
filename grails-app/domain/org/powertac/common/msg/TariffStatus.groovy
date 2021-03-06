/*
 * Copyright 2009-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.powertac.common.msg

import org.powertac.common.Broker
import org.powertac.common.transformer.BrokerConverter
import org.powertac.common.IdGenerator
import com.thoughtworks.xstream.annotations.*

/**
 * Represents a response from server to broker to publication or update
 * of a tariff.
 * @author jcollins
 */
@XStreamAlias("tariff-status")
class TariffStatus //implements Serializable
{
  enum Status {success, noSuchTariff, noSuchUpdate, illegalOperation,
               invalidTariff, invalidUpdate}

  @XStreamAsAttribute
  String id = IdGenerator.createId()

  @XStreamConverter(BrokerConverter)
  Broker broker

  @XStreamAsAttribute
  String tariffId
  
  @XStreamAsAttribute
  String updateId
  
  String message
  
  @XStreamAsAttribute
  Status status = Status.success

  static constraints = {
    id(nullable: false, blank: false, unique: true)
    broker(nullable: false)
    tariffId(nullable: false, blank: false)
    updateId(nullable: true)
    message(nullable: true)
    status(nullable: false)
  }
  
  static mapping = {
    id (generator: 'assigned')
  }
}
