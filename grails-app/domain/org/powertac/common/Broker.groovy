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

package org.powertac.common

/**
 * A broker domain instance represents a competition participants, or more
 * precisely the competition participant's agent. Every incoming request
 * from a broker agent is authenticated against the credentials stored in this class.
 *
 * @author Carsten Block, David Dauer
 * @version 1.1, 03/22/2011
 */
class Broker 
{

  //String id = IdGenerator.createId()

  /** the broker's login or user name    */
  String username
  /* Spring Security-related generated fields */
  String password
  Boolean enabled
  Boolean accountExpired
  Boolean accountLocked
  Boolean passwordExpired

  /** the broker's identifier token    */
  String apiKey = IdGenerator.createId()
  
  /** If true, the broker is local to the server and does not receive messages */
  Boolean local = false

  /** Broker's current cash position */
  CashPosition cash

  static auditable = true

  static hasMany = [shouts: Shout, tariffs: Tariff, marketPositions: MarketPosition]
  static transients = ['cashPosition']
  static constraints = {
    //id(nullable: false, blank: false, unique: true)
    username(nullable: false, blank: false, unique: true, minSize: 2, matches: /([a-zA-Z0-9_])*/)
    password(nullable: true, blank: false, minSize: 2) // TODO: with nullable:false all tests fail?!
    apiKey(nullable: false, blank: false, unique: true, minSize: 32)
    cash(nullable: true)
    enabled(nullable: true)
    accountExpired(nullable: true)
    accountLocked(nullable: true)
    passwordExpired(nullable: true)
    //shouts(nullable: true)
    //tariffs(nullable: true)
    //marketPositions(nullable: true)
  }

  static mapping = {
    cache(true)
    id(generator: 'assigned', name: 'apiKey', type: 'string')
    username(index: 'username_apikey_idx')
    apiKey(index: 'username_apikey_idx')
    password(column: '`password`') // Generated by Spring Security
  }

  CashPosition getCash () 
  {
    if (cash == null) {
      cash = new CashPosition(broker: this, overallBalance: 0.0)
    }
    return cash
  }
  
  void setCash (CashPosition thing)
  {
    cash = thing
  }
  
  // Generated by Spring Security
  Set<Role> getAuthorities() {
    BrokerRole.findAllByBroker(this).collect { it.role } as Set
  }

  public String toString() {
    return username
  }

  public String toQueueName() {
    return "brokers.${this.username}.outputQueue";
  }
}
