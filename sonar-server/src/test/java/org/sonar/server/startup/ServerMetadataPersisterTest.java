/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.server.startup;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.CoreProperties;
import org.sonar.api.platform.Server;
import org.sonar.server.platform.PersistentSettings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class ServerMetadataPersisterTest {

  private TimeZone initialTimeZone;
  private PersistentSettings persistentSettings;

  @Before
  public void fixTimeZone() {
    initialTimeZone = TimeZone.getDefault();
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    persistentSettings = mock(PersistentSettings.class);
  }

  @After
  public void revertTimeZone() {
    TimeZone.setDefault(initialTimeZone);
  }

  @Test
  public void testSaveProperties() throws ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2010-05-18 17:59");
    Server server = mock(Server.class);
    when(server.getPermanentServerId()).thenReturn("1abcdef");
    when(server.getId()).thenReturn("123");
    when(server.getVersion()).thenReturn("3.2");
    when(server.getStartedAt()).thenReturn(date);
    ServerMetadataPersister persister = new ServerMetadataPersister(server, persistentSettings);
    persister.start();

    verify(persistentSettings).saveProperties(argThat(new BaseMatcher<Map<String, String>>() {
      public boolean matches(Object o) {
        Map<String, String> map = (Map<String, String>) o;
        return map.get(CoreProperties.SERVER_ID).equals("123")
          && map.get(CoreProperties.SERVER_VERSION).equals("3.2")
          && map.get(CoreProperties.SERVER_STARTTIME).equals("2010-05-18T17:59:00+0000")
          && map.size() == 3;
      }

      public void describeTo(Description description) {
      }
    }));
  }


}
