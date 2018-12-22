function loadCharData() {
	$.ajax({
				type : "POST",
				url : "system/business/zdgzController/getChart.action",
				dataType : "json",
				data : "",
				success : function(msg) {
					var charData = jsonArr2ChartData(msg);
					showChart(charData);
				}
			});
}

function showChart(charData) {
	var chart = Highcharts.chart('container', {
				title : {
					text : '各地市上传数据总量',
					x : 25,
					align : 'left',
					style : {
						// color : "#ff0000",
						fontWeight : 'bold',
						textAlign : 'right',
						fontSize : '16px'
					}
				},
				yAxis : {
					min : 0,
					title : {
						text : '上传数 (个)'
					}
				},
				// subtitle : {
				// text : '普通的',
				// x : 30,
				// align : 'left',
				// style : {
				// // color : "#ff0000",
				// fontWeight : 'bold',
				// textAlign : 'right',
				// fontSize : '14px'
				// }
				// },
				credits : {
					enabled : false
				},
				xAxis : {
					categories : charData.name
				},
				exporting : {
					enabled : false
				},
				series : [{
							type : 'column',
							colorByPoint : true,
							name : '重点关注',
							data : charData.ct,
							showInLegend : false
						}]
			});
	$('#plain').click(function() {
				$(".active").removeClass("active");
				$("#plain").addClass("active");
				chart.update({
							chart : {
								inverted : false,
								polar : false
							},
							subtitle : {
								text : '普通的'
							}
						});
			});
	$('#inverted').click(function() {
				// chart.update 支持全部属性动态更新
				$(".active").removeClass("active");
				$("#inverted").addClass("active");
				chart.update({
							chart : {
								inverted : true,
								polar : false
							},
							subtitle : {
								text : '反转'
							}
						});
			});
	$('#polar').click(function() {
				$(".active").removeClass("active");
				$("#polar").addClass("active");
				chart.update({
							chart : {
								inverted : false,
								polar : true
							},
							subtitle : {
								text : '极地图'
							}
						});
			});
}
function quickEnter() {
	var cfg = {
		'1001' : {
			image : 'resources/homeFrame/images/1001.png',
			name : '基础数据'
		},
		'1006' : {
			image : 'resources/homeFrame/images/1006.png',
			name : '数据查询'
		},
		'1005' : {
			image : 'resources/homeFrame/images/1005.png',
			name : '数据上传'
		}
	}
	$.post("system/sys/sysController/getMenuTreeList.action", {
				node : 'PC'
			}, function(data) {
				$(data).each(function(idx, item) {
					if (cfg[item.id]) {
						$.ajax({
									url : 'system/sys/sysController/getMenuTreeList.action',
									async : false,
									type : "POST",
									dataType : 'json',
									data : {
										node : item.id
									},
									success : function(dataSon) {
										if (dataSon.length > 0) {
											var icon_html = '<div class="col-sm-2 div_center">';
											icon_html += '<a href="javascript:jumpPage(\'' + item.id + '\',\'' + dataSon[0].id + '\')">';
											icon_html += '<div class="icon_div"><img src="' + cfg[item.id].image
													+ '" height="100%"></div><div class="icon_text">' + cfg[item.id].name
													+ '</div></a></div>';
											$("#quickEnter").append(icon_html);
										}
									}
								});
					}

				});
			}, "json");
}
function jumpPage(id, sonId) {
	if ($('#' + sonId, top.document).length == 0) {
		$('a[href="javascript:hf.loadLeftTree(\'' + id + '\')"]', top.document)[0].click();
		setTimeout(function() {
					$('#' + sonId, top.document)[0].click();
				}, 500);
	} else {
		$('#' + sonId, top.document)[0].click();
	}

}
$(function($) {
			$(".center").css({
						"height" : ($(document).height() - $(".north").height()) + "px"
					});
			quickEnter();
			loadCharData();
		});