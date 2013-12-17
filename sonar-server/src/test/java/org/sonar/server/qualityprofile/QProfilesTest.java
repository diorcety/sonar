/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2013 SonarSource
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

package org.sonar.server.qualityprofile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.server.rule.ProfileRuleQuery;
import org.sonar.server.rule.ProfileRules;
import org.sonar.server.user.UserSession;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QProfilesTest {

  @Mock
  QProfileSearch search;

  @Mock
  QProfileOperations operations;

  @Mock
  ProfileRules rules;

  QProfiles qProfiles;

  @Before
  public void setUp() throws Exception {
    qProfiles = new QProfiles(search, operations, rules);
  }

  @Test
  public void search_profiles() throws Exception {
    qProfiles.searchProfiles();
    verify(search).searchProfiles();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSearchProfile() throws Exception {
    qProfiles.searchProfile(null);
  }

  @Test
  public void new_profile() throws Exception {
    Map<String, String> xmlProfilesByPlugin = newHashMap();
    qProfiles.newProfile("Default", "java", xmlProfilesByPlugin);
    verify(operations).newProfile(eq("Default"), eq("java"), eq(xmlProfilesByPlugin), any(UserSession.class));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteProfile() throws Exception {
    qProfiles.deleteProfile();
  }

  @Test
  public void rename_profile() throws Exception {
    qProfiles.renameProfile(1, "Default profile");
    verify(operations).renameProfile(eq(1), eq("Default profile"), any(UserSession.class));
  }

  @Test
  public void update_default_profile() throws Exception {
    qProfiles.setDefaultProfile(1);
    verify(operations).setDefaultProfile(eq(1), any(UserSession.class));
  }

  @Test
  public void update_default_profile_from_name_and_language() throws Exception {
    qProfiles.setDefaultProfile("Default", "java");
    verify(operations).setDefaultProfile(eq("Default"), eq("java"), any(UserSession.class));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCopyProfile() throws Exception {
    qProfiles.copyProfile();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testExportProfile() throws Exception {
    qProfiles.exportProfile(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testExportProfileByPlugin() throws Exception {
    qProfiles.exportProfile(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRestoreProfile() throws Exception {
    qProfiles.restoreProfile();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInheritance() throws Exception {
    qProfiles.inheritance();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInherit() throws Exception {
    qProfiles.inherit(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testChangelog() throws Exception {
    qProfiles.changelog(null);
  }

  @Test
  public void projects() throws Exception {
    qProfiles.projects(1);
    verify(operations).projects(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void get_profile_from_project_id() throws Exception {
    qProfiles.profile(1);
  }

  @Test
  public void add_project() throws Exception {
    qProfiles.addProject(1, 10L);
    verify(operations).addProject(eq(1), eq(10L), any(UserSession.class));
  }

  @Test
  public void remove_project_by_quality_profile_id() throws Exception {
    qProfiles.removeProject(1, 10L);
    verify(operations).removeProject(eq(1), eq(10L), any(UserSession.class));
  }

  @Test
  public void remove_project_by_language() throws Exception {
    qProfiles.removeProjectByLanguage("java", 10L);
    verify(operations).removeProject(eq("java"), eq(10L), any(UserSession.class));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveAllProjects() throws Exception {
    qProfiles.removeAllProjects(null);
  }

  public void testSearchActiveRules() throws Exception {
    final int profileId = 42;
    ProfileRuleQuery query = ProfileRuleQuery.create(profileId );
    Paging paging = Paging.create(20, 1);
    QProfileRuleResult result = mock(QProfileRuleResult.class);
    when(rules.searchActiveRules(query, paging)).thenReturn(result);
    assertThat(qProfiles.searchActiveRules(query, paging)).isEqualTo(result);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSearchInactiveRules() throws Exception {
    final int profileId = 42;
    ProfileRuleQuery query = ProfileRuleQuery.create(profileId );
    Paging paging = Paging.create(20, 1);
    qProfiles.searchInactiveRules(query, paging);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testActiveRule() throws Exception {
    qProfiles.activeRule(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeactiveRule() throws Exception {
    qProfiles.deactiveRule(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateParameters() {
    qProfiles.updateParameters(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void activeNote() {
    qProfiles.activeNote(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void editNote() {
    qProfiles.editNote(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteNote() {
    qProfiles.deleteNote(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void extendDescription() {
    qProfiles.extendDescription(null, null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void createTemplateRule() throws Exception {
    qProfiles.createTemplateRule();;
  }

  @Test(expected = UnsupportedOperationException.class)
  public void editTemplateRule() throws Exception {
    qProfiles.editTemplateRule();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteTemplateRule() throws Exception {
    qProfiles.deleteTemplateRule();
  }
}