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

package org.powertac.common.command

 /**
 * Command object that represents a published tariff
 * which is sent out to all customers and all brokers
 * in the competition. Customers may respond send their
 * response as a <code>TariffDoReply</code> in order
 * to either subscribe to this tariff instance or to
 * start negotiating the conditions.
 *
 * @author Carsten Block
 * @version 1.0, Date: 01.12.10
 * @see org.powertac.common.command.TariffDoReplyCmd
 */
class TariffDoPublishCmd extends AbstractTariff {
  String permittedCustomerTypes
  String authToken
}