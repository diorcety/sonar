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
package org.sonar.core.rule;

public class RuleTagDto {

  private Long id;
  private Integer ruleId;
  private String tag;
  private String type;

  public Long getId() {
    return id;
  }

  public RuleTagDto setId(Long id) {
    this.id = id;
    return this;
  }

  public Integer getRuleId() {
    return ruleId;
  }

  public RuleTagDto setRuleId(Integer ruleId) {
    this.ruleId = ruleId;
    return this;
  }

  public String getTag() {
    return tag;
  }

  public RuleTagDto setTag(String tag) {
    this.tag = tag;
    return this;
  }

  public String getType() {
    return type;
  }

  public RuleTagDto setType(String type) {
    this.type = type;
    return this;
  }
}
