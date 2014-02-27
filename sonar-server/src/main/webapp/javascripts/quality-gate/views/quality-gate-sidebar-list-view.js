// Generated by CoffeeScript 1.6.3
(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  define(['backbone.marionette', 'handlebars', '../models/quality-gate', '../views/quality-gate-sidebar-list-item-view'], function(Marionette, Handlebars, QualityGate, QualityGateSidebarListItemView) {
    var QualityGateSidebarListView, _ref;
    return QualityGateSidebarListView = (function(_super) {
      __extends(QualityGateSidebarListView, _super);

      function QualityGateSidebarListView() {
        _ref = QualityGateSidebarListView.__super__.constructor.apply(this, arguments);
        return _ref;
      }

      QualityGateSidebarListView.prototype.tagName = 'ul';

      QualityGateSidebarListView.prototype.className = 'sidebar blue-sidebar';

      QualityGateSidebarListView.prototype.template = Handlebars.compile(jQuery('#quality-gate-sidebar-list-template').html());

      QualityGateSidebarListView.prototype.itemView = QualityGateSidebarListItemView;

      QualityGateSidebarListView.prototype.ui = {
        spacer: '.spacer'
      };

      QualityGateSidebarListView.prototype.events = {
        'click #quality-gate-add': 'addQualityGate'
      };

      QualityGateSidebarListView.prototype.itemViewOptions = function(model) {
        return {
          app: this.options.app,
          highlighted: model.get('id') === +this.highlighted
        };
      };

      QualityGateSidebarListView.prototype.appendHtml = function(compositeView, itemView) {
        return itemView.$el.insertBefore(this.ui.spacer);
      };

      QualityGateSidebarListView.prototype.highlight = function(id) {
        this.highlighted = id;
        return this.render();
      };

      QualityGateSidebarListView.prototype.addQualityGate = function() {
        return this.options.app.router.navigate('new', {
          trigger: true
        });
      };

      return QualityGateSidebarListView;

    })(Marionette.CompositeView);
  });

}).call(this);

/*
//@ sourceMappingURL=quality-gate-sidebar-list-view.map
*/