Ext.define('HouseAgentSystem.view.calendar.Days', {
	extend: 'Ext.panel.Panel',
	xtype: 'calendar-days-view',

	requires: [
		'Ext.calendar.panel.Days',
		'Ext.calendar.List',
		'Ext.layout.container.Border'
	],
	viewModel: {
		data: {
			value: new Date()
		},
		stores: {
			calStore: {
				type: 'calendar-calendars',
				autoLoad: true,
				proxy: {
					type: 'ajax',
					url: '/calendar/findCalendars',
					reader: {
						type: 'json',
						rootProperty: 'lists'
					}
				},
				eventStoreDefaults: {
					proxy: {
						type: 'ajax',
						url: '/calendar/findEvents',
						reader: {
							type: 'json',
							rootProperty: 'lists'
						}
					}
				}
			}
		}
	},

	//layout: 'border',
	//bind: {title: '{value:date(M Y)}'},

	items: [{
		//region: 'west',
		title: 'Calendars',
		ui: 'light',
		width: 150,
		bodyPadding: 5,
		collapsible: true,
		items: {
			xtype: 'calendar-list',
			bind: '{calStore}'
		}
	}, {
		//region: 'center',
		xtype: 'calendar-days',
		height: 700,
		startTime: 0,
		endTime: 24,
		visibleDays: 2,
		timezoneOffset: -480,
		gestureNavigation: false,
		bind: {
			value: '{value}',
			store: '{calStore}'
		},
		listeners: {
			eventadd: function(view, context) {
				console.log('Event ' + context.event.data.title + ' was added');
				var id = '';
				var title = context.event.data.title;
				var calendarId = context.event.data.calendarId;
				var allDay = context.event.data.allDay;
				var description = context.event.data.description;
				var startDate = context.event.data.startDate;
				var endDate = context.event.data.endDate;

				console.log("前：" + context.event.data.startDate);
				console.log("前：" + context.event.data.endDate);
// 				console.log("后：" + startDate);
// 				console.log("后：" + endDate);
				Ext.Ajax.request({
					url: '/calendar/addEvents',
					method: 'POST',
					params: {
						id: id,
						title: title,
						calendarId: calendarId,
						description: description,
						startDate : startDate,
						endDate : endDate
					},
					success: function() {
						Ext.Msg.alert('Success', '添加成功！');
					},
					failure: function() {
						Ext.Msg.alert('Error', '添加失败！');
					}
				});
			},
			eventedit: function(view, context) { //更新无法监听?
				console.log('Event ' + context.event.data.id + ' was updated');
				
				var id = context.event.data.id;
				var title = context.event.data.title;
				var calendarId = context.event.data.calendarId;
				var allDay = context.event.data.allDay;
				var description = context.event.data.description;
				var startDate = context.event.data.startDate;
				var endDate = context.event.data.endDate;
				
				Ext.Ajax.request({
					url : '/calendar/editEvents',
					method : 'post',
					params: {
						id: id,
						title: title,
						calendarId: calendarId,
						description: description,
						startDate: startDate,
						endDate: endDate
					},
					success: function() {
						Ext.Msg.alert('Success', '修改成功！');
					},
					failure: function() {
						Ext.Msg.alert('Error', '修改失败！');
					}
				});
			},
			eventdrop: function(view, context) {
				console.log("Event " + context.event.data.id + ' was delete');
				var id = context.event.data.id;
				
				Ext.Ajax.request({
					url: '/calendar/deleteEvents',
					method:'POST',
					params: {
						id: id,
					},
					success: function() {
						Ext.Msg.alert('Success', '删除成功！');
					},
					failure: function() {
						Ext.Msg.alert('Error', '删除失败！');
					}
				});
			}
		}
	}]
});
