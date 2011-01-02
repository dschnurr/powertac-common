/*
 * Copyright 2009-2010 the original author or authors.
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

class Broker {

  String id = IdGenerator.createId()
  Competition competition
  String userName
  String apiKey

  static belongsTo = [competition: Competition]

  static hasMany = [cashUpdates: CashUpdate, positionUpdates: PositionUpdate, shouts: Shout, tariffs: Tariff]

  static constraints = {
    userName(nullable: false, blank: false, unique: true, minSize: 5, matches: /([a-zA-Z0-9])*/)
    apiKey(blank: false, unique: false, minSize: 32)
  }

  static mapping = {
    id (generator: 'assigned')
  }

  public String toString() {
    return userName
  }
}