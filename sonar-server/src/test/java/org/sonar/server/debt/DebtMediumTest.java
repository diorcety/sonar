/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.server.debt;

import org.junit.Test;
import org.sonar.api.server.debt.DebtCharacteristic;
import org.sonar.api.server.debt.internal.DefaultDebtCharacteristic;
import org.sonar.core.permission.GlobalPermissions;
import org.sonar.server.MediumTest;
import org.sonar.server.user.MockUserSession;

import static org.fest.assertions.Assertions.assertThat;

public class DebtMediumTest extends MediumTest {

  @Test
  public void find_characteristics() throws Exception {
    DebtModelService debtModelService = tester.get(DebtModelService.class);

    // Only root characteristics
    assertThat(debtModelService.characteristics()).hasSize(8);

    // Characteristics and sub-characteristics
    assertThat(debtModelService.allCharacteristics()).hasSize(39);
  }

  @Test
  public void create_characteristic() throws Exception {
    MockUserSession.set().setGlobalPermissions(GlobalPermissions.SYSTEM_ADMIN);

    DebtModelService debtModelService = tester.get(DebtModelService.class);
    int nb = debtModelService.characteristics().size();

    DebtCharacteristic result = debtModelService.create("New characteristic", null);

    assertThat(result.name()).isEqualTo("New characteristic");
    assertThat(result.key()).isEqualTo("NEW_CHARACTERISTIC");
    assertThat(result.isSub()).isFalse();
    assertThat(result.order()).isEqualTo(nb + 1);

    assertThat(debtModelService.characteristicByKey(result.key())).isNotNull();
  }

  @Test
  public void create_sub_characteristic() throws Exception {
    MockUserSession.set().setGlobalPermissions(GlobalPermissions.SYSTEM_ADMIN);

    DebtModelService debtModelService = tester.get(DebtModelService.class);

    DefaultDebtCharacteristic parent = (DefaultDebtCharacteristic) debtModelService.characteristicByKey("REUSABILITY");

    DebtCharacteristic result = debtModelService.create("New characteristic", parent.id());

    assertThat(result.name()).isEqualTo("New characteristic");
    assertThat(result.key()).isEqualTo("NEW_CHARACTERISTIC");
    assertThat(result.isSub()).isTrue();
    assertThat(result.order()).isNull();

    assertThat(debtModelService.characteristicByKey(result.key())).isNotNull();
  }

}
