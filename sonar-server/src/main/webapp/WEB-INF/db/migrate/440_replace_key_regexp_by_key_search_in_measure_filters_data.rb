#
# SonarQube, open source software quality management tool.
# Copyright (C) 2008-2013 SonarSource
# mailto:contact AT sonarsource DOT com
#
# SonarQube is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 3 of the License, or (at your option) any later version.
#
# SonarQube is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with this program; if not, write to the Free Software Foundation,
# Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
#

#
# Sonar 4.0
# SONAR-4195
#
class ReplaceKeyRegexpByKeySearchInMeasureFiltersData < ActiveRecord::Migration

  class MeasureFilter < ActiveRecord::Base
  end

  def self.up
    filters = MeasureFilter.all(:conditions => "data like '%keyRegexp%'")
    filters.each do |filter|
      filter.data = filter.data.sub('keyRegexp', 'keySearch')
      filter.save
    end
  end
end

