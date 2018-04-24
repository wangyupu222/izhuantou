$(document).ready(function(){

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	if($('.calendar').length > 0){
		$('.calendar').fullCalendar({
			header: {
				left: 'prev,next,today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			buttonText:{
				today:'跳转到当天'
			},
			editable: true,
			events: [
			{
				title: '待收利息：588元;',
				start: new Date(y, m, 1),
				
			},
			
			{
				id: 999,
				title: '待收利息：588元;',
				start: new Date(y, m, 2, 16, 0),
				allDay: false
			},
			{
				id: 999,
				title: '待收利息：588元;',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			},
			{
				title: '待收利息：588元;',
				start: new Date(y, m, d,0,0),
				allDay: false
			},
			
			{
				title: '待收本金：6000元',
				start: new Date(y, m, d,0,0),
				allDay: false
			},
			
			]
		});
	}
	
});